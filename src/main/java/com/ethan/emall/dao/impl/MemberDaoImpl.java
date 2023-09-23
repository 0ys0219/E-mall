package com.ethan.emall.dao.impl;

import com.ethan.emall.dao.MemberDao;
import com.ethan.emall.dto.MemberRegisterRequest;
import com.ethan.emall.model.Member;
import com.ethan.emall.rowmapper.MemberRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MemberDaoImpl implements MemberDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Integer createMember(MemberRegisterRequest memberRegisterRequest) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);

        HashMap<String, Object> mapIn = new HashMap<>();
        mapIn.put("in_account", memberRegisterRequest.getAccount());
        mapIn.put("in_password", memberRegisterRequest.getPassword());

        SqlParameterSource in = new MapSqlParameterSource(mapIn);
        Map<String, Object> mapOut = simpleJdbcCall.withProcedureName("createMember").execute(in);

        int memberId = Integer.parseInt(String.valueOf(mapOut.get("out_memberId")));

        return memberId;
    }


    public Member getMemberByAccount(String account) {
        String sql = "call getMemberByAccount(:account)";

        HashMap<String, Object> map = new HashMap<>();
        map.put("account", account);

        List<Member> memberList = namedParameterJdbcTemplate.query(sql, map, new MemberRowMapper());

        if (memberList.size() > 0) {
            return memberList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public Member getMemberById(Integer memberId) {

        String sql = "call getMemberById(:memberId)";

        HashMap<String, Object> map = new HashMap<>();

        map.put("memberId", memberId);

        List<Member> memberList = namedParameterJdbcTemplate.query(sql, map, new MemberRowMapper());

        if (memberList.size() > 0) {
            return memberList.get(0);
        } else {
            return null;
        }
    }
}
