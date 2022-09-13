package controller;

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
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import dao.BoardDAO;
import dto.CategoryDTO;
import dto.ContentsCommentDTO;
import dto.ContentsDTO;
import dto.InquiryDTO;
import dto.NoticeDTO;
import util.FileRenamePoicy;
import util.FileUpload;
import util.Pager;

@WebServlet("/board/*")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String uri = request.getRequestURI();
		String ctx = request.getContextPath();
		BoardDAO dao = new BoardDAO();

		if (uri.indexOf("view") != -1) {
			int c_idx = 0;
			if (request.getParameter("c_idx") != null) {
				c_idx = Integer.parseInt(request.getParameter("c_idx"));
			}

			// 중복 조회수 방지 기법
			HttpSession session = request.getSession();
			// 조회수 증가 처리
			dao.plusViewCount(c_idx, session);

			ContentsDTO dto = dao.getDetailContentsView(c_idx);
			List<ContentsDTO> list = dao.getOtherContentList();
			request.setAttribute("dto", dto);
			request.setAttribute("list", list);
			String page = "/contents/view.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);

		} else if (uri.indexOf("commentList.do") != -1) {
			int c_idx = Integer.parseInt(request.getParameter("c_idx"));
			List<ContentsCommentDTO> list = dao.getCommentList(c_idx);
			request.setAttribute("list", list);
			String page = "/comment_list.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);

		} else if (uri.indexOf("addComment.do") != -1) {
			int c_idx = Integer.parseInt(request.getParameter("c_idx"));
			String writer = request.getParameter("writer");
			String content = request.getParameter("content");
			ContentsCommentDTO dto = new ContentsCommentDTO();
			dto.setC_idx(c_idx);
			dto.setWriter(writer);
			dto.setContent(content);
			dao.addComment(dto);

		} else if (uri.indexOf("deleteComment.do") != -1) {
			int cmt_idx = Integer.parseInt(request.getParameter("cmt_idx"));
			dao.deleteComment(cmt_idx);

		} else if (uri.indexOf("write") != -1) {
			List<CategoryDTO> cateList = dao.getCateName();
			request.setAttribute("cateList", cateList);
			String page = "/contents/write.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);

		} else if (uri.indexOf("insertContents.do") != -1) {
			File uploadDir = new File(FileUpload.UPLOAD_PATH);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			MultipartRequest multi = new MultipartRequest(request, FileUpload.UPLOAD_PATH, FileUpload.MAX_UPLOAD,
					"utf-8", new FileRenamePoicy());

			String userid = multi.getParameter("userid");
			int m_idx = dao.getM_idx(userid);
			int cate_no = Integer.parseInt(multi.getParameter("cate_no"));
			String subject = multi.getParameter("subject");
			String content = multi.getParameter("content");
			String show = multi.getParameter("show");
			String ip = request.getRemoteAddr();
			if (ip.equalsIgnoreCase("0:0:0:0:0:0:0:1")) { // 안써주는것보다 써주는게 좋다.
				InetAddress inetAddress = InetAddress.getLocalHost();
				ip = inetAddress.getHostAddress();
			}
			String filename = " ";
			int filesize = 0;

			try {
				Enumeration files = multi.getFileNames();
				// summernote 때문에 while문을 돌리지 않고 첫번째 첨부파일만 받아온다.
				String file1 = (String) files.nextElement();
				filename = multi.getFilesystemName(file1);
				File f1 = multi.getFile(file1);
				if (f1 != null) {
					filesize = (int) f1.length();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			ContentsDTO dto = new ContentsDTO();
			dto.setM_idx(m_idx);
			dto.setCate_no(cate_no);
			dto.setSubject(subject);
			dto.setContent(content);
			dto.setShow(show);
			dto.setIp(ip);

			// 파일 첨부를 하지 않을 경우
			if (filename == null || filename.trim().equals("")) { // null값이거나 빈문자열일때
				filename = "-";
			}
			dto.setFilename(filename);
			dto.setFilesize(filesize);

			dao.insertContents(dto);

			String page = "/main";
			response.sendRedirect(ctx + page);

		} else if (uri.indexOf("deleteContents.do") != -1) {

			int c_idx = Integer.parseInt(request.getParameter("c_idx"));
			// 썸네일 이미지 삭제
			String filename = dao.getFileName(c_idx);
			File f = new File(FileUpload.UPLOAD_PATH + filename);
			f.delete();

			int result = dao.deleteContents(c_idx);
			if (result > 0) {
				response.getWriter().write("true");
			} else {
				response.getWriter().write("false");
			}

		} else if (uri.indexOf("edit") != -1) {
			int c_idx = Integer.parseInt(request.getParameter("c_idx"));
			ContentsDTO dto = dao.getDetailContentsView(c_idx);
			List<CategoryDTO> cateList = dao.getCateName();
			request.setAttribute("dto", dto);
			request.setAttribute("cateList", cateList);
			String page = "/contents/edit.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);

		} else if (uri.indexOf("updateContents.do") != -1) {
			File uploadDir = new File(FileUpload.UPLOAD_PATH);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			MultipartRequest multi = new MultipartRequest(request, FileUpload.UPLOAD_PATH, FileUpload.MAX_UPLOAD,
					"utf-8", new FileRenamePoicy());

			int c_idx = Integer.parseInt(multi.getParameter("c_idx"));
			int cate_no = Integer.parseInt(multi.getParameter("cate_no"));
			String subject = multi.getParameter("subject");
			String content = multi.getParameter("content");
			String show = multi.getParameter("show");
			String ip = request.getRemoteAddr();
			if (ip.equalsIgnoreCase("0:0:0:0:0:0:0:1")) { // 안써주는것보다 써주는게 좋다.
				InetAddress inetAddress = InetAddress.getLocalHost();
				ip = inetAddress.getHostAddress();
			}
			String filename = " ";
			int filesize = 0;

			try {
				Enumeration files = multi.getFileNames();
				// summernote 때문에 while문을 돌리지 않고 첫번째 첨부파일만 받아온다.
				String file1 = (String) files.nextElement();
				filename = multi.getFilesystemName(file1);
				File f1 = multi.getFile(file1);
				if (f1 != null) {
					filesize = (int) f1.length();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			ContentsDTO dto = new ContentsDTO();
			dto.setC_idx(c_idx);
			dto.setCate_no(cate_no);
			dto.setSubject(subject);
			dto.setContent(content);
			dto.setShow(show);
			dto.setUpdate_date(new Date());
			dto.setIp(ip);

			if (filename == null || filename.trim().equals("")) {
				// 새로운 썸네일이 없을때
				ContentsDTO dto2 = dao.getDetailContentsView(c_idx);
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
				File f = new File(FileUpload.UPLOAD_PATH + fileName);
				f.delete(); // 파일 삭제
				dto.setFilename("-");
				dto.setFilesize(0);
			}

			dao.updateContents(dto);
			String page = "/main";
			response.sendRedirect(ctx + page);

		} else if (uri.indexOf("notice") != -1) {

			if (request.getParameter("f_idx") != null) {
				int f_idx = Integer.parseInt(request.getParameter("f_idx"));

				HttpSession session = request.getSession();
				dao.plusViewCountNotice(f_idx, session);

				NoticeDTO dto = dao.getDetailNoticeView(f_idx);
				request.setAttribute("dto", dto);
				String page = "/notice/view.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(page);
				rd.forward(request, response);

			} else {
				String order = request.getParameter("order");
				String searchkey = request.getParameter("searchkey");
				String keyword = request.getParameter("keyword");

				if (keyword == null)
					keyword = "";
				if (searchkey == null)
					searchkey = "title";
				if (order == null)
					order = "write_date";

				int count = dao.getNoticeCount(searchkey, keyword);

				int curPage = 1;
				if (request.getParameter("page") != null) {
					curPage = Integer.parseInt(request.getParameter("page"));
				}
				Pager pager = new Pager(count, curPage);
				int start = pager.getPageBegin();
				int end = pager.getPageEnd();

				List<NoticeDTO> list = dao.getNoticeList(start, end, order, searchkey, keyword);

				request.setAttribute("list", list);
				request.setAttribute("page", pager);
				request.setAttribute("count", count);
				request.setAttribute("order", order);
				request.setAttribute("searchkey", searchkey);
				request.setAttribute("keyword", keyword);

				String page = "/notice/list.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(page);
				rd.forward(request, response);
			}

		} else if (uri.indexOf("inquiry") != -1) {

			if (request.getParameter("i_idx") != null) {
				int i_idx = Integer.parseInt(request.getParameter("i_idx"));

				HttpSession session = request.getSession();
				dao.plusViewCountInquiry(i_idx, session);

				InquiryDTO dto = dao.getDetailInquiryView(i_idx);
				request.setAttribute("dto", dto);
				String page = "/inquiry/view.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(page);
				rd.forward(request, response);

			} else {
				String order = request.getParameter("order");
				String searchkey = request.getParameter("searchkey");
				String keyword = request.getParameter("keyword");

				if (keyword == null)
					keyword = "";
				if (searchkey == null)
					searchkey = "title";
				if (order == null)
					order = "write_date";

				int count = dao.getInquiryCount(searchkey, keyword);

				int curPage = 1;
				if (request.getParameter("page") != null) {
					curPage = Integer.parseInt(request.getParameter("page"));
				}
				Pager pager = new Pager(count, curPage);
				int start = pager.getPageBegin();
				int end = pager.getPageEnd();

				List<InquiryDTO> list = dao.getInquiryList(start, end, order, searchkey, keyword);

				request.setAttribute("list", list);
				request.setAttribute("page", pager);
				request.setAttribute("count", count);
				request.setAttribute("order", order);
				request.setAttribute("searchkey", searchkey);
				request.setAttribute("keyword", keyword);

				String page = "/inquiry/list.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(page);
				rd.forward(request, response);
			}

		} else if(uri.indexOf("questions") != -1) {
			String page = "/inquiry/write.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		
		} else if(uri.indexOf("insertInquiry.do") != -1) {
			
			String userid = request.getParameter("userid");
			int m_idx = dao.getM_idx(userid);
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String show = request.getParameter("show");
			
			InquiryDTO dto = new InquiryDTO();
			dto.setM_idx(m_idx);
			dto.setTitle(title);
			dto.setContent(content);
			dto.setShow(show);
			
			dao.insertInquiry(dto);
			
			String page = "/board/inquiry";
			response.sendRedirect(ctx + page);
			
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
