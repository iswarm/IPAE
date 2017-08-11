package com.ssm.sweet.sevice;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.ssm.sweet.dao.mallMemberDao;
import com.ssm.sweet.moble.mallMember;

@Service
public interface mallMemberService {
	
	public List<mallMember> findAll(); 
	
/*     封装一个JdbcTemplate的模板对象  
	@Autowired
    private JdbcTemplate jdbcTemplate;  
  
     通过set方法注入进来即可   
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {  
        this.jdbcTemplate = jdbcTemplate;  
    }  

	public List<mallMember> findmallMemberAll() {
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
	}*/

}
