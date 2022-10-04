package controller;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.oreilly.servlet.MultipartRequest;

import util.FileRenamePoicy;
import util.FileUpload;

@WebServlet("/summernoteImageUpload.do")
public class SummernoteImageController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String fileName = "";
		
		try {
			MultipartRequest multi = new MultipartRequest(request, FileUpload.EDITOR_UPLOAD_PATH, 
					FileUpload.MAX_UPLOAD, new FileRenamePoicy());
			
			Enumeration files = multi.getFileNames();			
			String file = (String)files.nextElement();
			fileName = multi.getFilesystemName(file);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		String url = "/editorImage/" + fileName;
		//json 데이터로 변환
		JSONObject jsonData = new JSONObject();
		jsonData.put("url", url);
		response.setContentType("aplication/json");
		response.getWriter().print(jsonData.toJSONString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}

}
