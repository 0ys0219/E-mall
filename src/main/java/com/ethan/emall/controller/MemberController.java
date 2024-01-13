package com.ethan.emall.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ethan.emall.dto.MemberLoginRequest;
import com.ethan.emall.dto.MemberRegisterRequest;
import com.ethan.emall.model.Member;
import com.ethan.emall.service.MemberService;

@RestController
public class MemberController {

	@Autowired
	private MemberService memberService;

	@PostMapping("/members/register")
	public ResponseEntity<Member> register(@RequestBody @Valid MemberRegisterRequest memberRegisterRequest) {
		Integer memberId = memberService.register(memberRegisterRequest);

		Member member = memberService.getMemberById(memberId);

		return ResponseEntity.status(HttpStatus.CREATED).body(member);
	}

	@PostMapping("/members/login")
	public ResponseEntity<Member> login(@RequestBody @Valid MemberLoginRequest memberLoginRequest,
			HttpServletRequest request) {
		Member member = memberService.login(memberLoginRequest, request);

		return ResponseEntity.status(HttpStatus.OK).body(member);
	}
	
	@PostMapping("/members/logout")
	public void logout(HttpServletRequest request) {
		System.out.println("準備執行session失效"+request.getSession().getId());
		HttpSession session = request.getSession();
		session.invalidate();
		System.out.println("session失效"+request.getSession().getId());
	}

}
