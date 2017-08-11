package com.xin.serviceimpl;

import com.xin.moble.mallMember;
import com.xin.sevice.mallMemberService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class mallMemberServiceImpl implements mallMemberService {
	
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
					System.out.println("id="+rs.getString("id"));
					member.setUser_name(rs.getString("user_name"));
					System.out.println("user_name="+rs.getString("user_name"));
				} catch (Exception e) {
                    e.printStackTrace();
				}
				return member;
			}
		});
		for(mallMember attribute : entities) {
			System.out.println(attribute);
		}
		System.out.print(entities+"进了service");
		return entities;
	}
}
