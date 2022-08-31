package admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.dao.ContentsDAO;
import admin.dto.ContentsDTO;
import config.Pager;

@WebServlet("/admin/notice/*")
public class AdminContentsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String url = request.getRequestURL().toString();
		String ctx = request.getContextPath();
		ContentsDAO dao = new ContentsDAO();
		
		if(url.indexOf("noticeList") != -1) {
			String order = request.getParameter("order");
			String op = request.getParameter("op");
			String keyword = request.getParameter("keyword");

			if (keyword == null) keyword = "";
			if (op == null)	op = "subject";
			if (order == null) order = "p_idx";

			int count = dao.getContestsCount(op, keyword);

			int curPage = 1;
			if (request.getParameter("page") != null) {
				curPage = Integer.parseInt(request.getParameter("page"));
			}
			Pager pager = new Pager(count, curPage);
			int start = pager.getPageBegin();
			int end = pager.getPageEnd();
			
			
			List<ContentsDTO> list = dao.getContentsList(start, end, order, op, keyword);
			
			request.setAttribute("list", list);
			request.setAttribute("page", pager);
			request.setAttribute("count", count);
			request.setAttribute("order", order);
			request.setAttribute("op", op);
			request.setAttribute("keyword", keyword);

			String page = "/Admin/contentsList.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}

}
