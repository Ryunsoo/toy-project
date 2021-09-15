package com.kh.toy.common.code;

public enum Config {

	//DOMAIN("http://pclass.ga"),
	DOMAIL("http://localhost:9090"),
	COMPANY_EMAIL("babyfox225@gmail.com"),
	SMTP_AUTHENTICATION_ID("babyfox225@gmail.com"),
	SMTP_AUTHENTICATION_PASSWORD("hrs1672^^"),
	UPLOAD_PATH("C:\\CODE\\upload\\");
	
	public final String DESC;
	
	Config(String desc) {
		this.DESC = desc;
	}
	
}
