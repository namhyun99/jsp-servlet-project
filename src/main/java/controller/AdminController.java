package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDAO;
import dto.MemberDTO;


@WebServlet("/admin/*")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String url = request.getRequestURL().toString();
		String ctx = request.getContextPath();
		MemberDAO dao = new MemberDAO();
		
		if(url.indexOf("login.do") != -1) {
			String page = "/Admin/login.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		
		} else if(url.indexOf("actionLogin") != -1) {
			String userid = request.getParameter("userid");
			String passwd = request.getParameter("passwd");
			MemberDTO dto = new MemberDTO();
			dto.setUserid(userid);
			dto.setPasswd(passwd);		
			String authority = dao.loginCheck(dto); 
			System.out.println("authority : " + authority);
			if(authority == null || !authority.equals("관리자")) { //만약 roll값이 null이거나, admin이 아니라면
				response.getWriter().print("false");; //ajax호출 결과값 보내기
			} else { // admin이 맞다면..
				HttpSession session = request.getSession();
				session.setAttribute("userid", userid);
				session.setAttribute("authority", authority);
			}
		
		} else if(url.indexOf("main") != -1) {
			String page = "/Admin/main.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		
		} else if(url.indexOf("logout") != -1) {
			HttpSession session = request.getSession();
			session.invalidate();
			response.sendRedirect(ctx+"/admin/login.do");
		
		} else if(url.indexOf("remind") != -1) {
			String page = "/Admin/remind_id_pw.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		
		} else if(url.indexOf("actionID") != -1) {
			String name = request.getParameter("name");
			String phone = request.getParameter("phone");
			MemberDTO dto = new MemberDTO();
			dto.setName(name);
			dto.setPhone(phone);			
			String result = dao.remindID(dto);
			response.getWriter().print(result);
		
		} else if(url.indexOf("actionPwd") != -1) {
			String userid = request.getParameter("userid");
			String email = request.getParameter("email");
			System.out.println("userid : " + userid);
			System.out.println("email : " + email);
			MemberDTO dto = new MemberDTO();
			dto.setUserid(userid);
			dto.setEmail(email);
			String result = dao.remindPwd(dto);
			System.out.println("DB결과 :" + result);
			response.getWriter().print(result);			
		
		} else if(url.indexOf("userList") != -1) {
			List<MemberDTO> list = dao.getUserList();
			request.setAttribute("list", list);
			String page = "/Admin/userList.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		}
		

		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}

}
