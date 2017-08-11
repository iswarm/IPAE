package com.ssm.sweet.service.impl.user;

/**
 * Created by Administrator on 2017/6/20.
 */
import com.ssm.sweet.moble.user.User;
import com.ssm.sweet.sevice.user.newUserService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.stellar.sdk.KeyPair;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class newUserServiceImpl implements newUserService {

    public JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<User> insertUser() {
        KeyPair keyPair = KeyPair.random();
        List<User> entities =new ArrayList<User>();
        String sql = "select * from user_info";
        entities = (List<User>)jdbcTemplate.query(sql, new RowMapper(){
            public Object mapRow(ResultSet rs, int rowNum) {
                User user = new User();
                try {
                    user.setUser_id(rs.getString("user_id"));
                    user.setUser_name(rs.getString("user_name"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return user;
            }
        });
        return entities;
    }





}
