<%@ page language="java" import="java.util.*" pageEncoding="Utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
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
            <li><a href="javascript:;"><span class="glyphicon glyphicon-user" onclick="userInfo();"></span>
            <input type="hidden" value="" id="gsky"/>
            </li>
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


<!--登录-->
<div class="">
    <!--<div class="zhezhao"></div>-->
    <div id="loginModel" class="text-center">
        <div class="">
            <p class="title">登录</p>
            <p class="tip">使用你的秘钥登录已有的账户</p>
        </div>
        <form action="login/login.action">
            <a href="javascript;" class="selectFile">选择秘钥文件
                <input type="file" id="file">
            </a>

            <input type="password" class="form-control text-center pwd" id="log_pwd" placeholder="请输入密码">

            <input type="button" class="ld_btn ld_btn_margin" value="登录" onclick="getPath();">
        </form>
        <div class="log_foot">
            <a href="file/saveFile.action">创建新账户</a>
        </div>
    </div>
</div>

<script src="../views/lib/jquery.min.js"></script>
<script src="../views/js/common.js"></script>
<script src="../views/js/login.js"></script>
</body>
<script type="text/javascript">
    function getPath() {
        var pass = document.getElementById("log_pwd").value;
        if(pass==null || pass==""){
            alert("请输入密码！");return false;
        }
        var objFile = document.getElementById("file");
        if(objFile.value == "") {
            alert("密钥文件不能空")
        }

        console.log(objFile.files[0].size); // 文件字节数

        var files = $('#file').prop('files');//获取到文件列表
        if(files.length == 0){
            alert('请选择文件');
        }else{
            var reader = new FileReader();//新建一个FileReader
            reader.readAsText(files[0], "UTF-8");//读取文件
            var fileString = null;
            reader.onload = function(evt){ //读取完文件之后会回来这里
                fileString = evt.target.result; // 读取文件内容
                $.ajax({
                    type: "POST",
                    timeout: 3000,
                    async: false,
                    cache: false,
                    data:{fileString:fileString,pass:pass},//参数
                    url: "login/login.action",
                    success: function (data){
                        var result = eval("(" + data + ")");
                        if(result.status=="OK"){
                            window.location.href ="../views/fadian/fadian.jsp?gkey="+result.gkey+"&skey="+result.skey;
                        }else{
                            alert("登录失败，请检查密码跟钱包文件是否对应！");
                        }
                    }
                });
            }

        }

    }
    
    function userInfo() {
        alert(11111);
        
    }

</script>
</html>
