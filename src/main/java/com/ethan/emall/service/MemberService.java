package com.ethan.emall.service;


import com.ethan.emall.dto.MemberLoginRequest;
import com.ethan.emall.dto.MemberRegisterRequest;
import com.ethan.emall.model.Member;

public interface MemberService {

    Integer register(MemberRegisterRequest memberRegisterRequest);

    Member getMemberById(Integer memberId);

    Member login(MemberLoginRequest memberLoginRequest);
}
