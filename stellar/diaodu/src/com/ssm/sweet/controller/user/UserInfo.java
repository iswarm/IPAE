package com.ssm.sweet.controller.user;

import com.ssm.sweet.moble.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 获取授信用户的信息
 */

@Controller
@RequestMapping("userinfo")
public class UserInfo {
    @Autowired
    private com.ssm.sweet.sevice.user.UserService UserService;
    @RequestMapping("getinfo")
    public ModelAndView getInfo(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("Coming!");
        String skey = request.getParameter("skey");
        String gkey = request.getParameter("gkey");
        System.out.println("skey="+skey+"---gkey="+gkey);
        request.setAttribute("gkey",gkey);
        request.setAttribute("skey",skey);
        List<User> list = UserService.queryUser();
        for(User attribute : list) {
            System.out.println("数据--》"+attribute.getEle_no());
        }

        ModelAndView mv = new ModelAndView();
        //封装要显示到视图的数据
         mv.addObject("list",list);
        //视图名
        mv.setViewName("/diaodu/diaodu");

        return mv;
    }
}
