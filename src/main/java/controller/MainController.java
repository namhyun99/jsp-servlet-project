package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDAO;
import dao.MemberDAO;
import dto.CategoryDTO;
import dto.ContentsDTO;
import dto.MemberDTO;
import util.Pager;

@WebServlet("/main")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		BoardDAO dao = new BoardDAO();

		int count = dao.getContentsCount();
		int curPage = 1;
		if (request.getParameter("page") != null) {
			curPage = Integer.parseInt(request.getParameter("page"));
		}
		Pager pager = new Pager(count, curPage);
		int start = pager.getPageBegin();
		int end = pager.getPageEnd();
		int totPage = pager.getTotPage();
		String order="view_cnt";	
		
		List<ContentsDTO> list = dao.getContentsList(start, end, order);
		request.setAttribute("list", list);
		request.setAttribute("page", pager);
		request.setAttribute("totPage", totPage);
		request.setAttribute("count", count);
		String page = "/main.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}

}
