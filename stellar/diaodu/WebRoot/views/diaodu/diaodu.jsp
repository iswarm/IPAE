<%@ page language="java" import="java.util.*" pageEncoding="Utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0" />
    <title>调度</title>
    <link rel="stylesheet" href="../views/lib/bootstrap-3.3.7-dist/css/bootstrap.min.css" />
    <link rel="stylesheet" href="../views/css/reset.css" />
    <link rel="stylesheet" href="../views/css/diaodu.css" />

</head>
<body>
<div class="ld_header clearfix">
    <div class="ld_Wrap">
        <ul class="pull-left clearfix">
            <li><a href="javascript:;"><span class="glyphicon glyphicon-earphone"></span>020-38488825</a></li>
            <li><a href="javascript:;"><span class="glyphicon glyphicon-envelope"></span>srkj@sunrizetech.com</a></li>
        </ul>
        <ul class="pull-right clearfix">
            <li><a href="javascript:;"><span class="glyphicon glyphicon-user"></span>
                <input type="hidden" id="gkey" value="<%=request.getParameter("gkey")%>"/>
                <input type="hidden" id="skey" value="<%=request.getParameter("skey")%>"/>
            </a></li>
            <li><a href="../../index.jsp;"><span class="glyphicon glyphicon-log-out"></span>注销</a></li>
        </ul>
    </div>

</div>

<div class="ld_logo">
    <div class="ld_Wrap">
        <a href=""><img src="../views/img/logo.png" alt="" /></a>
        <ul class="pull-right" role="tablist">
            <li role="presentation" class=" ld_active"><a href="#setCount" aria-controls="home" role="tab" data-toggle="tab">账户设置</a></li>
            <li role="presentation"><a href="#giveUser" aria-controls="home" role="tab" data-toggle="tab">授信用户</a></li>
            <li role="presentation"><a href="#publish" aria-controls="home" role="tab" data-toggle="tab">发行电资产</a></li>
            <li role="presentation"><a href="#transaction" aria-controls="home" role="tab" data-toggle="tab">交易情况</a></li>
        </ul>
    </div>

