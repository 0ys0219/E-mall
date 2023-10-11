package com.ethan.emall.repository;

import org.springframework.data.repository.CrudRepository;

import com.ethan.emall.model.Member;
import java.util.List;


public interface MemberRepository extends CrudRepository<Member,Integer>{
	
	List<Member> findByAccount(String account);

}
