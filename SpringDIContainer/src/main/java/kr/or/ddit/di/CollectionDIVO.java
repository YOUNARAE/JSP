package kr.or.ddit.di;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CollectionDIVO {
	private List<?> sampleList; //엘리먼트 타입에는 제한을 두지 않은 List
	private Set<String> sampleSet; //Set은 String으로 제한
	private Map<String, ?> sampleMap;
	
	private Properties props;
	
	private String[] array;
} 