</div>
<div class="tab-content ld_Wrap tab-content-padding">
    <!--账户设置-->

    <div role="tabpanel" class="tab-pane fade in active ld_content" id="setCount">
        <div class="margin_top20">
            <h3 class="content_title">
                设置账户
            </h3>
            <div class="ld_content_list margin_top20">
                <div>
                    设置跨调度地区电网剩余通道能力：
                    <div class="setAble">
                        <input type="text" class="div_input" value="1000"/>
                        <!--<span class="pull-left">1000</span>-->
                        <span class="pull-right setDanwei">W</span>
                    </div>
                </div>
                <div class="clearfix margin_top20">
                    <span class="pull-left">签订协议上传：</span>
                    <a href="javescript:;" class="xieyi text-center pull-left">+<input type="file" value="" /></a>
                </div>
                <input type="button" name="" id="" value="设置" class="ld_btn padding_l_r" />
            </div>
            <div class="padBottom"></div>
        </div>


    </div>

    <!--授权用户-->
    <div role="tabpanel" class="tab-pane fade ld_content" id="giveUser">
        <h3 class="content_title">授信用户</h3>
        <div class="ld_content_list margin_top20 clearfix margin_bottom18">
            <div class="pull-left clearfix">
                <div class="pull-left">
                    <div class="leixing pull-left">用户类型：
                        <select name="" id="usertype">
                            <option value="yd" selected="selected">用电用户</option>
                            <option value="fd">发电用户</option>
                        </select>
                    </div>
                </div>
                <div class="leixing pull-left">调度地区：
                    <select name="" id="area">
                        <option value="gz" selected="selected">广州</option>

                    </select>
                </div>
            </div>

            <div class="pull-right text-right margin_bottom18">
                <input type="button" name="" onclick="query1();" value="查询" class="ld_btn ld_btn_margin margin_top0 padding_l_r" data-toggle="modal" data-target="#select"/>
                <input type="button" name="" value="添加" class="ld_btn ld_btn_margin margin_top0 padding_l_r" data-toggle="modal" data-target="#addUser" />
            </div>
        </div>
        <%--<iframe id="frame" class="table table-striped text-center" frameborder="0" scrolling="auto" src="#table">--%>
        <div class="ld_content_list padding_l_r clearfix" id="diaodu_shouxin">

            <table class="table table-striped text-center" id="table">
                <thead>
                <tr>
                    <th>用户类型</th>
                    <th>用户姓名</th>
                    <th>手机号</th>
                    <th class="text_overflow">公钥地址</th>
                    <th>发电量/用电量（度)</th>
                    <th>能源类型</th>
                    <th>电表编号 </th>
                    <th>电度数</th>
                    <th>所属调度地区</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody id="mytable">
                <c:forEach items="${list}" var="user" varStatus="vs">
                <tr>
                    <c:if test="${user.user_type==0}">
                        <td>发电用户</td>
                    </c:if>
                    <c:if test="${user.user_type==1}">
                        <td>用电用户</td>
                    </c:if>
                    <td><a href="" class="heightLight_txt" data-target="#personal_msg" data-toggle="modal">${user.user_name}</a></td>
                    <td>${user.phone_no}</td>
                    <td class="text_overflow" title="${user.user_pukey}">
                            ${user.user_pukey}
                    </td>
                    <td>${user.electric_quantity}</td>
                    <c:if test="${user.energy_type==1}">
                        <td>电峰</td>
                    </c:if>
                    <c:if test="${user.energy_type==2}">
                        <td>电谷</td>
                    </c:if>
                    <c:if test="${user.energy_type==3}">
                        <td>电平</td>
                    </c:if>
                    <c:if test="${user.energy_type==4}">
                        <td>代币</td>
                    </c:if>
                    <input type="hidden" id="ty" value="${user.energy_type}"/>
                    <td>${user.ammeter_no}</td>
                    <td>${user.ele_no}</td>
                    <td>${user.dispatch_area}</td>
                    <td>

                        <a href="" class="heightLight_txt pull-left" data-target="#personal_msg" data-toggle="modal" onclick="update(this);" id="sx">授信</a>
                        <%--<a href="" class="heightLight_txt pull-right" data-toggle="modal" data-target="#tixian" >审核提现</a>--%>
                    </td>
                    <%--<input type="hidden" id="ugkey" value="GBO43A3W4ZQHQUDWHJSLORWKCBEFX4KVSJLVSPSS6VIJOIK5VTJZUSNQ"/>--%>

                </tr>
                </c:forEach>
                </tbody>
            </table>
            <nav aria-label="..." class="pull-right">
                <ul class="pagination">
                    <li><a href="#" aria-label="Previous"><span aria-hidden="true"> << </span></a></li>
                    <li><a href="#" aria-label="Previous"><span aria-hidden="true"> < </span></a></li>
                    <li class="active"><a href="#">1 <span class="sr-only">(current)</span></a></li>
                    <li><a href="#">2 <span class="sr-only">(current)</span></a></li>
                    <li><a href="#">3 <span class="sr-only">(current)</span></a></li>
                    <li><a href="#">4 <span class="sr-only">(current)</span></a></li>
                    <li><a href="#">5 <span class="sr-only">(current)</span></a></li>
                    <li><a href="#">6 <span class="sr-only">(current)</span></a></li>
                    <li><a href="#" aria-label="Previous"><span aria-hidden="true"> > </span></a></li>
                    <li><a href="#" aria-label="Previous"><span aria-hidden="true"> >> </span></a></li>
                </ul>
            </nav>

        </div>
        <%--</iframe>--%>
        <div class="padBottom"></div>
    </div>




    <!--发行电资产-->
    <div role="tabpanel" class="tab-pane fade ld_content" id="publish">
        <h3 class="content_title margin_top20">发行电资产 <span class="content_tips">(您最多可以买1000度电)</span></h3>
        <div class="ld_content_list  margin_top20">
            <div class="clearfix">
                <div class="pull-left clearfix  margin_bottom18" id="faxingzichan">
                    <div class="leixing pull-left">电类型：
                        <select id="zctype" >
                            <option value="PING" selected="selected">电平</option>
                            <option value="FENG">电峰</option>
                            <option value="GU">电谷</option>
                            <option value="DAIBI">代币</option>
                        </select>
                    </div>
                    <div class="pull-left dianliang">电量：
                        <div class="clearfix display_inlineBlock">
                            <input type="text" name="" id="num" value="" class="div_input" />
                            <!--<span class="pull-left">100000</span>-->
                            <span class="setDanwei">度</span>
                        </div>
                    </div>
                    <div class="pull-left yonghu">售电用户：<div class="clearfix display_inlineBlock">
                        <input type="text" class="div_input" id="sdgkey" value="" />
                    </div></div>
                </div>

                <div class="pull-right text-right">
                    <input type="button" name="" onclick="faxing();" value="发行" class="ld_btn ld_btn_margin margin_top0" />
                </div>
            </div>

        </div>

    </div>

    <!--交易情况-->
    <div role="tabpanel" class="tab-pane fade ld_content" id="transaction">
        <h3 class="content_title">交易情况 <span class="content_tips">(包含本地区和跨地区)</span></h3>
        <div class="ld_content_list  margin_top20">
            <div class="clearfix">
                <div class="pull-left clearfix  margin_bottom18" id="faxingzichan">

                    <div class="pull-left dianliang">调度账户：
                        <div class="clearfix display_inlineBlock">
                            <input type="text" class="div_input" />
                        </div>
                    </div>
                    <div class="pull-left yonghu">调度地区：<div class="clearfix display_inlineBlock"><input type="text" class="div_input" /></div></div>
                    <div class="leixing pull-left">交易类型：
                        <select name="" >
                            <option value="本地区" selected="selected">本地区</option>
                        </select>
                    </div>
                </div>
                <div class="pull-right text-right">
                    <input type="button" name="" onclick="query();" value="查询" class="ld_btn ld_btn_margin margin_top0" />
                </div>
            </div>

        </div>
        <div class="ld_content_list margin_top20" id="transaction_tabel">
            <table class="table text-center table-striped">
                <thead>
                <tr>
                    <th class="text-center">交易类型</th>
                    <th class="text-center">调度账户</th>
                    <th class="text-center">调度地区</th>
                    <th class="text-center">交易电量(W)</th>
                    <th class="text-center">交易用户</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>本地区</td>
                    <td>调度账户</td>
                    <td>调度地区</td>
                    <td>10000</td>
                    <td>用户A</td>
                </tr>
                <tr>
                    <td>本地区</td>
                    <td>调度账户</td>
                    <td>调度地区</td>
                    <td>10000</td>
                    <td>用户A</td>
                </tr>
                <tr>
                    <td>本地区</td>
                    <td>调度账户</td>
                    <td>调度地区</td>
                    <td>10000</td>
                    <td>用户A</td>
                </tr>
                <tr>
                    <td>本地区</td>
                    <td>调度账户</td>
                    <td>调度地区</td>
                    <td>10000</td>
                    <td>用户A</td>
                </tr>
                <tr>
                    <td>本地区</td>
                    <td>调度账户</td>
                    <td>调度地区</td>
                    <td>10000</td>
                    <td>用户A</td>
                </tr>
                <tr>
                    <td>本地区</td>
                    <td>调度账户</td>
                    <td>调度地区</td>
                    <td>10000</td>
                    <td>用户A</td>
                </tr>
                <tr>
                    <td>本地区</td>
                    <td>调度账户</td>
                    <td>调度地区</td>
                    <td>10000</td>
                    <td>用户A</td>
                </tr>
                <tr>
                    <td>本地区</td>
                    <td>调度账户</td>
                    <td>调度地区</td>
                    <td>10000</td>
                    <td>用户A</td>
                </tr><tr>
                    <td>本地区</td>
                    <td>调度账户</td>
                    <td>调度地区</td>
                    <td>10000</td>
                    <td>用户A</td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="padBottom"></div>
    </div>

