package com.ssm.sweet.controller.user;

import com.ssm.sweet.sevice.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.stellar.sdk.*;
import org.stellar.sdk.responses.AccountResponse;
import org.stellar.sdk.responses.SubmitTransactionResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Administrator on 2017/6/23.
 */
/*public class UserOperation {
}*/

@Controller
@RequestMapping("operation")
public class UserOperation {

    @Autowired
    private UserService UserService;


    @RequestMapping("yddeal")
    public ModelAndView deal(HttpServletRequest request, HttpServletResponse response) {


        ModelAndView mv = new ModelAndView();
        //封装要显示到视图的数据
        // mv.addObject("pass",pass);
        //视图名
        mv.setViewName("/yongdian/yongdian");

        return mv;
    }


    @RequestMapping("ddsetUp")
    public void setUp(HttpServletRequest request, HttpServletResponse response) {
        Network.useTestNetwork();
        System.out.println("进来了！！！！！！！！！！！！！！！");
        String zctype = request.getParameter("zctype");
        String sdgkey = request.getParameter("sdgkey");
        String gkey = request.getParameter("gkey");
        String num = request.getParameter("num");
        Server server = new Server("https://horizon-testnet.stellar.org");
        System.out.println(gkey);
        KeyPair issuingKeys = KeyPair.fromAccountId(gkey);
        Asset astroDollar = Asset.createNonNativeAsset(zctype, issuingKeys);
        PrintWriter printWriter = null;

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


    }

    @RequestMapping("setUser")
    public void setUser(HttpServletRequest request, HttpServletResponse response) {
        Network.useTestNetwork();
        System.out.println("进来了！！！！！！！！！！！！！！！111111");
        String skey = request.getParameter("skey");
        String usergkey = request.getParameter("usergkey");
        String ty = request.getParameter("ty");
        System.out.println("ty="+ty);
        System.out.println(skey);
        Server server = new Server("https://horizon-testnet.stellar.org");
        System.out.println(usergkey);
        KeyPair source = KeyPair.fromSecretSeed("SDAERZVKOXIS73Q26457TNYOXVYQCKQ6GEY74RC7XVIMA5GIB56X54QS");//调度账号密钥
        KeyPair source1 = KeyPair.fromSecretSeed(skey);
        KeyPair destination = KeyPair.fromAccountId(usergkey);//普通用户
        System.out.println("调度账号密钥="+skey+"------普通用户="+usergkey);
        String saddress = source.getAccountId();
        PrintWriter printWriter = null;
        // First, check to make sure that the destination account exists.
        // You could skip this, but if the account does not exist, you will be charged
        // the transaction fee when the transaction fails.
        // It will throw HttpResponseException if account does not exist or there was another error.
        try {
            server.accounts().account(destination);
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        // If there was no error, load up-to-date information on your account.
        AccountResponse sourceAccount = null;
        try {
            sourceAccount = server.accounts().account(source);
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        // Start building the transaction.
        Transaction transaction = new Transaction.Builder(sourceAccount)
                .addOperation(new AllowTrustOperation.Builder(destination, ty,true).build())
                // A memo allows you to add your own metadata to a transaction. It's
                // optional and does not affect how Stellar treats the transaction.
                .build();
        // Sign the transaction to prove you are actually the person sending it.
        transaction.sign(source1);

        // And finally, send it off to Stellar!
        try {
            SubmitTransactionResponse res = server.submitTransaction(transaction);
            System.out.print("Success?");
            System.out.println(res.isSuccess());
            if(res.isSuccess()){
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
            } else{
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
            // System.out.println(response);
        } catch (Exception e) {
            System.out.println("Something went wrong!");
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
            System.out.println(e.getMessage());
        }


    }

}