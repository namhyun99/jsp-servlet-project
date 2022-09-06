package admin.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import admin.dao.MemberDAO;
import dto.MemberDTO;
import util.BCrypt;
import util.RandomPwd;

/**
 * Servlet implementation class AdminLoginController
 */
@WebServlet("/admin/login/*")
public class AdminLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String url = request.getRequestURL().toString();
		String ctx = request.getContextPath();
		MemberDAO dao = new MemberDAO();
		
		if(url.indexOf("actionLogin") != -1) { //로그인 하기
			String userid = request.getParameter("userid");
			String passwd = request.getParameter("passwd");
			MemberDTO dto = new MemberDTO();
			dto.setUserid(userid);	
			dto.setPasswd(passwd);
			
			String dbPasswd = dao.passwdCheck(userid);
			if(dbPasswd == null) {
				response.getWriter().print("false");
			
			} else if(BCrypt.checkpw(dto.getPasswd(), dbPasswd)) {			
				dto.setPasswd(dbPasswd);
				String authority = dao.loginCheck(dto); 
				if(authority == null || !authority.equals("관리자")) { //만약값이 null이거나, admin이 아니라면
					response.getWriter().print("false"); //ajax호출 결과값 보내기
				} else { // admin이 맞다면..
					HttpSession session = request.getSession();
					session.setAttribute("userid", userid);
					session.setAttribute("authority", authority);
				}
			
			} else {
				response.getWriter().print("false");
			}
		}else if(url.indexOf("logout.do") != -1) { //로그아웃
			HttpSession session = request.getSession();
			session.invalidate();
			response.sendRedirect(ctx+"/admin/login.do");
		
		} else if(url.indexOf("remind") != -1) { //아이디,비번찾기
			String page = "/Admin/remind_id_pw.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		
		} else if(url.indexOf("actionID") != -1) { //아이디찾기
			String name = request.getParameter("name");
			String phone = request.getParameter("phone");
			MemberDTO dto = new MemberDTO();
			dto.setName(name);
			dto.setPhone(phone);			
			String result = dao.remindID(dto);
			response.getWriter().print(result);
		
		} else if(url.indexOf("actionPwd") != -1) { //비밀번호찾기
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
			
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}

}
