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
import dto.ContentsDTO;
import util.Pager;

@WebServlet("/search")
public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	
		BoardDAO dao = new BoardDAO();
		String keyword = request.getParameter("keyword");	
		
		if(keyword != null) {
			int curPage = 1;
			if (request.getParameter("curPage") != null) {
				curPage = Integer.parseInt(request.getParameter("curPage"));
			}
			int count = dao.getSearchCount(keyword);	
			Pager pager = new Pager(count, curPage);
			int start = pager.getPageBegin();
			int end = pager.getPageEnd();
			int totPage = pager.getTotPage();
			String order="view_cnt";	

			List<ContentsDTO> list = dao.getSearchList(start, end, order, keyword);
			request.setAttribute("list", list);
			request.setAttribute("page", pager);
			request.setAttribute("totPage", totPage);
			request.setAttribute("count", count);
			request.setAttribute("keyword", keyword);
			
			String page = "/search_result.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		} else {
			String page = "/search.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}

}
