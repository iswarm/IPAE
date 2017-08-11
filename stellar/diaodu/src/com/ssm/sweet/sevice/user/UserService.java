package com.ssm.sweet.sevice.user;

import com.ssm.sweet.moble.user.User;
import net.sf.json.JSONObject;

import java.util.List;

/**
 * Created by Administrator on 2017/6/20.
 */
public interface UserService {
    public List<User> queryUser();
    public int insertUser(JSONObject jsonObject);
}