</div>
<div class="ld_footer text-center">
    Copyright ©2013-2017  链电网络  粤ICP备10011451号-6
</div>

<!-- 提现Modal -->
<div class="modal fade" id="tixian" tabindex="-1" role="dialog" aria-labelledby="tixianModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="tixianModalLabel">提现TEE</h4>
            </div>
            <div class="modal-body">
                <form class="form-inline">
                    <div class="form-group margin_top20">
                        <label for="zhuanzhangdizhi">转账地址：</label>
                        <input type="text" class="form-control input_type2" id="zhuanzhangdizhi" value="DX4F7J2CWRD5UM5GPIFQW7UAYEKKNRQOFRDDGTFJSBBCFAMZACOFUII">
                    </div>
                    <div class="form-group margin_top20">
                        <label for="dianleixing">电类型：</label>
                        <select name="" id="" class="input_type1 form-control">
                            <option value="">aaaa</option>
                        </select>
                    </div>
                    <div class="form-group margin_top20">
                        <label for="diandushu">电度数：</label>
                        <input type="text" class="form-control input_type1" id="diandushu" placeholder="请输入电度数">
                    </div>
                    <div class="form-group margin_top20">
                        <label for="beizhu">备注：</label>
                        <textarea class="form-control input_type2" rows="5" placeholder="请输入您的备注内容"></textarea>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="ld_btn padding_l_r">添加</button>
                <button type="button" class="ld_btn btn_default padding_l_r"  data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<!-- 添加用户Modal -->
