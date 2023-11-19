package com.ethan.emall.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.web.server.ResponseStatusException;

import com.ethan.emall.dao.MemberDao;
import com.ethan.emall.dto.MemberLoginRequest;
import com.ethan.emall.dto.MemberRegisterRequest;
import com.ethan.emall.model.Member;
import com.ethan.emall.service.MemberService;

@Component
public class MemberServiceImpl implements MemberService {

	private final static Logger log = LoggerFactory.getLogger(MemberServiceImpl.class);

	@Autowired
	private MemberDao memberDao;

	@Override
	public Integer register(MemberRegisterRequest memberRegisterRequest) {
		// 檢查註冊得密碼
		Member member = memberDao.getMemberByAccount(memberRegisterRequest.getAccount());

		if (member != null) {
			log.warn("該 account {} 已經被註冊了", memberRegisterRequest.getAccount());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}

		// 使用 MD5 生成密碼的雜湊值
		String hashedPassword = DigestUtils.md5DigestAsHex(memberRegisterRequest.getPassword().getBytes());
		memberRegisterRequest.setPassword(hashedPassword);

		// 創建帳號
		return memberDao.createMember(memberRegisterRequest);
	}

	@Override
	public Member getMemberById(Integer memberId) {
		return memberDao.getMemberById(memberId);
	}

	@Override
	public Member login(MemberLoginRequest memberLoginRequest, HttpServletRequest request) {
		Member member = memberDao.getMemberByAccount(memberLoginRequest.getAccount());
		// 檢查帳號是否已存在
		if (member == null) {
			log.warn("該帳號 {} 尚未註冊", memberLoginRequest.getAccount());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}

		// 使用 MD5 生成密碼的雜湊值
		String hashedPassword = DigestUtils.md5DigestAsHex(memberLoginRequest.getPassword().getBytes());

		// 檢查密碼是否正確
		if (member.getPassword().equals(hashedPassword)) {
			//這邊要再思考應該放在controller還是serviceImpl
			request.getSession().setAttribute("member", member);
			return member;
		} else {
			log.warn("該帳號 {} 的密碼不正確", memberLoginRequest.getAccount());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}

}
