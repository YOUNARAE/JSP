package kr.or.ddit.vo;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.web.multipart.MultipartFile;

public class FileTestVO {
	private String textParam;
	private String dateParam;
	private MultipartFile file1; //클라이언트와 대화하기 위한 프로퍼티
	private String file1Savename; //여기로 메타 데이터를 호출, 메타데이터 파일을 별도로 관리하기 위하여
	public void file1SaveTo(File saveFolder) throws IllegalStateException, IOException {
		if(file1==null) return;
		file1.transferTo(new File(saveFolder, file1Savename));
	}
	
	private MultipartFile[] file2;
	private List<String> file2Savename; //배열은 데이터베이스에서 가져올 때 왜 안된다고 하셨더라?!!!
	//command Object
	
	public void file2SaveTo(File saveFolder) throws IllegalStateException, IOException {
		//배열이라 여러차례 반복 저장해야함
		if(file2==null || file2.length==0) return;
		//여기로 오면 적어도 1개 이상의 파일이 업로드 됨
		//배열이라 인덱스가 ㅇ벗음
		for(int idx=0; idx<file2.length; idx++) {
			file2[idx].transferTo(new File(saveFolder, file2Savename.get(idx))); //get으로 idx를 넘겨야 같은 위치인 저장명을 사용한다
		}
	}
	
	public String getTextParam() {
		return textParam;
	}
	public void setTextParam(String textParam) {
		this.textParam = textParam;
	}
	public String getdateParam() {
		return dateParam;
	}
	public void setdateParam(String dateParam) {
		this.dateParam = dateParam;
	}
	public MultipartFile getFile1() {
		return file1;
	}
	// 여기에 savename만들
	public void setFile1(MultipartFile file1) {
		if(!file1.isEmpty()) {
			//업로드가 제대로 되었을 때
			this.file1 = file1;
			this.file1Savename = UUID.randomUUID().toString();
		}
		//여기를 안 타면 file1 값은 비어있어서 널값이 뜸
	}
	public MultipartFile[] getFile2() {
		return file2;
	}
	public void setFile2(MultipartFile[] file2) {
		this.file2Savename = Arrays.stream(file2)
								.filter((f)->!f.isEmpty())
								.map((f)-> {
									return UUID.randomUUID().toString();
									//이떄 f는 실제로
								}).collect(Collectors.toList());
		this.file2 = Arrays.stream(file2)
				.filter((f)->!f.isEmpty())
				.toArray(MultipartFile[]::new);
	}
	
	public String getFile1Savename() {
		return file1Savename;
	}
	public void setFile1Savename(String file1Savename) {
		this.file1Savename = file1Savename;
	}
	public List<String> getFile2Savename() {
		return file2Savename;
	}
	public void setFile2Savename(List<String> file2Savename) {
		this.file2Savename = file2Savename;
	}
	
	
}
