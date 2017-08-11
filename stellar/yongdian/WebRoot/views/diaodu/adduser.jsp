<%@ page language="java" import="java.util.*" pageEncoding="Utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <meta charset="UTF-8">
        <title></title>
        <link rel="stylesheet" href="../views/lib/bootstrap-3.3.7-dist/css/bootstrap.min.css" />
        <link rel="stylesheet" href="../views/css/reset.css" />
        <link rel="stylesheet" type="text/css" href="../views/css/diaodu.css"/>
    </head>
<body>
<!-- 添加用户Modal -->
<div id="addUser">
    <div class="addUser_header">
        添加用户
        <span class="glyphicon glyphicon-remove"></span>
    </div>
    <div class="addUser_content"></div>
    <div class="addUser_footer"></div>
</div>


<script src="../views/lib/jquery.min.js"></script>
<script src="../views/lib/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</body>
</html>
