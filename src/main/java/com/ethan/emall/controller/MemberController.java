package com.ethan.emall.controller;

import com.ethan.emall.dto.MemberLoginRequest;
import com.ethan.emall.dto.MemberRegisterRequest;
import com.ethan.emall.model.Member;
import com.ethan.emall.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
    public ResponseEntity<Member> login(@RequestBody @Valid MemberLoginRequest memberLoginRequest) {
        Member member = memberService.login(memberLoginRequest);

        return ResponseEntity.status(HttpStatus.OK).body(member);
    }

}
