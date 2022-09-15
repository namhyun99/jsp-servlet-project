package controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import dao.MemberDAO;
import dto.MemberDTO;
import util.BCrypt;
import util.FileRenamePoicy;
import util.FileUpload;
import util.RandomPwd;

@WebServlet("/member/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String uri = request.getRequestURI();
		String ctx = request.getContextPath();
		MemberDAO dao = new MemberDAO();

		// 로그인 화면
		if (uri.indexOf("login") != -1) {
			String page = "/account/login.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		
		// ID,PW 찾기 화면
		} else if (uri.indexOf("remind") != -1) {
			String page = "/account/remind.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);

		//회원가입 화면
		} else if (uri.indexOf("join") != -1) {
			String page = "/account/join.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);

		//로그인 하기
		} else if (uri.indexOf("actionLogin.do") != -1) {

			String userid = request.getParameter("userid");
			String passwd = request.getParameter("passwd");
			MemberDTO dto = new MemberDTO();
			dto.setUserid(userid);
			dto.setPasswd(passwd);
			String dbPasswd = dao.passwdCheck(userid); //db에 저장된 비밀번호 호출
			if (dbPasswd == null) {
				response.getWriter().print("false");
			} else if (BCrypt.checkpw(dto.getPasswd(), dbPasswd)) {
				dto.setPasswd(dbPasswd); //dto비밀번호랑 db에서 호출한 비밀번호 비교후 맞으면 db페스워드로 셋팅
				MemberDTO dto1 = dao.loginCheck(userid, dbPasswd);
				if (dto1 == null) { // 만약값이 null이면
					response.getWriter().print("false"); // ajax호출 결과값 보내기
				} else { // 값이 있다면..
					HttpSession session = request.getSession();
					session.setAttribute("m_idx", dto1.getM_idx());
					session.setAttribute("userid", dto1.getUserid());
					session.setAttribute("profile_img", dto1.getProfile_img());
					session.setAttribute("name", dto1.getName());
					session.setAttribute("authority", dto1.getAuthority());
				}
			} else {
				response.getWriter().print("false");
			}

	    //로그아웃 하기
		} else if (uri.indexOf("logout.do") != -1) {
			HttpSession session = request.getSession();
			session.invalidate();
			response.sendRedirect(ctx+"/main");		
		
		//아이디 찾기
		} else if(uri.indexOf("actionID") != -1) { //아이디찾기
			String name = request.getParameter("name");
			String phone = request.getParameter("phone");
			MemberDTO dto = new MemberDTO();
			dto.setName(name);
			dto.setPhone(phone);			
			String result = dao.remindID(dto);
			response.getWriter().print(result);
		
		//비밀번호 찾기
		} else if(uri.indexOf("actionPwd") != -1) { //비밀번호찾기
			String userid = request.getParameter("userid");
			String email = request.getParameter("email");
			MemberDTO dto = new MemberDTO();
			dto.setUserid(userid);
			dto.setEmail(email);
			String result = dao.remindPwd(dto);
			if(result != null) {
				RandomPwd c = new RandomPwd();
				String passwd = c.getRandomPassword(10);
				dto.setPasswd(passwd);
				dao.updatePasswd(dto);
				response.getWriter().print(passwd);	
			} else {
				response.getWriter().print("null");				
			}
			
		//아이디 중복체크
		} else if (uri.indexOf("duplicate.do") != -1) {
			String userid = request.getParameter("userid");
			String result = dao.useridCheck(userid);
			if (result == null) {
				response.getWriter().write("0");
			}
		
		//회원가입 하기
		} else if(uri.indexOf("insertMember.do") != -1) {
			File uploadDir = new File(FileUpload.PRO_UPLOAD_PATH);
			if(!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			
			MultipartRequest multi = new MultipartRequest(request, FileUpload.PRO_UPLOAD_PATH,
					FileUpload.MAX_UPLOAD, "utf-8", new FileRenamePoicy());

			String userid = multi.getParameter("userid");
			String passwd = multi.getParameter("passwd");
			String name = multi.getParameter("name");
			String email1 = multi.getParameter("email1");
			String email2 = multi.getParameter("email2");
			String phone1 = multi.getParameter("phone1");
			String phone2 = multi.getParameter("phone2");
			String phone3 = multi.getParameter("phone3");
			String consent = multi.getParameter("consent");
			String privacy = multi.getParameter("privacy");
			String authority = multi.getParameter("authority");
					
			String email = email1 + "@" + email2;
			String phone = phone1 + phone2 + phone3;	
			//이메일, 전화번호 값이 없다면..
			if(email1.equals("") || email2.equals("")) {
				email = "-";
			} 
			
			if(phone1.equals("") || phone2.equals("") || phone3.equals("")) {
				phone = "-";
			}
				
			String profile_img = " ";
			int filesize = 0;
			
			try {
				Enumeration files = multi.getFileNames();
				while(files.hasMoreElements()) {
					String file1 = (String) files.nextElement();
					profile_img = multi.getFilesystemName(file1);
					File f1 = multi.getFile(file1);
					if(f1 != null) {
						filesize = (int)f1.length();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			MemberDTO dto = new MemberDTO();
			dto.setUserid(userid);
			dto.setPasswd(passwd);
			dto.setName(name);
			dto.setEmail(email);
			dto.setPhone(phone);
			dto.setConsent(consent);
			dto.setPrivacy(privacy);
			//파일 첨부를 하지 않을 경우
			if(profile_img == null || profile_img.trim().equals("")) { //null값이거나 빈문자열일때
				profile_img = "-";
			}
			dto.setProfile_img(profile_img);
			dto.setAuthority(authority);

			dao.insertMember(dto);
			String page = "/member/login";
			String msg = URLEncoder.encode("가입완료! 로그인 부탁드립니다!", "utf-8");
			response.sendRedirect(ctx + page + "?msg=" + msg);
		
		// 설정 화면
		} else if(uri.indexOf("setting") != -1) {
			
			int m_idx = Integer.parseInt(request.getParameter("m_idx"));
			MemberDTO dto = dao.getMemberDetailView(m_idx);
			request.setAttribute("dto", dto);			
			String page = "/mypage/setting.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		
		// 회원정보 수정하기
		} else if(uri.indexOf("updateMember.do") != -1) {
			File uploadDir = new File(FileUpload.PRO_UPLOAD_PATH);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}

			MultipartRequest multi = new MultipartRequest(request, FileUpload.PRO_UPLOAD_PATH, FileUpload.MAX_UPLOAD,
					"utf-8", new FileRenamePoicy() );
			int m_idx = Integer.parseInt(multi.getParameter("m_idx"));
			String userid = multi.getParameter("userid");
			String passwd = multi.getParameter("passwd");
			String name = multi.getParameter("name");
			String email = multi.getParameter("email");
			String phone = multi.getParameter("phone");
			String profile_img = " ";

			try {
				Enumeration files = multi.getFileNames();
				while (files.hasMoreElements()) {
					String file1 = (String) files.nextElement();
					profile_img = multi.getFilesystemName(file1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			MemberDTO dto = new MemberDTO();

			dto.setM_idx(m_idx);
			dto.setPasswd(passwd);
			dto.setName(name);
			dto.setEmail(email);
			dto.setPhone(phone);

			if (profile_img == null || profile_img.trim().equals("")) {
				// 새로운 썸네일이 없을때
				MemberDTO dto2 = dao.getMemberDetailView(m_idx);
				String fName = dto2.getProfile_img();
				dto.setProfile_img(fName);
			} else {
				// 새로운 썸네일이 있다면..
				dto.setProfile_img(profile_img);
			}

			// 첨부파일 삭제
			String fileDel = multi.getParameter("fileDel");
			if (fileDel != null && fileDel.equals("on")) {
				String profileImg = dao.getProfileImg(m_idx);
				File f = new File(FileUpload.PRO_UPLOAD_PATH + profileImg);
				f.delete(); // 파일 삭제
				dto.setProfile_img("-");
			}

			String dbPasswd = dao.passwdCheck(userid);		
			dao.updateMember(dto, dbPasswd);
			String page = "/main";
			response.sendRedirect(ctx + page);
		
		//회원탈퇴 하기
		} else if (uri.indexOf("deleteMember.do") != -1) {
			MultipartRequest multi = new MultipartRequest(request, FileUpload.PRO_UPLOAD_PATH, FileUpload.MAX_UPLOAD,
					"utf-8", new FileRenamePoicy());

			int m_idx = Integer.parseInt(multi.getParameter("m_idx"));	
			
			//프로필 이미지 파일삭제
			String filename = dao.getProfileImg(m_idx);
			File f = new File(FileUpload.PRO_UPLOAD_PATH + filename);
			f.delete();

			dao.deleteMember(m_idx);
			HttpSession session = request.getSession();
			session.invalidate(); //탈퇴시 세션 초기화 
			String page = "/main";
			response.sendRedirect(ctx + page);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
