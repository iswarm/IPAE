package com.ssm.sweet.service.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.ssm.sweet.moble.mallMember;
import com.ssm.sweet.sevice.mallMemberService;

public class mallMemberServiceImpl implements mallMemberService{
	
	public JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<mallMember> findAll() {
		List<mallMember> entities =new ArrayList<mallMember>();
		String sql = "select * from mall_member";
		entities = (List<mallMember>)jdbcTemplate.query(sql, new RowMapper(){
			public Object mapRow(ResultSet rs, int rowNum) {
				mallMember member = new mallMember();
				try {
					member.setId(rs.getString("id"));
					member.setUser_name(rs.getString("user_name"));
				} catch (Exception e) {
                    e.printStackTrace();
				}
				return member;
			}
		});
		return entities;
	}
}
