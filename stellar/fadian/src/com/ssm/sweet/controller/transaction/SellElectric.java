package com.ssm.sweet.controller.transaction;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.stellar.sdk.*;
import org.stellar.sdk.responses.AccountResponse;
import org.stellar.sdk.responses.SubmitTransactionResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 用电用户交易
 */

@Controller
@RequestMapping("transaction")
public class SellElectric {

    @RequestMapping("sell")
    public void buy(HttpServletRequest request, HttpServletResponse response) {

        String type = request.getParameter("type");
        String price = request.getParameter("price");
        String num = request.getParameter("num");
        String skey = request.getParameter("skey");
        String gkey = request.getParameter("gkey");
        String faxingzhanghu = request.getParameter("faxingzhanghu");
        System.out.println("type="+type+"-----price="+price+"-----num="+num);

        PrintWriter printWriter = null;

        Network.useTestNetwork();
        Server server = new Server("https://horizon-testnet.stellar.org");
        //System.out.println(server);
        // Keys for accounts to issue and receive the new asset
        KeyPair receivingKeys = KeyPair
                .fromSecretSeed(skey);// 发出
        KeyPair issuingKeys = KeyPair
                .fromAccountId(faxingzhanghu);// 接收

        String iaddress = issuingKeys.getAccountId();
        String raddress = receivingKeys.getAccountId();

        // Create an object to represent the new asset
        //Asset astroDollar = Asset.createNonNativeAsset("TEST", issuingKeys);//FENG

        // First, the receiving account must trust the asset
        //交易前检查
        String astroDollarCode = type;
        String astroDollarIssuer = faxingzhanghu;//获取发行账户公钥
        System.out.println("faxingzhanghu="+astroDollarIssuer);
        // Load the account you want to check 要检查的账号

        KeyPair keysToCheck = KeyPair.fromAccountId(gkey);
        AccountResponse accountToCheck = null;
        try {
            accountToCheck = server.accounts().account(keysToCheck);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // See if any balances are for the asset code and issuer we're looking for
        boolean trusted = false;
        for (AccountResponse.Balance balance : accountToCheck.getBalances()) {
            //System.out.println(balance.getAssetCode().equals(astroDollarCode));
			/*System.out.println(balance.getAssetType());
			System.out.println(balance.getAssetCode());*/
            System.out.println(balance.getAssetIssuer().getAccountId());
            if (!balance.getAssetType().equals("native") &&
                    balance.getAssetCode().equals(astroDollarCode) &&
                    balance.getAssetIssuer().getAccountId().equals(astroDollarIssuer)) {
                trusted = true;
                break;
            }
        }

        if(trusted){
            AccountResponse receiving = null;
            try {
                receiving = server.accounts().account(receivingKeys);
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            Asset buying = Asset.createNonNativeAsset("DAIBI", receivingKeys);
            Asset selling = Asset.createNonNativeAsset(type, receivingKeys);
            Transaction allowAstroDollars = new Transaction.Builder(receiving)
                    .addOperation(
                            // The `ChangeTrust` operation creates (or alters) a trustline
                            // The second parameter limits the amount the account can hold
                            new ManageOfferOperation.Builder(selling, buying, price,num).build())
                    .build();
            allowAstroDollars.sign(receivingKeys);
            System.out.println("1111112222");
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
            } catch (IOException e) {
                // TODO Auto-generated catch block
                System.out.println("NO");
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
        }else{
            try {
                printWriter = response.getWriter();
                printWriter.print("NOTR");
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
