package com.ssm.sweet.controller;

import com.ssm.sweet.moble.mallMember;
import com.ssm.sweet.sevice.mallMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("user")
public class mallMemberController {
	@Autowired
	private mallMemberService memberService;
	
    @RequestMapping("mall")
    @ResponseBody
    public List<mallMember> startMVC (HttpServletRequest request){
    	String name = request.getParameter("name");
    	System.out.println(name+"进了控制器");
    	return memberService.findAll();
    }  
	

}
