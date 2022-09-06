package util;

import java.io.File;
import java.util.UUID;

public class FileUpload {
	//파일 업로드되고 관리되는 디렉토리 
	public static final String UPLOAD_PATH = "c:\\work_java\\jsp-servlet-project\\src\\main\\webapp\\upload\\content\\";
	public static final String PRO_UPLOAD_PATH = "c:\\work_java\\jsp-servlet-project\\src\\main\\webapp\\upload\\profile\\";
	//파일 업로드 용량 제한 (10MB)
	public static final int MAX_UPLOAD = 10*1024*1024; 
		
	
	
	public String OrignalFileName(File f) {
		String filename = f.getName();
		
		return filename;
	}

	
	public File CreateFileName(File f) {
		String orignalFileName = OrignalFileName(f);
		String ext = "";
		int dot = orignalFileName.lastIndexOf(".");
		if(dot != -1) {
			ext = orignalFileName.substring(dot);
		} else {
			ext = "";
		}
		
		String saveFileName = UUID.randomUUID() + ext;
		File renameFile = new File(f.getParent(), saveFileName);
		
		return renameFile;
	}
}
