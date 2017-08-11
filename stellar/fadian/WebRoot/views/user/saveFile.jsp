<%@ page language="java" import="java.util.*" pageEncoding="Utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    String filepath = request.getParameter("path");
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
            <li><a href="javascript:;"><span class="glyphicon glyphicon-user"></span>GDX4F7*****FAMZACOFUII</a></li>
            <li><a href="javascript:;"><span class="glyphicon glyphicon-log-out"></span>注销</a></li>
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
    Copyright ©2013-2017  链能网络  粤ICP备10011451号-6
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
            <input type="password" class="form-control text-center pwd" id="onepwd" placeholder="请输入密码">
            <input type="password" class="form-control text-center pwd" id="twopwd" placeholder="请再次输入密码">

            <input type="button" class="ld_btn ld_btn_margin" value="新建加密账户">
        </form>
        <div class="foot">
            <a href="javascript:;">取消并删除秘钥</a>
        </div>
    </div>
</div>

<script src="../views/lib/jquery.min.js"></script>
<script src="../views/js/common.js"></script>
<script src="../views/js/login.js"></script>
</body>

</html>
