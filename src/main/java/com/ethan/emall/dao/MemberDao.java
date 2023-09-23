package com.ethan.emall.dao;


import com.ethan.emall.dto.MemberRegisterRequest;
import com.ethan.emall.model.Member;

public interface MemberDao {

    Integer createMember(MemberRegisterRequest memberRegisterRequest);

    Member getMemberByAccount(String account);

    Member getMemberById(Integer memberId);
}
