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
        <title>登陆成功</title>
        <link rel="stylesheet" href="../views/lib/bootstrap-3.3.7-dist/css/bootstrap.min.css" />
        <link rel="stylesheet" href="../views/css/reset.css" />
    </head>
<body>
<div class="ld_header clearfix">
    <div class="ld_Wrap">
        <ul class="pull-left clearfix">
            <li><a href="javascript:;"><span class="glyphicon glyphicon-earphone"></span>020-38488825</a></li>
            <li><a href="javascript:;"><span class="glyphicon glyphicon-envelope"></span>srkj@sunrizetech.com</a></li>
        </ul>
        <ul class="pull-right clearfix">
            <li><a href="javascript:;"><span class="glyphicon glyphicon-user"></span><input type="hidden" id="gkey" value="<%=request.getAttribute("gkey")%>"/></a></li>
            <li><a href="../../index.jsp"><span class="glyphicon glyphicon-log-out"></span>注销</a></li>
        </ul>
    </div>

</div>
<div class="ld_logo">
    <div class="ld_Wrap">
        <a href=""><img src="../views/img/logo.png" alt="" /></a>
    </div>


</div>
<div class="ld_content tab-content-padding ld_Wrap">
    <h3 class="content_title">
        链能地址<span class="content_tips">(可用于交易转账的公用交易地址) </span>
    </h3>
    <div class="ld_content_list clearfix margin_top20">
        <span class="list_title1 display_block">地址:</span>
        <span class="list_title2 display_block margin_bottom18"> <%=request.getAttribute("gkey")%></span>
    </div>
    <h3 class="content_title margin_bottom18">
        重要信息<span class="content_tips">(任何拥有该秘钥的人都可以取出你的钱。所以务必将它保管在安全的地方。)</span>
    </h3>
    <div class="ld_content_list">
        <div>
            <span class="list_title1">秘钥:</span>
            <span class="list_title2" id="str"><%=request.getAttribute("str")%></span>
            <span class="list_title2" style="display: none" id="skey"><%=request.getAttribute("skey")%></span>
            <input type="hidden" id="copy" value="<%=request.getAttribute("skey")%>"/>
            <span class="content_tips">(请在确定安全的地方显示，防止泄露秘钥信息造成损失)</span>
            <input type="button" class="ld_btn" id="show" name="show" value="显示密钥" onclick="show();"/>
            <input type="button" class="ld_btn" id="noshow" name="noshow" value="隐藏密钥" style="display: none" onclick="noshow();"/>
        </div>
        <div>
            <span class="list_title1">你把密钥保存在安全的地方了吗？</span>
            <span class="content_tips">(秘钥文件不得外流，请妥善保存你的秘钥文件，建议多用几个专门的U盘备份钱包文件并分地方存放。)</span>
            <input type="button" class="ld_btn" value="嗯！我已经保存好了" onclick="win();"/>
            <input type="button" class="ld_btn" value="保存秘钥" onclick="copy();"/>
        </div>
    </div>
    <div class="padBottom"></div>
</div>
<div class="ld_footer text-center">
    Copyright ©2013-2017  链电网络  粤ICP备10011451号-6
</div>

<script src="../views/lib/jquery.min.js"></script>
<script src="../views/js/common.js"></script>
<script type="text/javascript">
    
    function win() {
        var gkey = document.getElementById("gkey").value;
        var skey = document.getElementById("copy").value;
        window.location.href="../../userinfo/getinfo.action?gkey="+gkey+"&skey="+skey;
       // window.location.href="../views/diaodu/diaodu.jsp?gkey="+gkey+"&skey="+skey;
        //window.location.href="../../userinfo/getinfo.action?gkey="+gkey+"&skey="+skey;
    }

    function show() {
        var show = document.getElementById("show");
        var noshow = document.getElementById("noshow");
        var str = document.getElementById("str");
        var skey = document.getElementById("skey");
        noshow.style.display = "block";
        show.style.display = "none";
        str.style.display = "none";
        skey.style.display = "block";
    }
    function noshow() {
        var show = document.getElementById("show");
        var noshow = document.getElementById("noshow");
        var str = document.getElementById("str");
        var skey = document.getElementById("skey");
        noshow.style.display = "none";
        show.style.display = "block";
        str.style.display = "block";
        skey.style.display = "none";
    }

    function copy()
    {
        var oContent=document.getElementById("copy");
        oContent.select(); // 选择对象
        document.execCommand("Copy"); // 执行浏览器复制命令
        alert("复制完毕，可粘贴");
        alert(s);
    }

    function copyToClipboard(elem) {
// create hidden text element, if it doesn't already exist
        alert(3345);
        var targetId = "_hiddenCopyText_";
        var isInput = elem.tagName === "INPUT" || elem.tagName === "TEXTAREA";
        var origSelectionStart, origSelectionEnd;
        if (isInput) {
// can just use the original source element for the selection and copy
            target = elem;
            origSelectionStart = elem.selectionStart;
            origSelectionEnd = elem.selectionEnd;
        } else {
// must use a temporary form element for the selection and copy
            target = document.getElementById(targetId);
            if (!target) {
                var target = document.createElement("textarea");
                target.style.position = "absolute";
                target.style.left = "-9999px";
                target.style.top = "0";
                target.id = targetId;
                document.body.appendChild(target);
            }
            target.textContent = elem.textContent;
        }
// select the content
        var currentFocus = document.activeElement;
        target.focus();
        target.setSelectionRange(0, target.value.length);
// copy the selection
        var succeed;
        try {
            succeed = document.execCommand("copy");
        } catch(e) {
            succeed = false;
        }
// restore original focus
        if (currentFocus && typeof currentFocus.focus === "function") {
            currentFocus.focus();
        }
        if (isInput) {
// restore prior selection
            elem.setSelectionRange(origSelectionStart, origSelectionEnd);
        } else {
// clear temporary content
            target.textContent = "";
        }
        return succeed;
    }
</script>
</body>
</html>
