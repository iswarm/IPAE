<%@ page language="java" import="java.util.*" pageEncoding="Utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    String filepath = request.getParameter("path");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0" />
    <title>链电</title>
    <link rel="stylesheet" href="../views/lib/bootstrap-3.3.7-dist/css/bootstrap.min.css" />
    <link rel="stylesheet" href="../views/css/reset.css" />
    <link rel="stylesheet" type="text/css" href="../views/css/login.css"/>
</head>
<body>
<div class="ld_header clearfix">
    <div class="ld_Wrap">
        <ul class="pull-left clearfix">
            <li><a href="javascript:;"><span class="glyphicon glyphicon-earphone"></span>020-38488825</a></li>
            <li><a href="javascript:;"><span class="glyphicon glyphicon-envelope"></span>srkj@sunrizetech.com</a></li>
        </ul>
        <ul class="pull-right clearfix">
            <li><a href="javascript:;"><span class="glyphicon glyphicon-user"></span><%=request.getAttribute("gsky")%></a></li>
            <li><a href="../../index.jsp"><span class="glyphicon glyphicon-log-out"></span>注销</a></li>
        </ul>
    </div>

</div>
<div class="ld_logo">
    <div class="ld_Wrap">
        <a href=""><img src="../views/img/logo.png" alt="" /></a>
    </div>

</div>
<div class="ld_banner">
    <!--<img src="../../img/bg.jpg"></img>-->

</div>
<div class="ld_footer text-center">
    Copyright ©2013-2017  链电网络  粤ICP备10011451号-6
</div>




<!--创建新账户-->
<div class="loginWrap" style="">
    <!--<div class="zhezhao"></div>-->
    <div id="createModel" class="text-center">
        <div class="">
            <p class="title">创建新账户</p>
            <p class="tip">加密你的新账户文件</p>
        </div>
        <form>
            <!--<a href="javascript:;" class="selectFile">选择秘钥文件
                <input type="file" id="">
            </a>-->
            <p><%=request.getAttribute("path")%></p>
            <input type="hidden" id="path" name="path" value="<%=request.getAttribute("path")%>"/>
            <input type="password" class="form-control text-center pwd" name="pass" id="pass" placeholder="请输入密码" value="">
            <input type="password" class="form-control text-center pwd" name="twopwd" id="twopwd" placeholder="请再次输入密码" value="">

            <input type="button" class="ld_btn ld_btn_margin" value="新建加密账户" onclick="save();">
        </form>
        <div class="foot">
            <a href="../index.jsp">取消并删除秘钥</a>
        </div>
    </div>
</div>

<script src="../views/lib/jquery.min.js"></script>
<script src="../views/js/common.js"></script>
<script src="../views/js/login.js"></script>
</body>

<script type="text/javascript">
    function save() {
        var path = document.getElementById("path").value;
        var pass = document.getElementById("pass").value;
        var twopwd = document.getElementById("twopwd").value;
        if(pass!=twopwd){
            alert("两次密码不一致，请重新输入。");return false;
        }else{
            window.location.href = "file/userInfo.action?pass="+pass+"&path="+path;
            /*$.ajax({
                type: "POST",
                timeout: 3000,
                async: false,
                cache: false,
                data:{path:path,pass:pass},//参数
                url: "file/userInfo.action",
                success: function (data){
                    var result = eval("(" + data + ")");
                    if(result.status=="OK"){
                    window.location.href = "";
                      $.ajax({
                            type: "POST",
                            timeout: 3000,
                            async: false,
                            cache: false,
                            data:{path:result.pass,skey:result.skey,gkey:result.gkey,str:result.str},//参数
                            url: "file/showInfo.action",
                            success: function (data){
                                //newForm(result.pass,result.skey,result.gkey,result.str);
                         }
                        });
                    }else{
                        alert("创建用户失败！");
                    }
                }
            });*/
        }
    }

    function newForm(pass,skey,gkey,str) {


        var f=document.createElement('form');
        document.body.appendChild(f);

        f.action='file/showInfo1.action';
        f.method='post';
        var newElement2 = document.createElement("input");
        newElement2.setAttribute("id","skey1");
        newElement2.setAttribute("type","hidden");
        newElement2.setAttribute("value",skey);
        var newElement3 = document.createElement("input");
        newElement3.setAttribute("id","gkey1");
        newElement3.setAttribute("type","hidden");
        newElement3.setAttribute("value",gkey);
        var newElement4 = document.createElement("input");
        newElement4.setAttribute("id","str1");
        newElement4.setAttribute("type","hidden");
        newElement4.setAttribute("value",str);
        f.appendChild(newElement1);
        f.appendChild(newElement2);
        f.appendChild(newElement3);
        f.appendChild(newElement4);

        console.log(f);
        var w = document.getElementById("gkey1").value;
        alert(w);
        f.submit();
        /*var f = document.createElement("form");
        document.body.appendChild(f);
        var i = document.createElement("input");
        i.type = "hidden";
        i.value = "5";
        i.id = "skey1";
        f.appendChild(i);
        f.action = "file/showInfo1.action";
        f.method='post';
        alert(document.getElementById("skey").value);
        f.submit();*/
    }

</script>
</html>
