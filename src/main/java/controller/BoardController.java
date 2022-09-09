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
import dto.ContentsCommentDTO;
import dto.ContentsDTO;

@WebServlet("/board/*")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String uri = request.getRequestURI();
		String ctx = request.getContentType();
		BoardDAO dao = new BoardDAO();
			
		if(uri.indexOf("view") != -1) {
			int c_idx = 0;
			if(request.getParameter("c_idx") != null) {
				c_idx = Integer.parseInt(request.getParameter("c_idx"));
			}
			ContentsDTO dto = dao.getDetailContentsView(c_idx);		
			request.setAttribute("dto", dto);
			String page = "/view.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		
		} else if(uri.indexOf("commentList.do") != -1) {
			int c_idx = Integer.parseInt(request.getParameter("c_idx"));
			List<ContentsCommentDTO> list = dao.getCommentList(c_idx);
			request.setAttribute("list", list);
			String page = "/comment_list.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		
		} else if(uri.indexOf("addComment.do") != -1) {
			int c_idx = Integer.parseInt(request.getParameter("c_idx"));
			String writer = request.getParameter("writer");
			String content = request.getParameter("content");
			ContentsCommentDTO dto = new ContentsCommentDTO();
			dto.setC_idx(c_idx);
			dto.setWriter(writer);
			dto.setContent(content);
			dao.addComment(dto);
		
		} else if(uri.indexOf("deleteComment.do") != -1) {
			int cmt_idx = Integer.parseInt(request.getParameter("cmt_idx"));
			dao.deleteComment(cmt_idx);
			
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}

}
