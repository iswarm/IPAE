package com.ssm.sweet.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.ssm.sweet.moble.mallMember;

public interface mallMemberDao {
	
	public List<mallMember> findmallMemberAll();
}
