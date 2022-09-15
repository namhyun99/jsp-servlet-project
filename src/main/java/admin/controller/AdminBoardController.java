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

import admin.dao.ContentsDAO;
import admin.dao.InquiryDAO;
import admin.dao.NoticeDAO;
import dto.CategoryDTO;
import dto.ContentsDTO;
import dto.InquiryDTO;
import dto.NoticeDTO;
import util.FileRenamePoicy;
import util.FileUpload;
import util.Pager;

@WebServlet("/admin/board/*")
public class AdminBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = request.getRequestURL().toString();
		String ctx = request.getContextPath();
		ContentsDAO c_dao = new ContentsDAO();
		NoticeDAO n_dao = new NoticeDAO();
		InquiryDAO i_dao = new InquiryDAO();
		
		//컨텐츠 리스트 화면
		if (url.indexOf("contents") != -1) {
			String order = request.getParameter("order");
			String searchkey = request.getParameter("searchkey");
			String keyword = request.getParameter("keyword");

			if (keyword == null)
				keyword = "";
			if (searchkey == null)
				searchkey = "all";
			if (order == null)
				order = "write_date";

			int count = c_dao.getContestsCount(searchkey, keyword);

			int curPage = 1;
			if (request.getParameter("page") != null) {
				curPage = Integer.parseInt(request.getParameter("page"));
			}
			Pager pager = new Pager(count, curPage);
			int start = pager.getPageBegin();
			int end = pager.getPageEnd();

			List<ContentsDTO> list = c_dao.getContentsList(start, end, order, searchkey, keyword);

			request.setAttribute("list", list);
			request.setAttribute("page", pager);
			request.setAttribute("count", count);
			request.setAttribute("order", order);
			request.setAttribute("searchkey", searchkey);
			request.setAttribute("keyword", keyword);

			String page = "/Admin/contentsList.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		
		//컨텐츠 수정화면
		} else if(url.indexOf("editContents") != -1) {
			int c_idx = Integer.parseInt(request.getParameter("c_idx"));
			
			ContentsDTO dto = c_dao.detailContentsVeiw(c_idx);
			List<CategoryDTO> cateList = c_dao.getCateName();
			request.setAttribute("dto", dto);
			request.setAttribute("cateList", cateList);
			String page = "/Admin/editContents.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		
		//컨텐츠 수정하기
		} else if(url.indexOf("updateContents.do") != -1) {
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
			dto.setCate_no(cate_no);
			dto.setSubject(subject);
			dto.setContent(content);
			dto.setShow(show);
			dto.setUpdate_date(new Date());
			dto.setIp(ip);
			
			if (filename == null || filename.trim().equals("")) {
				// 새로운 썸네일이 없을때
				ContentsDTO dto2 = c_dao.detailContentsVeiw(c_idx);
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
				String fileName = c_dao.getFileName(c_idx);
				File f = new File(FileUpload.UPLOAD_PATH + fileName);
				f.delete(); // 파일 삭제
				dto.setFilename("-");
				dto.setFilesize(0);
			}
			
			c_dao.updateContents(dto);
			String page = "/admin/board/contents";
			response.sendRedirect(ctx + page);
		
		//컨텐츠 작성화면
		} else if (url.indexOf("addContents") != -1) {
			List<CategoryDTO> cateList = c_dao.getCateName();
			request.setAttribute("cateList", cateList);
			
			String page = "/Admin/addContents.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		
		//컨텐츠 작성하기
		} else if (url.indexOf("writeContents.do") != -1) {
			
			File uploadDir = new File(FileUpload.UPLOAD_PATH);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			MultipartRequest multi = new MultipartRequest(request, FileUpload.UPLOAD_PATH, FileUpload.MAX_UPLOAD,
					"utf-8", new FileRenamePoicy());
			
			String userid = multi.getParameter("userid");
			int m_idx = c_dao.getM_idx(userid);
			int cate_no = Integer.parseInt(multi.getParameter("cate_no"));
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
			dto.setM_idx(m_idx);
			dto.setCate_no(cate_no);
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
						
			c_dao.writeContents(dto);

			String page = "/admin/board/contents";
			response.sendRedirect(ctx + page);
		
		//컨텐츠 삭제하기
		} else if (url.indexOf("deleteContents.do") != -1) {
			MultipartRequest multi = new MultipartRequest(request, FileUpload.UPLOAD_PATH, FileUpload.MAX_UPLOAD,
					"utf-8", new FileRenamePoicy());

			int c_idx = Integer.parseInt(multi.getParameter("c_idx"));
			//썸네일 이미지 삭제
			String filename = c_dao.getFileName(c_idx);
			File f = new File(FileUpload.UPLOAD_PATH + filename);
			f.delete();
					
			c_dao.deleteContents(c_idx);
			
			String page = "/admin/board/contents";
			response.sendRedirect(ctx + page);
			
		//공지사항 화면
		} else if (url.indexOf("notice") != -1) {
			String order = "write_date";
			String keyword = request.getParameter("keyword");
			if (keyword == null)keyword = "";

			int count = n_dao.getNoticeCount(keyword);

			int curPage = 1;
			if (request.getParameter("page") != null) {
				curPage = Integer.parseInt(request.getParameter("page"));
			}
			Pager pager = new Pager(count, curPage);
			int start = pager.getPageBegin();
			int end = pager.getPageEnd();

			List<NoticeDTO> list = n_dao.getNoticeList(start, end, order, keyword);

			request.setAttribute("list", list);
			request.setAttribute("page", pager);
			request.setAttribute("count", count);
			request.setAttribute("order", order);
			request.setAttribute("keyword", keyword);

			String page = "/Admin/noticeList.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		
		//공지사항 수정화면
		} else if(url.indexOf("editNotice") != -1) {
			int f_idx = Integer.parseInt(request.getParameter("f_idx"));
			NoticeDTO dto = n_dao.detailNoticeVeiw(f_idx);
			request.setAttribute("dto", dto);
			String page = "/Admin/editNotice.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		
		//공지사항 수정하기
		} else if(url.indexOf("updateNotice.do") != -1) {
						
			int f_idx = Integer.parseInt(request.getParameter("f_idx"));
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			NoticeDTO dto = new NoticeDTO();
			dto.setF_idx(f_idx);
			dto.setTitle(title);
			dto.setContent(content);
			dto.setUpdate_date(new Date());
			
			n_dao.updateNotice(dto);
			
			String page = "/admin/board/notice";
			response.sendRedirect(ctx + page);
		
		//공지사항 작성화면
		} else if (url.indexOf("addNotice") != -1) {
			
			String page = "/Admin/addNotice.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		
		//공지사항 작성하기
		} else if (url.indexOf("writeNotice.do") != -1) {
			
			String userid = request.getParameter("userid");
			int m_idx = n_dao.getM_idx(userid);
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			NoticeDTO dto = new NoticeDTO();
			dto.setM_idx(m_idx);
			dto.setTitle(title);
			dto.setContent(content);
			
			n_dao.writeNotice(dto);
			String page = "/admin/board/notice";
			response.sendRedirect(ctx + page);
		
		//공지사항 삭제하기
		} else if (url.indexOf("deleteNotice.do") != -1) {
			int f_idx = Integer.parseInt(request.getParameter("f_idx"));
			n_dao.deleteNotice(f_idx);
			String page = "/admin/board/notice";
			response.sendRedirect(ctx + page);
			
		//1대1 문의 리스트
		} else if (url.indexOf("inquiry") != -1) {
			String order = request.getParameter("order");
			String keyword = request.getParameter("keyword");

			if (keyword == null) keyword = "";
			if (order == null) order = "all";
			
			System.out.println(keyword);
			System.out.println(order);

			int count = i_dao.getInquiryCount(order, keyword);

			int curPage = 1;
			if (request.getParameter("page") != null) {
				curPage = Integer.parseInt(request.getParameter("page"));
			}
			Pager pager = new Pager(count, curPage);
			int start = pager.getPageBegin();
			int end = pager.getPageEnd();

			List<InquiryDTO> list = i_dao.getInquiryList(start, end, order, keyword);

			request.setAttribute("list", list);
			request.setAttribute("page", pager);
			request.setAttribute("count", count);
			request.setAttribute("order", order);
			request.setAttribute("keyword", keyword);

			String page = "/Admin/inquiryList.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		
		//1대1 문의 보기화면
		} else if(url.indexOf("viewInquiry") != -1) {
			int i_idx = Integer.parseInt(request.getParameter("i_idx"));
			InquiryDTO dto = i_dao.detailInquiryVeiw(i_idx);
			request.setAttribute("dto", dto);
			String page = "/Admin/viewInquiry.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		
		//1대1 문의 답변화면
		} else if(url.indexOf("replyInquiry") != -1) {
			int i_idx = Integer.parseInt(request.getParameter("i_idx"));
			InquiryDTO dto = i_dao.detailInquiryVeiw(i_idx);
			dto.setContent("==== 문의글의 내용 ==== \n <br> " + dto.getContent());
			request.setAttribute("dto", dto);
			String page = "/Admin/replyInquiry.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		//1대1문의 답변 쓰기
		} else if(url.indexOf("writeReply.do") != -1) {
			int i_idx = Integer.parseInt(request.getParameter("i_idx"));
			InquiryDTO dto = i_dao.detailInquiryVeiw(i_idx);
			int ref = dto.getRef();
			int re_step = dto.getRe_step()+1;
			int re_level = dto.getRe_level()+1;
			String userid = request.getParameter("userid");
			int m_idx = i_dao.getM_idx(userid);
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String complete = "y";
			
			dto.setM_idx(m_idx);
			dto.setTitle(title);
			dto.setContent(content);
			dto.setComplete(complete);
			dto.setRef(ref);
			dto.setRe_step(re_step);
			dto.setRe_level(re_level);
			
			i_dao.updateStep(ref, re_step); 
			i_dao.writeReply(dto);
			i_dao.updateComplete(i_idx, complete);
			
			String page = "/admin/board/inquiry";
			response.sendRedirect(ctx + page);
			
		//1대1문의 삭제하기
		} else if (url.indexOf("deleteInquiry.do") != -1) {
			int i_idx = Integer.parseInt(request.getParameter("i_idx"));
			i_dao.deleteInquiry(i_idx);
			String page = "/admin/board/inquiry";
			response.sendRedirect(ctx + page);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
