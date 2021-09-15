package com.kh.toy.member.model.dto;

import java.sql.Date;

//DTO(Data Transfer Object)
//데이터 전송 객체
//데이터베이스로부터 얻어온 데이터를 service(비지니스로직)으로 보내기 위해 사용하는 객체
//비지니스 로직을 포함하고 있지 않은, 순수하게 데이터 전송만을 위해 사용되는 객체
//getter/setter, equals, hashCode, toString 메서드만을 가진다.

//*** 참고
//DTO와 비슷한 친구들
//Domain Object, Value Object(VO), Entity, Bean

//DTO의 조건(java bean 규약)
//1. 모든 필드변수는 private 처리
//2. 모든 필드변수는 getter/setter 메서드를 가져야한다.
//3. 반드시 기본 생성자가 존재할 것(매개변수가 있는 생성자가 있더라도, 기본 생성자가 있어야한다.)

//오라클 - 자바 타입 매핑
//CHAR VARCHAR2 -> String
//DATE -> java.util.Date, java.sql.Date
//NUMBER -> int, double
public class Member {
	
	private String userId;
	private String password;
	private String email;
	private String grade;
	private String tell;
	private Date regDate;
	private Date rentableDate;
	private int isLeave;
	
	public Member() {
		// TODO Auto-generated constructor stub
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getTell() {
		return tell;
	}

	public void setTell(String tell) {
		this.tell = tell;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public Date getRentableDate() {
		return rentableDate;
	}

	public void setRentableDate(Date rentableDate) {
		this.rentableDate = rentableDate;
	}

	public int getIsLeave() {
		return isLeave;
	}

	public void setIsLeave(int isLeave) {
		this.isLeave = isLeave;
	}

	@Override
	public String toString() {
		return "Member [userId=" + userId + ", password=" + password + ", email=" + email + ", grade=" + grade
				+ ", tell=" + tell + ", regDate=" + regDate + ", rentableDate=" + rentableDate + ", isLeave=" + isLeave
				+ "]";
	}
	
}
