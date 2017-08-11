package com.ssm.sweet.controller.user;

import com.ssm.sweet.moble.user.User;
import com.ssm.sweet.sevice.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Administrator on 2017/6/20.
 */

@Controller
@RequestMapping("user")
public class newUserController {

    @Autowired
    private UserService UserService;

    @RequestMapping("mall1")
    @ResponseBody
    public List<User> startMVC (HttpServletRequest request){
        String name = request.getParameter("name");
        System.out.println(name+"进了控制器");
        return UserService.queryUser();
    }



}
