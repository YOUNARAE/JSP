package kr.or.ddit.mvc.multipart;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * Part -> MultipartFile을 하기 위해서.
 *
 */
public class MultipartHttpServletRequest extends HttpServletRequestWrapper{
	
	private Map<String, List<MultipartFile>> fileMap;

	public MultipartHttpServletRequest(HttpServletRequest request) throws IOException, ServletException {
		super(request);
		//맵을 만들자
		parseRequest(request); //메소드 추가
	}
	
	private void parseRequest(HttpServletRequest request) throws IOException, ServletException {
		fileMap = new LinkedHashMap<>();
		request.getParts().stream()//전제 다 꺼내오기
					.filter((p)->p.getContentType()!=null)//아닌 것 판단
					.forEach((p)->{
						String partName = p.getName();
						MultipartFile file = new StandardServletMultipartFile(p); 
//						fileMap.put(partName, file);
						List<MultipartFile> fileList = Optional.ofNullable(fileMap.get(partName)) //있을 수도 있고 없을 수도 있는 값이라서 
																.orElse(new ArrayList<>()); //비어있어서 어레이 리스트를 하나 만들어줌
						fileList.add(file);
						fileMap.put(partName, fileList);
					}); //모든 파트 데이터가 맵으로 만들어졌다
	}		

	public Map<String, List<MultipartFile>> getFileMap() {
		return fileMap;
	}
	
	public MultipartFile getFile(String name){
		List<MultipartFile> files = fileMap.get(name);
		if(files!=null && !files.isEmpty()) //비어있지 않다면
			return files.get(0);
		else return null;
	}
	
	public List<MultipartFile> getFiles(String name){
		return fileMap.get(name);
	}
	
	public Enumeration<String> getFileNames(){//맵에서 키만 꺼내서 반환할 수 있어야한다
		Iterator<String> names = fileMap.keySet().iterator();
		return new Enumeration<String>() {
			@Override
			public boolean hasMoreElements() {
				return names.hasNext();
			}
			
			@Override
			public String nextElement() {
				return names.next();
			}
		};
	}
}
