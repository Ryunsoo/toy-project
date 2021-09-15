package com.kh.toy.member.model.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kh.toy.common.db.JDBCTemplate;
import com.kh.toy.common.http.HttpConnector;
import com.kh.toy.common.http.RequestParams;
import com.kh.toy.common.mail.MailSender;
import com.kh.toy.member.model.dao.MemberDao;
import com.kh.toy.member.model.dto.Member;

//Service
//어플리케이션의 비지니스 로직을 작성
//사용자의 요청을 컨트롤러로부터 위임받아 해당 요청을 처리하기 위해 필요한 핵심적인 작업을 진행
//작업을 수행하기 위해 데이터베이스에 저장된 데이터가 필요하면 Dao에게 요청
//비지니스 로직을 Service가 담당하기 때문에 Transaction 관리를 Service가 담당
//	commit, rollback을 Service가 담당

//Connection 객체 생성, close 처리
//commit, rollback
//SQLException에 대한 예외처리(rollback)
public class MemberService {

	private MemberDao memberDao = new MemberDao();
	private JDBCTemplate template = JDBCTemplate.getInstance();
	//private RentDao rentDao = new RentDao();
	
	public Member memberAuthenticate(String userId, String password) {
		Connection conn = template.getConnenction();
		Member member = null;
		try {
			member = memberDao.memberAuthenticate(userId, password, conn);
		} finally {
			template.close(conn);
		}
		return member;
	}

	public Member selectMemberById(String userId) {
		Connection conn = template.getConnenction();
		Member member = null;
		
		try {
			member = memberDao.selectMemberById(userId, conn);
		} finally {
			template.close(conn);
		}
		return member;
	}

	public List<Member> selectMemberList() {
		Connection conn = template.getConnenction();
		List<Member> memberList = null;
		
		try {
			memberList = memberDao.selectMemberList(conn);
		} finally {
			template.close(conn);
		}
		return memberList;
	}

	public int insertMember(Member member) {
		Connection conn = template.getConnenction();
		int res = 0;
		
		//회원가입을 진행하고
		try {
			res = memberDao.insertMember(member, conn);
			//회원가입을 성공하면 해당 회원의 정보를 방아와서 Controller 반환
			//Member resMember = memberDao.selectMemberById(member.getUserId(), conn);
			template.commit(conn);
		} catch (Exception e) {
			template.rollback(conn);
			throw e;
		} finally {
			template.close(conn);
		}
		return res;
	}

	public int updatePassword(String userId, String password) {
		Connection conn = template.getConnenction();
		int res = 0;
		
		try {
			res = memberDao.updatePassword(userId, password, conn);
			template.commit(conn);
		} catch (Exception e) {
			res = -1;
			template.rollback(conn);
			e.printStackTrace();
		} finally {
			template.close(conn);
		}
		return res;
	}

	public int deleteMember(String userId) {
		Connection conn = template.getConnenction();
		int res = 0;
		
		try {
			res = memberDao.deleteMember(userId, conn);
			template.commit(conn);
		} catch (Exception e) {
			res = -1;
			template.rollback(conn);
			e.printStackTrace();
		} finally {
			template.close(conn);
		}
		return res;
	}

	public void authenticateByEmail(Member member, String persistToken) {
		MailSender mailSender = new MailSender();
		HttpConnector conn = new HttpConnector();
		
		String queryString = conn.urlEncodedForm(RequestParams.builder()
												.param("mailTemplate", "join-auth-mail")
												.param("userId", member.getUserId())
												.param("persistToken", persistToken).build());
		
		String response = conn.get("http://localhost:9090/mail?" + queryString);
		mailSender.sendEmail(member.getEmail(), "회원가입 축하합니다.", response);
	}

//	public boolean updateMemberGrade() {
//		Connection conn = template.getConnenction();
//		List<Member> memberList = null;
//		Map<String, Integer> bookCntMap = null;
//		
//		try {
//			//모든 회원 조회
//			memberList = memberDao.selectMemberList(conn);
//			bookCntMap = selectBookCntForUpateMemberGrade(memberList, conn);
//			
//			String userId = "";
//			for (Member member : memberList) {
//				userId = member.getUserId();
//				memberDao.updateMemberGrade(userId, bookCntMap.get(userId).intValue(), conn);
//			}
//			template.commit(conn);
//		} catch (Exception e) {
//			template.rollback(conn);
//			e.printStackTrace();
//			return false;
//		} finally {
//			template.close(conn);
//		}
//		return true;
//	}
	
//	private Map<String, Integer> selectBookCntForUpateMemberGrade(List<Member> memberList, Connection conn) {
//		List<Rent> rentList = null;
//		Map<String, Integer> bookCntMap = new HashMap<>();
//		
//		for (Member member : memberList) {
//			int bookCntByMember = 0;
//			int extendedBookCnt = 0;
//			//rent_master의 회원별 대출도서건수
//			rentList = rentDao.selectRentListByRegDate(member.getUserId(), conn);
//			for (Rent rent : rentList) {
//				//rm_idx별 대여한 도서수량
//				bookCntByMember += rent.getRentBookCnt();
//				//rm_idx별 연체한 도서수량
//				extendedBookCnt += rentDao.countExtendedBookByRmIdx(rent.getRmIdx(), conn);
//			}
//			//해당 회원이 연체하지 않고 대여한 도서 수량
//			int notOverduedBookCnt = bookCntByMember - extendedBookCnt;
//			bookCntMap.put(member.getUserId(), notOverduedBookCnt);
//		}
//		return bookCntMap;
//	}

}
