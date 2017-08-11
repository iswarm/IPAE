package com.ssm.sweet.controller.login;

import com.ssm.sweet.sevice.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.stellar.sdk.KeyPair;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Controller
@RequestMapping("login")
public class GetFile {

    @Autowired
    private UserService UserService;

    @RequestMapping("getfile")
    public void  getFilePath (HttpServletRequest request, HttpServletResponse response){
        File file=null;
        FileNameExtensionFilter filter=new FileNameExtensionFilter("*.txt","txt");
        JFileChooser fc=new JFileChooser("D:/");
        fc.setFileFilter(filter);
        fc.setMultiSelectionEnabled(false);
        fc.setSelectedFile(new File("wallet.txt")); //设置默认文件名
        int result=fc.showOpenDialog(null);
        if (result==JFileChooser.APPROVE_OPTION) {
            file=fc.getSelectedFile();
            if (!file.getPath().endsWith(".txt")) {
                file=new File(file.getPath()+".txt");
            }

            System.out.println("file path1="+file.getPath());
           /* FileOutputStream fos=null;
            try {
                if (!file.exists()) {//文件不存在 则创建一个
                    file.createNewFile();
                }
                fos=new FileOutputStream(file);
                fos.write(keyPair().getBytes());
                fos.flush();
            } catch (IOException e) {
                System.err.println("文件创建失败：");
                e.printStackTrace();
            }finally{
                if (fos!=null) {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }*/
        }



       /* ModelAndView mv = new ModelAndView();
        //封装要显示到视图的数据
        //mv.addObject("path",file.getPath());
        //视图名
        mv.setViewName("/user/savePass");
        request.setAttribute("path",file.getPath());
        return mv;*/
    }

    @RequestMapping("userInfo")
    public ModelAndView  saveFile (HttpServletRequest request, HttpServletResponse response){

        KeyPair pair = KeyPair.random();//新建stellar账户

        String skey = new String(pair.getSecretSeed());//私钥
        String gkey = pair.getAccountId();//公钥
        int len = skey.length();
        String str = "";
        for(int i=0;i<len;i++){
            if(i>2&&i<len-3){
                str+="*";
            }else{
                str+=skey.charAt(i);
            }
        }
        String key = md5(skey);
        String path = request.getParameter("path");
        String pass = request.getParameter("pass");
        System.out.println("path="+path+"--------"+"pass="+pass+"-----------str="+str);
        createFile(path);
        WriteStringToFile(path);
        ModelAndView mv = new ModelAndView();
        //封装要显示到视图的数据
        // mv.addObject("pass",pass);
        //视图名
        mv.setViewName("/user/userInfo");
        request.setAttribute("pass",pass);
        request.setAttribute("skey",skey);
        request.setAttribute("str",str);
        request.setAttribute("path",pass);
        return mv;
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


    public static void createFile(String path){

        //path表示你所创建文件的路径
        // String path = "d:/tr/rt";

        String filename = null;
        int len = path.length();
        String pa = null;
        for(int i = len - 1; i > 0; i--) {
            char c = path.charAt(i);
            if(c == '\\') {
                filename=path.substring(i+1, len);
                pa = path.substring(0, i+1);
                break;
            }
        }

        System.out.println("pa="+pa);
        File f = new File(pa);
        if(!f.exists()){
            f.mkdirs();
        }
        // fileName表示你创建的文件名；为txt类型；

        String fileName=filename;
        System.out.println("fileName="+fileName);
        File file = new File(f,fileName);
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    public static String keyPair(){

        InputStream response = null;
        KeyPair pair = KeyPair.random();//新建stellar账户

        String skey = new String(pair.getSecretSeed());//私钥
        String gkey = pair.getAccountId();//公钥
        String key = md5(skey);
        System.out.println("key="+key);
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