<div class="modal fade" id="addUser" tabindex="-1" role="dialog" aria-labelledby="addUserModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="addUserModalLabel">添加新用户</h4>
            </div>
            <div class="modal-body">
                <form class="form-inline">
                    <div class="form-group margin_top20">
                        <label for="user_type">用户类型：</label>
                        <select name="" id="user_type" class="type1">
                            <option value="yd">用电用户</option>
                            <option value="fd">发电用户</option>
                        </select>
                    </div>
                    <div class="form-group margin_top20">
                        <label for="user_name">用户姓名：</label>
                        <input type="text" class="type1" id="user_name" placeholder="请输入姓名">
                    </div>
                    <div class="form-group margin_top20">
                        <label for="user_car">身份证号：</label>
                        <input type="text" class="type1" id="user_car" placeholder="请输入身份证号码">
                    </div>
                    <div class="form-group margin_top20">
                        <label for="user_phone">手机号码：</label>
                        <input type="text" class="type1" id="user_phone" placeholder="请输入手机号码">
                    </div>
                    <div class="form-group margin_top20">
                        <label for="user_phone">公钥地址：</label>
                        <input type="text" class="type1" id="user_gkey" placeholder="请输入公钥地址">
                    </div>
                    <div class="form-group margin_top20">
                        <label for="user_fadian">发电量/用电量（度）：</label>
                        <input type="text" class="type2" id="user_fadian" placeholder="请输入用电量/发电量">
                    </div>
                    <div class="form-group margin_top20">
                        <label for="user_nengyuan">能源类型：</label>
                        <select name="" id="user_nengyuan" class="type1">
                            <option value="FENG">电峰</option>
                            <option value="PING">电平</option>
                            <option value="GU">电谷</option>
                            <option value="DAIBI">代币</option>
                        </select>
                    </div>
                    <div class="form-group margin_top20">
                        <label for="user_yongdian">电表编号：</label>
                        <input type="text" class="type1" id="user_dbnum" placeholder="请输入电表编号">
                    </div>
                    <div class="form-group margin_top20">
                        <label for="dianleixing">所属调度地区：</label>
                        <select name="" id="ddarea" class="type3">
                            <option value="gz">广州</option>
                        </select>
                    </div>
                    <div class="form-group margin_top20">
                        <div class="clearfix" id="addUser_xieyi">
                            <span class="pull-left">签订协议上传：</span>
                            <a href="javescript:;" class="xieyi text-center pull-left">+<input type="file" id="file" value=""/></a>
                        </div>

                    </div>

                </form>
            </div>
            <div class="modal-footer text-center">
                <button type="button" class="ld_btn padding_l_r" onclick="BrowseFolder();">添加</button>
                <button type="button" class="ld_btn btn_default padding_l_r"  data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<!-- 个人信息Modal -->
