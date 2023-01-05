package kr.or.ddit.mvc.multipart;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

public class StandardServletMultipartFile implements MultipartFile{

	private Part adaptee;
	
	public StandardServletMultipartFile(Part adaptee) {
		super();
		this.adaptee = adaptee;
	}
	
	@Override
	public byte[] getBytes() throws IOException {
//		ByteArrayOutputStream os = new ByteArrayOutputStream(); //바이트 배열에 데이터를 기록한다
//		InputStream is = getInputStream();
//		//기록하는 매체가 꼭 소켓같은 것이 될 필요는 없다
//		os.toByteArray();
		return IOUtils.toByteArray(getInputStream());
	}

	@Override
	public String getContentType() {
		return adaptee.getContentType();
	}

	@Override
	public InputStream getInputStream() throws IOException {
		return adaptee.getInputStream();
	}

	@Override
	public String getName() {
		return adaptee.getName();
	}

	@Override
	public String getOriginalFilename() {
		return adaptee.getSubmittedFileName();
	}

	@Override
	public long getSize() {
		return adaptee.getSize();
	}

	@Override
	public boolean isEmpty() {
		return StringUtils.isBlank(getOriginalFilename()); //adaptee로는 is로 시작하는 게 없음
	}

	@Override
	public void transferTo(File dest) throws IOException {
		adaptee.write(dest.getCanonicalPath()); //File이 우리가 저장하려는 file이다.
	}

}
