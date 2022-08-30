package test;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import config.FileUpload;
import admin.dao.MemberDAO;
import admin.dto.MemberDTO;

@WebServlet("/pageTest/*")
public class testController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String uri = request.getRequestURI();
		String ctx = request.getContextPath();
		String page = "/test/insert.jsp";
		MemberDAO dao = new MemberDAO();
		
		if(uri.indexOf("join.do") != -1) {
			File uploadDir = new File(FileUpload.PRO_UPLOAD_PATH);
			if(!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			
			MultipartRequest multi = new MultipartRequest(request, FileUpload.PRO_UPLOAD_PATH,
					FileUpload.MAX_UPLOAD, "utf-8", new DefaultFileRenamePolicy());
			
			String userid = multi.getParameter("userid");
			String passwd = multi.getParameter("passwd");
			String name = multi.getParameter("name");
			String email = multi.getParameter("email");
			String phone = multi.getParameter("phone");
			String consent = multi.getParameter("consent");
			String privacy = multi.getParameter("privacy");
			String authority = multi.getParameter("authority");
	
			
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
			
			int result = dao.join(dto);
			response.getWriter().write(result);
			response.sendRedirect(ctx + page);
			
		} else if(uri.indexOf("insertBoard.do") != -1) {
			String title = request.getParameter("title");
			String sub_title = request.getParameter("sub_title");
			
			System.out.println("title : " + title);
			System.out.println("sub_title : " + sub_title);
			
			response.sendRedirect(ctx + page);
		
		} else if(uri.indexOf("writer.do") != -1) {
			File uploadDir = new File(FileUpload.UPLOAD_PATH);
			if(!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			
			MultipartRequest multi = new MultipartRequest(request, FileUpload.UPLOAD_PATH,
					FileUpload.MAX_UPLOAD, "utf-8", new DefaultFileRenamePolicy());
			
			String cate = multi.getParameter("cate");
			String subject = multi.getParameter("subject");
			String content = multi.getParameter("content");
			String thumb = " ";
			int filesize = 0;
			
			try {
				Enumeration files = multi.getFileNames();
				while(files.hasMoreElements()) {
					String file2 = (String) files.nextElement();
					thumb = multi.getFilesystemName(file2);
					File f2 = multi.getFile(file2);
					if(f2 != null) {
						filesize = (int) f2.length();
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			System.out.println("cate : " + cate);
			System.out.println("subject : " + subject);
			System.out.println("content : " + content);
			System.out.println("thumb : " + thumb);
			System.out.println("filesize : " + filesize);
			
			response.sendRedirect(ctx + page);
			
			
		} else if ( uri.indexOf("idCheck.do") != -1) {
			
			String userid = request.getParameter("userid");	
			
			System.out.println(userid);
			MemberDTO dto = new MemberDTO();
			dto.setUserid(userid);
			
			String result = dao.useridCheck(userid);
			
			System.out.println("dao결과값 : " + result);
			
			if(result == null){
				result = "";
				response.getWriter().write("true");
			}
			
			if(result.equals(userid)) {
				response.getWriter().write("false");
			} 
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}

}
