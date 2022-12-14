package util;

import java.io.File;
import java.util.UUID;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class FileRenamePoicy implements FileRenamePolicy{
	@Override
	public File rename(File f) {
		String name = f.getName(); //업로드 파일명
		String ext = ""; //확장자
		
		int dot = name.lastIndexOf("."); //마지막 인덱스 값에 . 이없으면 -1
		if(dot != -1) { 
			ext = name.substring(dot);
		} else {
			ext = "";
		}
		String filename =  UUID.randomUUID()+ext;
		File renameFile = new File(f.getParent(), filename);
		
		return renameFile;
	}
}
