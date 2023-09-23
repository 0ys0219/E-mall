package com.ethan.emall.service.impl;

import com.ethan.emall.dao.MemberDao;
import com.ethan.emall.dto.MemberRegisterRequest;
import com.ethan.emall.model.Member;
import com.ethan.emall.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class MemberServiceImpl implements MemberService {

    private final static Logger log = LoggerFactory.getLogger(MemberServiceImpl.class);

    @Autowired
    private MemberDao memberDao;

    @Override
    public Integer register(MemberRegisterRequest memberRegisterRequest) {

        Member member = memberDao.getMemberByAccount(memberRegisterRequest.getAccount());

        if (member != null) {
            log.warn("該 account {} 已經被註冊了",memberRegisterRequest.getAccount());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        return memberDao.createMember(memberRegisterRequest);
    }

    @Override
    public Member getMemberById(Integer memberId){
        return memberDao.getMemberById(memberId);
    }
}
