package com.ssm.sweet.controller;

import com.ssm.sweet.moble.user.User;
import com.ssm.sweet.sevice.user.UserService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {
	@Autowired
	private UserService userService;
	
    @RequestMapping("query")
    @ResponseBody
    public List<User> startMVC (HttpServletRequest request){
    	String name = request.getParameter("name");
    	System.out.println(name+"进了控制器");
    	return userService.queryUser();
    }

	@RequestMapping("insert")
	public void inUser (HttpServletRequest request, HttpServletResponse response){

		JSONObject jsonObject = new JSONObject();
		String user_type = request.getParameter("usertype");
		String user_name = request.getParameter("username");
		String card_no = request.getParameter("usercar");
		String phone_no = request.getParameter("userphone");
		String user_pukey = request.getParameter("usergkey");
		String electric_quantity = request.getParameter("usernum");
		String energy_type = request.getParameter("usernengyuan");
		String ammeter_no = request.getParameter("userdbnum");
		//String Ele_no = request.getParameter("usertype");
		String file_id = request.getParameter("file");
		String dispatch_area = request.getParameter("ddarea");
		jsonObject.put("user_type",user_type);
		jsonObject.put("user_name",user_name);
		jsonObject.put("card_no",card_no);
		jsonObject.put("phone_no",phone_no);
		jsonObject.put("user_pukey",user_pukey);
		jsonObject.put("electric_quantity",electric_quantity);
		jsonObject.put("energy_type",energy_type);
		jsonObject.put("ammeter_no",ammeter_no);
		jsonObject.put("Ele_no","");
		jsonObject.put("file_id",file_id);
		jsonObject.put("dispatch_area",dispatch_area);
		jsonObject.put("lon","");
		jsonObject.put("lat","");
		/*String name = request.getParameter("username");
		String name = request.getParameter("username");*/
		System.out.println("进了控制器11");
		int i = userService.insertUser(jsonObject);
		System.out.println(i+"");
		PrintWriter printWriter = null;
		if(i==1){
			try {
				printWriter = response.getWriter();
				printWriter.print("OK");
			} catch (IOException ex) {
				// Logger.getLogger(HelloController.class.getName()).log(Level.SEVERE, null, ex);
			} finally {
				if (null != printWriter) {
					printWriter.flush();
					printWriter.close();
				}
			}
		}else{
			try {
				printWriter = response.getWriter();
				printWriter.print("NO");
			} catch (IOException ex) {
				// Logger.getLogger(HelloController.class.getName()).log(Level.SEVERE, null, ex);
			} finally {
				if (null != printWriter) {
					printWriter.flush();
					printWriter.close();
				}
			}
		}
	}
	

}
