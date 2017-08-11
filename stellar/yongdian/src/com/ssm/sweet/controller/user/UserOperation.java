package com.ssm.sweet.controller.user;

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
    private com.ssm.sweet.sevice.user.newUserService newUserService;


    @RequestMapping("yddeal")
    public ModelAndView deal(HttpServletRequest request, HttpServletResponse response) {


        ModelAndView mv = new ModelAndView();
        //封装要显示到视图的数据
        // mv.addObject("pass",pass);
        //视图名
        mv.setViewName("/yongdian/yongdian");

        return mv;
    }

    /**
     *
     * 发电、用电用户提交授信设置
     */
    @RequestMapping("ydsetUp")
    public void setUp(HttpServletRequest request, HttpServletResponse response) {
        Network.useTestNetwork();
        System.out.println("进来了！！！！！！！！！！！！！！！");
        String dianfeng = request.getParameter("dianfeng");
        String faxingzhanghu = request.getParameter("faxingzhanghu");
        String gkey = request.getParameter("gkey");
        String skey = request.getParameter("skey");
        String diangu = request.getParameter("diangu");
        String dianping = request.getParameter("dianping");
        String daibi = request.getParameter("daibi");
        System.out.println("gkey="+gkey+"---skey="+skey);
        Server server = new Server("https://horizon-testnet.stellar.org");
        PrintWriter printWriter = null;
        KeyPair receivingKeys = KeyPair
                .fromSecretSeed(skey);// 普通用户
        KeyPair  issuingKeys = KeyPair
                .fromAccountId(faxingzhanghu);// 调度用户
        if(!("".equals(dianfeng))) {
            Asset astroDollar = Asset.createNonNativeAsset("FENG", issuingKeys);//创建新资产
            AccountResponse receiving = null;
            try {
                receiving = server.accounts().account(receivingKeys);
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            Transaction allowAstroDollars = new Transaction.Builder(receiving)
                    .addOperation(
                            // The `ChangeTrust` operation creates (or alters) a trustline
                            // The second parameter limits the amount the account can hold
                            new ChangeTrustOperation.Builder(astroDollar, dianfeng).build())
                    .build();
            allowAstroDollars.sign(receivingKeys);

            try {
                SubmitTransactionResponse res = server.submitTransaction(allowAstroDollars);
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

            } catch (IOException e) {
                // TODO Auto-generated catch block
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
                e.printStackTrace();
            }

        }

        if(!("".equals(diangu))) {
            Asset astroDollar = Asset.createNonNativeAsset("GU", issuingKeys);//创建新资产
            AccountResponse receiving = null;
            try {
                receiving = server.accounts().account(receivingKeys);
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            Transaction allowAstroDollars = new Transaction.Builder(receiving)
                    .addOperation(
                            // The `ChangeTrust` operation creates (or alters) a trustline
                            // The second parameter limits the amount the account can hold
                            new ChangeTrustOperation.Builder(astroDollar, diangu).build())
                    .build();
            allowAstroDollars.sign(receivingKeys);

            try {
                SubmitTransactionResponse res = server.submitTransaction(allowAstroDollars);
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

            } catch (IOException e) {
                // TODO Auto-generated catch block
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
                e.printStackTrace();
            }

        }

        if(!("".equals(dianping))) {
            Asset astroDollar = Asset.createNonNativeAsset("PING", issuingKeys);//创建新资产
            AccountResponse receiving = null;
            try {
                receiving = server.accounts().account(receivingKeys);
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            Transaction allowAstroDollars = new Transaction.Builder(receiving)
                    .addOperation(
                            // The `ChangeTrust` operation creates (or alters) a trustline
                            // The second parameter limits the amount the account can hold
                            new ChangeTrustOperation.Builder(astroDollar, dianping).build())
                    .build();
            allowAstroDollars.sign(receivingKeys);

            try {
                SubmitTransactionResponse res = server.submitTransaction(allowAstroDollars);
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

            } catch (IOException e) {
                // TODO Auto-generated catch block
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
                e.printStackTrace();
            }

        }

        if(!("".equals(daibi))) {
            Asset astroDollar = Asset.createNonNativeAsset("DAIBI", issuingKeys);//创建新资产
            AccountResponse receiving = null;
            try {
                receiving = server.accounts().account(receivingKeys);
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            Transaction allowAstroDollars = new Transaction.Builder(receiving)
                    .addOperation(
                            // The `ChangeTrust` operation creates (or alters) a trustline
                            // The second parameter limits the amount the account can hold
                            new ChangeTrustOperation.Builder(astroDollar, daibi).build())
                    .build();
            allowAstroDollars.sign(receivingKeys);

            try {
                SubmitTransactionResponse res = server.submitTransaction(allowAstroDollars);
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

            } catch (IOException e) {
                // TODO Auto-generated catch block
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
                e.printStackTrace();
            }

        }

    }

}