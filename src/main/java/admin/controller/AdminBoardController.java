package admin.controller;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Date;
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

import admin.dao.BoardDAO;
import admin.dto.BoardDTO;
import admin.dto.ContentsDTO;
import config.FileUpload;
import config.Pager;

@WebServlet("/admin/board/*")
public class AdminBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = request.getRequestURL().toString();
		String ctx = request.getContextPath();
		BoardDAO dao = new BoardDAO();

		if (url.indexOf("contents") != -1) {
			String order = request.getParameter("order");
			String op = request.getParameter("op");
			String keyword = request.getParameter("keyword");

			if (keyword == null)
				keyword = "";
			if (op == null)
				op = "subject";
			if (order == null)
				order = "write_date";

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
		
		} else if (url.indexOf("notice") != -1) {
			String order = request.getParameter("order");
			String op = request.getParameter("op");
			String keyword = request.getParameter("keyword");

			if (keyword == null)
				keyword = "";
			if (op == null)
				op = "subject";
			if (order == null)
				order = "write_date";

			int count = dao.getNoticeCount(op, keyword);

			int curPage = 1;
			if (request.getParameter("page") != null) {
				curPage = Integer.parseInt(request.getParameter("page"));
			}
			Pager pager = new Pager(count, curPage);
			int start = pager.getPageBegin();
			int end = pager.getPageEnd();

			List<ContentsDTO> list = dao.getNoticeList(start, end, order, op, keyword);

			request.setAttribute("list", list);
			request.setAttribute("page", pager);
			request.setAttribute("count", count);
			request.setAttribute("order", order);
			request.setAttribute("op", op);
			request.setAttribute("keyword", keyword);

			String page = "/Admin/noticeList.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		
		} else if (url.indexOf("faq") != -1) {
			String order = request.getParameter("order");
			String op = request.getParameter("op");
			String keyword = request.getParameter("keyword");

			if (keyword == null)
				keyword = "";
			if (op == null)
				op = "subject";
			if (order == null)
				order = "write_date";

			int count = dao.getFaqCount(op, keyword);

			int curPage = 1;
			if (request.getParameter("page") != null) {
				curPage = Integer.parseInt(request.getParameter("page"));
			}
			Pager pager = new Pager(count, curPage);
			int start = pager.getPageBegin();
			int end = pager.getPageEnd();

			List<ContentsDTO> list = dao.getFaqList(start, end, order, op, keyword);

			request.setAttribute("list", list);
			request.setAttribute("page", pager);
			request.setAttribute("count", count);
			request.setAttribute("order", order);
			request.setAttribute("op", op);
			request.setAttribute("keyword", keyword);

			String page = "/Admin/faqList.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		
		} else if(url.indexOf("editBoard") != -1) {
			int board_no = Integer.parseInt(request.getParameter("board_no"));
			int c_idx = Integer.parseInt(request.getParameter("c_idx"));
			
			ContentsDTO dto = dao.detailBoardVeiw(board_no, c_idx);
			String bdTitle = dao.getBoardTitle(dto.getBoard_no());

			request.setAttribute("dto", dto);
			request.setAttribute("bdTitle", bdTitle);
			String page = "/Admin/editBoard.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		
		} else if(url.indexOf("update.do") != -1) {
			File uploadDir = new File(FileUpload.UPLOAD_PATH);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			MultipartRequest multi = new MultipartRequest(request, FileUpload.UPLOAD_PATH, FileUpload.MAX_UPLOAD,
					"utf-8", new DefaultFileRenamePolicy());
			
			int c_idx = Integer.parseInt(multi.getParameter("c_idx"));
			int board_no = Integer.parseInt(multi.getParameter("board_no"));
			String subject = multi.getParameter("subject");
			String content = multi.getParameter("content");
			String show = multi.getParameter("show");
			String ip = request.getRemoteAddr();
			if(ip.equalsIgnoreCase("0:0:0:0:0:0:0:1")) { //안써주는것보다 써주는게 좋다.
				InetAddress inetAddress = InetAddress.getLocalHost();
				ip = inetAddress.getHostAddress();
			}
			String filename = " ";	
			int filesize = 0;
			
			try {
				Enumeration files = multi.getFileNames();
				//summernote 때문에 while문을 돌리지 않고 첫번째 첨부파일만 받아온다.
				String file1 = (String) files.nextElement();
				filename = multi.getFilesystemName(file1);
				File f1 = multi.getFile(file1);
				if(f1 != null) {
					filesize = (int)f1.length();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			ContentsDTO dto = new ContentsDTO();
			dto.setC_idx(c_idx);
			dto.setBoard_no(board_no);
			dto.setSubject(subject);
			dto.setContent(content);
			dto.setShow(show);
			dto.setUpdate_date(new Date());
			dto.setIp(ip);
			
			if (filename == null || filename.trim().equals("")) {
				// 새로운 썸네일이 없을때
				ContentsDTO dto2 = dao.detailBoardVeiw(board_no, c_idx);
				String fName = dto2.getFilename();
				int fSize = dto2.getFilesize();
				dto.setFilename(fName);
				dto.setFilesize(fSize);
			} else {
				// 새로운 썸네일이 있다면..
				dto.setFilename(filename);
				dto.setFilesize(filesize);
			}

			// 첨부파일 삭제
			String fileDel = multi.getParameter("fileDel");
			if (fileDel != null && fileDel.equals("on")) {
				String fileName = dao.getFileName(c_idx);
				File f = new File(FileUpload.PRO_UPLOAD_PATH + fileName);
				f.delete(); // 파일 삭제
				dto.setFilename("-");
				dto.setFilesize(0);
			}
			
			dao.updateBoard(dto);

			String page = " ";
			if(board_no == 1) {
				page = "/admin/board/notice";				
			} else if(board_no == 2) {
				page = "/admin/board/faq";
			} else if(board_no == 5) {
				page = "/admin/board/contents";
			}
			response.sendRedirect(ctx + page);
		
		} else if (url.indexOf("addBoard") != -1) {
			List<BoardDTO> boardlist = dao.getBoardList();
			request.setAttribute("boardlist", boardlist);
			String page = "/Admin/addBoard.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		
		} else if (url.indexOf("write.do") != -1) {
			
			File uploadDir = new File(FileUpload.UPLOAD_PATH);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			MultipartRequest multi = new MultipartRequest(request, FileUpload.UPLOAD_PATH, FileUpload.MAX_UPLOAD,
					"utf-8", new DefaultFileRenamePolicy());
			
			int board_no = Integer.parseInt(multi.getParameter("board_no"));
			String userid = multi.getParameter("userid");
			int m_idx = dao.getM_idx(userid);
			String subject = multi.getParameter("subject");
			String content = multi.getParameter("content");
			String show = multi.getParameter("show");
			String ip = request.getRemoteAddr();
			if(ip.equalsIgnoreCase("0:0:0:0:0:0:0:1")) { //안써주는것보다 써주는게 좋다.
				InetAddress inetAddress = InetAddress.getLocalHost();
				ip = inetAddress.getHostAddress();
			}
			String filename = " ";	
			int filesize = 0;
			
			try {
				Enumeration files = multi.getFileNames();
				//summernote 때문에 while문을 돌리지 않고 첫번째 첨부파일만 받아온다.
				String file1 = (String) files.nextElement();
				filename = multi.getFilesystemName(file1);
				File f1 = multi.getFile(file1);
				if(f1 != null) {
					filesize = (int)f1.length();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			ContentsDTO dto = new ContentsDTO();
			dto.setBoard_no(board_no);
			dto.setM_idx(m_idx);
			dto.setSubject(subject);
			dto.setContent(content);
			dto.setShow(show);
			dto.setIp(ip);
			
			//파일 첨부를 하지 않을 경우
			if(filename == null || filename.trim().equals("")) { //null값이거나 빈문자열일때
				filename = "-";
			}
			dto.setFilename(filename);
			dto.setFilesize(filesize);
						
			dao.writeBoard(dto);

			String page = " ";
			if(board_no == 1) {
				page = "/admin/board/notice";				
			} else if(board_no == 2) {
				page = "/admin/board/faq";
			} else if(board_no == 5) {
				page = "/admin/board/contents";
			}
			response.sendRedirect(ctx + page);
		
		} else if (url.indexOf("deleteBoard.do") != -1) {
			MultipartRequest multi = new MultipartRequest(request, FileUpload.PRO_UPLOAD_PATH, FileUpload.MAX_UPLOAD,
					"utf-8", new DefaultFileRenamePolicy());

			int c_idx = Integer.parseInt(multi.getParameter("c_idx"));
			int board_no = Integer.parseInt(multi.getParameter("board_no"));

			dao.deleteBoard(c_idx);
			
			String page = " ";
			if(board_no == 1) {
				page = "/admin/board/notice";				
			} else if(board_no == 2) {
				page = "/admin/board/faq";
			} else if(board_no == 5) {
				page = "/admin/board/contents";
			}
			response.sendRedirect(ctx + page);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
