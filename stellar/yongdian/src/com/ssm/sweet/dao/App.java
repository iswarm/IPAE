package com.ssm.sweet.dao;

import java.util.List;  



import org.springframework.context.ApplicationContext;  
import org.springframework.context.support.ClassPathXmlApplicationContext;  

import com.ssm.sweet.moble.mallMember;

public class App {
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:app*.xml");  
		 mallMemberDao dao = (mallMemberDao) ac.getBean("mallMemberDaoImpl");
		 List<mallMember> list= dao.findmallMemberAll();
		 for(mallMember member : list) {
			 System.out.println(member.getId());
			 System.out.println(member.getUser_name());
		 }
	}

}
