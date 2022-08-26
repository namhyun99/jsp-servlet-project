package config;

public class FileUpload {
	//파일 업로드되고 관리되는 디렉토리 
	public static final String UPLOAD_PATH = "c:\\work_java\\jsp-servlet-project\\src\\main\\webapp\\upload\\";
	public static final String PRO_UPLOAD_PATH = "c:\\work_java\\jsp-servlet-project\\src\\main\\webapp\\upload\\profile\\";
	//파일 업로드 용량 제한 (10MB)
	public static final int MAX_UPLOAD = 10*1024*1024; 
}
