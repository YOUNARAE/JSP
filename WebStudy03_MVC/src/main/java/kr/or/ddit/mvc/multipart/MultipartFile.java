package kr.or.ddit.mvc.multipart;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public interface MultipartFile {
	
	public byte[] getBytes() throws IOException; //업로드가 된 파일을 이진데이터로 쪼개려면 io작업 필요함
	public String getContentType();
	public InputStream getInputStream() throws IOException;
	public String getName();
	public String getOriginalFilename();
	public long getSize();
	public boolean isEmpty();
	public void transferTo(File dest) throws IOException; //write에서는 저장명만 받는데 여기는 파일명을 받는다

}
