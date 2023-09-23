package com.ethan.emall.dao.impl;

import com.ethan.emall.dao.MemberDao;
import com.ethan.emall.dto.MemberRegisterRequest;
import com.ethan.emall.model.Member;
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
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Integer createMember(MemberRegisterRequest memberRegisterRequest) {

        String sql = "insert into Member(account, password)" +
                "VALUES(:account, :password)";

        HashMap<String, Object> map = new HashMap<>();
        map.put("account",memberRegisterRequest.getAccount());
        map.put("password",memberRegisterRequest.getPassword());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql,new MapSqlParameterSource(map),keyHolder);

        int memberId = keyHolder.getKey().intValue();

        return memberId;
    }


    public Member getMemberByAccount(String account) {
        String sql = "select id,account,password from Member where account = :account";

        HashMap<String, Object> map = new HashMap<>();
        map.put("account",account);

        List<Member> memberList = namedParameterJdbcTemplate.query(sql,map,new MemberRowMapper());

        if (memberList.size() > 0) {
            return memberList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public Member getMemberById(Integer memberId) {

        String sql = "select id,account,password from Member where id = :memberId";

        HashMap<String, Object> map = new HashMap<>();

        map.put("memberId",memberId);

        List<Member> memberList = namedParameterJdbcTemplate.query(sql, map, new MemberRowMapper());

        if (memberList.size() > 0) {
            return memberList.get(0);
        } else {
            return null;
        }
    }
}