<div class="modal fade" id="personal_msg" tabindex="-1" role="dialog" aria-labelledby="personal_msgModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="personal_msgModalLabel">个人信息</h4>
            </div>
            <div class="modal-body">
                <p>用户姓名：张三</p>
                <p>身份证：441887896657845217</p>
                <p>手机号：18765489523</p>
                <p>位置信息：（经度：113.352623, 纬度：23.146964）</p>
                <div class="ld_content">
                    <h3 class="content_title">签订协议：</h3>
                    <div class="xieyi_img text-center">
                        <img src="../views/img/xieyi.fw.png"/>
                    </div>
                </div>
            </div>

        </div>
    </div>

    <script src="../views/lib/jquery.min.js"></script>
    <script src="../views/lib/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <script src="../views/js/common.js"></script>
    <script src="../views/layer_mobile/layer.js"></script>
    <script>
        var banner = $(".ld_banner");
        //	$(".ld_banner").height(bannerHeight);
        setEleHeight(banner,contentHeight);

        //		    setminHeight($(".tab-content"),contentHeight);

        $(".tab-pane").each(function(i,ele){
            setminHeight($(ele),contentHeight);
        });

        function query1() {
            var usertype = document.getElementById("usertype").value;
            var gkey = document.getElementById("gkey").value;
            var skey = document.getElementById("skey").value;
            window.location.href = "../../userinfo/getinfo1.action?usertype="+usertype+"&skey="+skey+"&gkey="+gkey;
        }
        
        function faxing() {
            var zctype = document.getElementById("zctype").value;
            var num = document.getElementById("num").value;
            var sdgkey = document.getElementById("sdgkey").value;
            var gkey = document.getElementById("gkey").value;
            if(num==null || num==""){
                alert("电量不可为空！");return false;
            }
            if(sdgkey==null || sdgkey==""){
                alert("售电用户不可为空！");return false;
            }
            $.ajax({
                type: "POST",
                timeout: 3000,
                async: false,
                cache: false,
                data:{zctype:zctype,num:num,sdgkey:sdgkey,gkey:gkey},//参数
                url: "../../operation/ddsetUp.action",
                success: function (data){
                    if(data=="OK"){
                        alert("发行成功！");
                    }else{
                        alert("发行失败！");
                    }
                }
            });
        }


        function BrowseFolder(){
            var usertype = document.getElementById("user_type").value;
            var username = document.getElementById("user_name").value;
            var usercar = document.getElementById("user_car").value;
            var userphone = document.getElementById("user_phone").value;
            var usergkey = document.getElementById("user_gkey").value;
            var usernum = document.getElementById("user_fadian").value;
            var usernengyuan = document.getElementById("user_nengyuan").value;
            var userdbnum = document.getElementById("user_dbnum").value;
            var file = document.getElementById("file").value;
            var ddarea = document.getElementById("ddarea").value;

            if(username==null || username==""){
                alert("请输入用户名！");return false;
            }
            if(usercar==null || usercar==""){
                alert("请输入用户身份证号！");return false;
            }
            if(userphone==null || userphone==""){
                alert("请输入用户手机号！");return false;
            }
            if(usergkey==null || usergkey==""){
                alert("请输入用户公钥！");return false;
            }
            if(usernum==null || usernum==""){
                alert("请输入用户用电量/发电量！");return false;
            }
            if(userdbnum==null || userdbnum==""){
                alert("请输入用户电表编号！");return false;
            }

            $.ajax({
                type: "POST",
                timeout: 3000,
                async: false,
                cache: false,
                data:{
                    usertype:usertype,
                    username:username,
                    usercar:usercar,
                    userphone:userphone,
                    usergkey:usergkey,
                    usernum:usernum,
                    usernengyuan:usernengyuan,
                    userdbnum:userdbnum,
                    file:file,
                    ddarea:ddarea
                },//参数
                url: "../../../user/insert.action",
                success: function (data){
                    if(data=="NO"){
                        alert("添加失败!");
                    }else{
                        alert("添加成功！");
                    }
                }
            });


        }

        function update(data){

            var tr = $(data).parent().parent();//tr对象

            var tdcon = [];//本行所有数据

            tr.find('td').each(function(i,td){

                if($(td).find('a').length == 0){//过滤修改列

                    tdcon.push($(td).html());

                }

            });
            var skey = document.getElementById("skey").value;
            var usergkey = tdcon[2];
            var ty = document.getElementById("ty").value;
            if(ty=="1"){
                ty="FENG";
            }else if(ty=="2"){
                ty="GU";
            }else if(ty=="3"){
                ty="PING";
            }else if(ty=="4"){
                ty="DAIBI";
            }
            $.ajax({
                type: "POST",
                timeout: 3000,
                async: false,
                cache: false,
                data:{skey:skey,usergkey:usergkey,ty:ty},//参数
                url: "../../operation/setUser.action",
                success: function (data){
                    if(data=="NO"){
                        alert("授权失败!");
                    }else{
                        alert("授权成功！");
                    }
                }
            });

        }

        function setUser() {
            var count=document.getElementById("table").rows.length;
            alert(count);return false;
            var skey = document.getElementById("skey").value;
            var usergkey = document.getElementById("ugkey").value;
            var ty = document.getElementById("ty").value;
            alert(usergkey);return false;
            if(ty=="1"){
                ty="FENG";
            }else if(ty=="2"){
                ty="GU";
            }else if(ty=="3"){
                ty="PING";
            }else if(ty=="4"){
                ty="DAIBI";
            }
            layer.open({
                type:2,
                //shade:false
            })
            $.ajax({
                type: "POST",
                timeout: 3000,
                async: false,
                cache: false,
                data:{skey:skey,usergkey:usergkey,ty:ty},//参数
                url: "../../operation/setUser.action",
                success: function (data){
                    layer.closeAll();
                    if(data=="NO"){
                        alert("授权失败!");
                    }else{
                        alert("授权成功！");
                    }
                }
            });
        }
        
        function query() {
            var usertype = document.getElementById("usertype").value;
            var area = document.getElementById("area").value;
            window.location.href = "";
        }
    </script>
</body>
</html>
