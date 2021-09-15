package com.kh.toy.common.file;

import java.util.List;
import java.util.Map;

public class MultiPartParams {

	private Map<String, List> paramMap;
	
	public MultiPartParams(Map<String, List> paramMap) {
		this.paramMap = paramMap;
	}
	
	public String getParameter(String name) {
		if(name.equals("com.kh.toy.files")) {
			throw new IllegalArgumentException("com.kh.toy.files는 사용할 수 없는 파라미터명입니다.");
		}
		
		return (String) paramMap.get(name).get(0);
	}
	
	public String[] getParameterValues(String name) {
		if(name.equals("com.kh.toy.files")) {
			throw new IllegalArgumentException("com.kh.toy.files는 사용할 수 없는 파라미터명입니다.");
		}
		
		List<String> valueList = paramMap.get(name);
		return valueList.toArray(new String[valueList.size()]);
	}
	
	public List<FileDTO> getFilesInfo() {
		return paramMap.get("com.kh.toy.files");
	}
	
}
