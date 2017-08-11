package com.ssm.sweet.controller.user;

import com.ssm.sweet.sevice.user.UserService;
import com.ssm.sweet.util.DESUtil;
import com.ssm.sweet.util.Watch;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.stellar.sdk.KeyPair;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Controller
@RequestMapping("login")
public class Login {

    @Autowired
    private UserService newUserService;


    @RequestMapping(value = "login",method = RequestMethod.POST)
    public void login(HttpServletRequest request, HttpServletResponse response) throws Exception {


        String pass = request.getParameter("pass");
        String fileString = request.getParameter("fileString");

        DESUtil des = new DESUtil();
        String key = des.decrypt(fileString,pass);
        System.out.println("key="+key+"-----fileString"+fileString);
        boolean bool = des.isMessyCode(key);
        System.out.println("bool="+bool);
        PrintWriter printWriter = null;
        JSONObject jsonObject = new JSONObject();
        try {
            printWriter = response.getWriter();
            if(bool){
                System.out.println("666666666");
                jsonObject.put("status","NO");
                printWriter.print(jsonObject);
            }else{
                KeyPair keyPair = KeyPair.fromSecretSeed(key);
                String gkey = keyPair.getAccountId();
                System.out.println("公钥="+gkey);
                new Watch().test(gkey);
                jsonObject.put("status","OK");

                jsonObject.put("gkey",gkey);
                jsonObject.put("skey",key);

                printWriter.print(jsonObject);
            }

        } catch (IOException ex) {
           // Logger.getLogger(HelloController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (null != printWriter) {
                printWriter.flush();
                printWriter.close();
            }
        }
    }

    public static void WriteStringToFile(String filePath) {
        try {
            FileWriter fw = new FileWriter(filePath, true);
            BufferedWriter bw = new BufferedWriter(fw);
            String key = keyPair();
            bw.write(key);// 往已有的文件上添加字符串
            bw.close();
            fw.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public static void createFile(String path) {

        //path表示你所创建文件的路径
        // String path = "d:/tr/rt";

        String filename = null;
        int len = path.length();
        String pa = null;
        for (int i = len - 1; i > 0; i--) {
            char c = path.charAt(i);
            if (c == '\\') {
                filename = path.substring(i + 1, len);
                pa = path.substring(0, i + 1);
                break;
            }
        }

        System.out.println("pa=" + pa);
        File f = new File(pa);
        if (!f.exists()) {
            f.mkdirs();
        }
        // fileName表示你创建的文件名；为txt类型；

        String fileName = filename;
        System.out.println("fileName=" + fileName);
        File file = new File(f, fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    public static String keyPair() {

        InputStream response = null;
        KeyPair pair = KeyPair.random();//新建stellar账户

        String skey = new String(pair.getSecretSeed());//私钥
        String gkey = pair.getAccountId();//公钥
        String key = md5(skey);
        System.out.println("key=" + key);
        System.out.println(skey);//私钥
        System.out.println(gkey);//公钥
        return key;

    }

    public static String md5(String str) {
        try {
            //此 MessageDigest 类为应用程序提供信息摘要算法的功能，如 MD5 或 SHA 算法
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[] byteDigest = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < byteDigest.length; offset++) {
                i = byteDigest[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            // 32位加密
            return buf.toString();
            // 16位的加密
            // return buf.toString().substring(8, 24);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }


}
