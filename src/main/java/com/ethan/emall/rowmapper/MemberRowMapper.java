package com.ethan.emall.rowmapper;

import com.ethan.emall.model.Member;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberRowMapper implements RowMapper<Member> {
    @Override
    public Member mapRow(ResultSet resultSet, int i) throws SQLException {
        Member member = new Member();

        member.setId(resultSet.getInt("Id"));
        member.setAccount(resultSet.getString("Account"));
        member.setPassword(resultSet.getString("Password"));

        return member;
    }
}
