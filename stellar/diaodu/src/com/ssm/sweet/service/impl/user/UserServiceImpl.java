package com.ssm.sweet.service.impl.user;

/**
 * Created by Administrator on 2017/6/20.
 */
import com.ssm.sweet.moble.user.User;
import com.ssm.sweet.sevice.user.UserService;
import net.sf.json.JSONObject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    public JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<User> queryUser() {
        List<User> entities =new ArrayList<User>();
        String sql = "select * from user_info";
        entities = (List<User>)jdbcTemplate.query(sql, new RowMapper(){
            public Object mapRow(ResultSet rs, int rowNum) {
                User user = new User();
                try {
                    user.setUser_id(rs.getInt("user_id"));
                    user.setAmmeter_no(rs.getString("ammeter_no"));
                    user.setEle_no(rs.getString("ele_no"));
                    user.setUser_name(rs.getString("user_name"));
                    user.setElectric_quantity(rs.getString("electric_quantity"));
                    user.setLon(rs.getString("lon"));
                    user.setEnergy_type(rs.getInt("energy_type"));
                    user.setFile_id(rs.getString("file_id"));
                    user.setCard_no(rs.getString("card_no"));
                    user.setPhone_no(rs.getString("phone_no"));
                    user.setDispatch_area(rs.getString("dispatch_area"));
                    user.setLat(rs.getString("lat"));
                    user.setUser_pukey(rs.getString("user_pukey"));
                    user.setUser_type(rs.getInt("user_type"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return user;
            }
        });
        return entities;
    }

    @Override
    public int insertUser(final JSONObject jsonObject) {


        int in=0;
        //String sql = "select * from user_info";
        in = jdbcTemplate.update(new PreparedStatementCreator(){
                                     public PreparedStatement createPreparedStatement(Connection conn){
                                         System.out.print("0101010101");
                                         String usertype = jsonObject.getString("user_type");
                                         String energytype = jsonObject.getString("energy_type");
                                         int a=0;
                                         int b=0;
                                         if("yd".equals(usertype)){
                                             a=1;
                                         }

                                         if("FENG".equals(energytype)){
                                             b=1;
                                         }else if("GU".equals(energytype)){
                                             b=2;
                                         }else if("PING".equals(energytype)){
                                             b=3;
                                         }else if("DAIBI".equals(energytype)){
                                             b=4;
                                         }
                                         String sql="insert into user_info (user_name,user_type,card_no,phone_no,lon,lat,file_id,electric_quantity,energy_type,dispatch_area,ammeter_no,ele_no,user_pukey) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
                                         PreparedStatement ps= null;
                                         System.out.print("0101010101");
                                         try {
                                             ps = conn.prepareStatement(sql);
                                             ps.setString(1,jsonObject.getString("user_name"));
                                             ps.setInt(2,a);
                                             ps.setString(3,jsonObject.getString("card_no"));
                                             ps.setString(4,jsonObject.getString("phone_no"));
                                             ps.setString(5,jsonObject.getString("lon"));
                                             ps.setString(6,jsonObject.getString("lat"));
                                             ps.setString(7,jsonObject.getString("file_id"));
                                             ps.setString(8,jsonObject.getString("electric_quantity"));
                                             ps.setInt(9,b);
                                             ps.setString(10,jsonObject.getString("dispatch_area"));
                                             ps.setString(11,jsonObject.getString("ammeter_no"));
                                             ps.setString(12,jsonObject.getString("Ele_no"));
                                             ps.setString(13,jsonObject.getString("user_pukey"));
                                             System.out.println("插入SQL---->"+sql);
                                         } catch (SQLException e) {
                                             e.printStackTrace();
                                         }

                                         return ps;
                                     }
                                 }
        );
        return in;
    }


}
