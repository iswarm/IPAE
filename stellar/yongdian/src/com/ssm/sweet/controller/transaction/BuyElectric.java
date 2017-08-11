package com.ssm.sweet.controller.transaction;

import com.ssm.sweet.moble.Data;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.stellar.sdk.*;
import org.stellar.sdk.responses.AccountResponse;
import org.stellar.sdk.responses.SubmitTransactionResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 用电用户交易
 */

@Controller
@RequestMapping("transaction")
public class BuyElectric {

    @RequestMapping("buy")
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
        KeyPair  issuingKeys = KeyPair
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
            Asset buying = Asset.createNonNativeAsset(type, receivingKeys);
            Asset selling = Asset.createNonNativeAsset("DAIBI", receivingKeys);
            Transaction allowAstroDollars = new Transaction.Builder(receiving)
                    .addOperation(
                            // The `ChangeTrust` operation creates (or alters) a trustline
                            // The second parameter limits the amount the account can hold
                            new ManageOfferOperation.Builder(selling, buying,num,price).build())
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

    @RequestMapping("userInfo")
    public ModelAndView saveFile (HttpServletRequest request, HttpServletResponse response){
        String skey = request.getParameter("skey");
        String gkey = request.getParameter("gkey");
        String Balance = request.getParameter("Balance");
        String Limit = request.getParameter("Limit");
        String url = "https://horizon-testnet.stellar.org/order_book/trades";
        String param = "order=desc&limit=5";
        String s = sendGet(url, param);
        System.out.println(s);
        JSONObject json_test = JSONObject.fromObject(s);
        System.out.println(json_test.get("_embedded"));
        json_test = (JSONObject) json_test.get("_embedded");
        System.out.println(json_test);
        JSONArray json_to_strings = json_test.getJSONArray("records");//先将JSONObject里包含的JSONArray取出
        System.out.println(json_to_strings.size());
        List<com.ssm.sweet.moble.Data> entities =new ArrayList<com.ssm.sweet.moble.Data>();
        com.ssm.sweet.moble.Data data = new Data();
        for (Object object : json_to_strings) {//循环读取即可
            JSONObject json_to_string = JSONObject.fromObject(object);
            String boughtamount = json_to_string.get("bought_amount")+"";
            String bought_asset_code = json_to_string.get("bought_asset_code")+"";
            String price = json_to_string.get("price")+"";
            data.setBought_amount(boughtamount);
            data.setBought_asset_code(bought_asset_code);
            data.setPrice(price);
            entities.add(data);
            System.out.println(data.getBought_amount());
            System.out.println("bought_amount="+json_to_string.get("bought_amount"));
            System.out.println("bought_asset_code="+json_to_string.get("bought_asset_code"));
            System.out.println("price="+json_to_string.get("price"));
        }


        for(int i=0;i<entities.size();i++){

            System.out.println(entities.get(i).getBought_amount());
        }


        ModelAndView mv = new ModelAndView();
        //封装要显示到视图的数据
         mv.addObject("list",entities);
        //视图名
        mv.setViewName("/yongdian/yongdian");
        request.setAttribute("skey",skey);
        request.setAttribute("gkey",gkey);
        request.setAttribute("Balance",Balance);
        request.setAttribute("Limit",Limit);

        return mv;
    }

    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            /*for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }*/
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
}
