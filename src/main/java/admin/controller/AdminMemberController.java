package admin.controller;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import admin.dao.MemberDAO;
import admin.dto.MemberDTO;
import config.FileUpload;
import config.Pager;

/**
 * Servlet implementation class AdminMemberController
 */
@WebServlet("/admin/member/*")
public class AdminMemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String url = request.getRequestURL().toString();
		String ctx = request.getContextPath();
		MemberDAO dao = new MemberDAO();
		
		if (url.indexOf("userList") != -1) { // 회원목록 리스트

			String order = request.getParameter("order");
			String op = request.getParameter("op");
			String keyword = request.getParameter("keyword");
			String authority = "사용자";

			if (keyword == null)
				keyword = "";
			if (op == null)
				op = "userid";
			if (order == null)
				order = "join_date";

			int count = dao.getMemberCount(authority, op, keyword);

			int curPage = 1;
			if (request.getParameter("page") != null) {
				curPage = Integer.parseInt(request.getParameter("page"));
			}

			Pager pager = new Pager(count, curPage);
			int start = pager.getPageBegin();
			int end = pager.getPageEnd();
			List<MemberDTO> list = dao.getMemberList(authority, start, end, order, op, keyword);
			request.setAttribute("list", list);
			request.setAttribute("page", pager);
			request.setAttribute("count", count);
			request.setAttribute("order", order);
			request.setAttribute("op", op);
			request.setAttribute("keyword", keyword);

			String page = "/Admin/userList.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		} else if (url.indexOf("adminList") != -1) {
			String order = request.getParameter("order");
			String op = request.getParameter("op");
			String keyword = request.getParameter("keyword");
			String authority = "관리자";

			if (keyword == null)
				keyword = "";
			if (op == null)
				op = "userid";
			if (order == null)
				order = "join_date";

			int count = dao.getMemberCount(authority, op, keyword);
			int curPage = 1;
			if (request.getParameter("page") != null) {
				curPage = Integer.parseInt(request.getParameter("page"));
			}

			Pager pager = new Pager(count, curPage);
			int start = pager.getPageBegin();
			int end = pager.getPageEnd();

			List<MemberDTO> list = dao.getMemberList(authority, start, end, order, op, keyword);

			request.setAttribute("list", list);
			request.setAttribute("page", pager);
			request.setAttribute("count", count);
			request.setAttribute("order", order);
			request.setAttribute("op", op);
			request.setAttribute("keyword", keyword);

			String page = "/Admin/adminList.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);

		} else if (url.indexOf("search.do") != -1) {
			String op = request.getParameter("op");
			String keyword = request.getParameter("keyword");
			String authority = request.getParameter("authority");
			
			if(keyword == null) keyword = "";
			 
			int result = dao.getMemberCount(authority, op, keyword);
			if(result > 0) {
				response.getWriter().write("true");
			}
			
		} else if (url.indexOf("editMember") != -1) {
			int m_idx = Integer.parseInt(request.getParameter("m_idx"));
			MemberDTO dto = dao.detailVeiw(m_idx);
			request.setAttribute("dto", dto);
			String page = "/Admin/editMember.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);

		} else if (url.indexOf("updateMember.do") != -1) {
			File uploadDir = new File(FileUpload.PRO_UPLOAD_PATH);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}

			MultipartRequest multi = new MultipartRequest(request, FileUpload.PRO_UPLOAD_PATH, FileUpload.MAX_UPLOAD,
					"utf-8", new DefaultFileRenamePolicy());

			int m_idx = Integer.parseInt(multi.getParameter("m_idx"));
			String userid = multi.getParameter("userid");
			String passwd = multi.getParameter("passwd");
			String name = multi.getParameter("name");
			String email = multi.getParameter("email");
			String phone = multi.getParameter("phone");
			String authority = multi.getParameter("authority");
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
				MemberDTO dto2 = dao.detailVeiw(m_idx);
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

			String page = "";
			if (authority.equals("관리자")) {
				page = "/admin/member/adminList";
			} else if (authority.equals("사용자")) {
				page = "/admin/member/userList";
			}
			response.sendRedirect(ctx + page);

		} else if (url.indexOf("deleteMember.do") != -1) {
			MultipartRequest multi = new MultipartRequest(request, FileUpload.PRO_UPLOAD_PATH, FileUpload.MAX_UPLOAD,
					"utf-8", new DefaultFileRenamePolicy());

			int m_idx = Integer.parseInt(multi.getParameter("m_idx"));
			String authority = multi.getParameter("authority");

			dao.deleteMember(m_idx);

			String page = "";
			if (authority.equals("관리자")) {
				page = "/admin/member/adminList";
			} else if (authority.equals("사용자")) {
				page = "/admin/member/userList";
			}
			response.sendRedirect(ctx + page);

		} else if (url.indexOf("addMember") != -1) {
			String page = "/Admin/addMember.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);

		} else if (url.indexOf("duplicate.do") != -1) {
			//아이디 중복체크
			String userid = request.getParameter("userid");
			String result = dao.useridCheck(userid);
			if (result == null) {
				response.getWriter().write("0");
			}
		
		} else if (url.indexOf("joinMember.do") != -1) {
			File uploadDir = new File(FileUpload.PRO_UPLOAD_PATH);
			if(!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			
			MultipartRequest multi = new MultipartRequest(request, FileUpload.PRO_UPLOAD_PATH,
					FileUpload.MAX_UPLOAD, "utf-8", new DefaultFileRenamePolicy());
			
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
			
			dao.joinMember(dto);
			
			String page = "";
			if (authority.equals("관리자")) {
				page = "/admin/member/adminList";
			} else if (authority.equals("사용자")) {
				page = "/admin/member/userList";
			}
			
			response.sendRedirect(ctx + page);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
