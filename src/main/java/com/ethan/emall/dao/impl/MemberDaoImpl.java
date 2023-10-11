package com.ethan.emall.dao.impl;

import com.ethan.emall.dao.MemberDao;
import com.ethan.emall.dto.MemberRegisterRequest;
import com.ethan.emall.model.Member;
import com.ethan.emall.repository.MemberRepository;
import com.ethan.emall.rowmapper.MemberRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class MemberDaoImpl implements MemberDao {
    
    @Autowired
    private MemberRepository memberRepository;

    @Override
    public Integer createMember(MemberRegisterRequest memberRegisterRequest) {

    	Member member = new Member();
    	member.setAccount(memberRegisterRequest.getAccount());
    	member.setPassword(memberRegisterRequest.getPassword());
    	memberRepository.save(member);
    	Integer memberId = member.getId();
    	System.out.println(member.getId());
        return memberId;
    }


    public Member getMemberByAccount(String account) {
    	List<Member> memberList = memberRepository.findByAccount(account);
    	
    	if (memberList.size() > 0) {
    		return memberList.get(0);
    	} else {
    		return null;
    	}
    	   	
    }

    @Override
    public Member getMemberById(Integer memberId) {

    	Member member = memberRepository.findById(memberId).orElse(null);
    	
    	return member;
    }
}
