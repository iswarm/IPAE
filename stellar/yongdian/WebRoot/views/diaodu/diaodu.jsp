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
            <li><a href="javascript:;"><span class="glyphicon glyphicon-user"></span>GDX4F7*****FAMZACOFUII</a></li>
            <li><a href="javascript:;"><span class="glyphicon glyphicon-log-out"></span>注销</a></li>
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
        <h3 class="content_title">授信用户 <span class="content_tips">  (您最多可以买1000度电)</span></h3>
        <div class="ld_content_list margin_top20 clearfix margin_bottom18">
            <div class="pull-left clearfix">
                <div class="pull-left">
                    <div class="leixing pull-left">用户类型：
                        <select name="" >
                            <option value="平" selected="selected">用电用户</option>
                            <option value="贵">贵</option>
                        </select>
                    </div>
                </div>
                <div class="leixing pull-left">调度地区：
                    <select name="" >
                        <option value="平" selected="selected">调度地区</option>
                        <option value="贵">贵</option>
                    </select>
                </div>
            </div>

            <div class="pull-right text-right margin_bottom18">
                <input type="button" name="" id="" value="查询" class="ld_btn ld_btn_margin margin_top0 padding_l_r" data-toggle="modal" data-target="#select"/>
                <input type="button" name="" id="" value="添加" class="ld_btn ld_btn_margin margin_top0 padding_l_r" data-toggle="modal" data-target="#addUser" />
            </div>
        </div>

        <div class="ld_content_list padding_l_r clearfix" id="diaodu_shouxin">
            <table class="table table-striped text-center">
                <thead>
                <tr>
                    <th>用户类型</th>
                    <th>用户姓名</th>
                    <th>手机号</th>
                    <th>发电量/用电量（度)</th>
                    <th>能源类型</th>
                    <th>电表编号 </th>
                    <th>电度数</th>
                    <th>所属调度地区</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>用电用户</td>
                    <td>张三</td>
                    <td>18718064782</td>
                    <td>100000</td>
                    <td>电能</td>
                    <td>D1231574744</td>
                    <td>5000</td>
                    <td>调度地区</td>
                    <td>
                        <a href="" class="heightLight_txt pull-left" data-target="#personal_msg" data-toggle="modal">授信</a>
                        <a href="" class="heightLight_txt pull-right" data-toggle="modal" data-target="#tixian" >审核提现</a>
                    </td>
                </tr>
                <tr>
                    <td>用电用户</td>
                    <td>张三</td>
                    <td>18718064782</td>
                    <td>100000</td>
                    <td>电能</td>
                    <td>D1231574744</td>
                    <td>5000</td>
                    <td>调度地区</td>
                    <td>
                        <a href="" class="heightLight_txt pull-left" data-target="#personal_msg" data-toggle="modal">授信</a>
                        <a href="" class="heightLight_txt pull-right" data-toggle="modal" data-target="#tixian" >审核提现</a>
                    </td>
                </tr>
                <tr>
                    <td>用电用户</td>
                    <td>张三</td>
                    <td>18718064782</td>
                    <td>100000</td>
                    <td>电能</td>
                    <td>D1231574744</td>
                    <td>5000</td>
                    <td>调度地区</td>
                    <td>
                        <a href="" class="heightLight_txt pull-left" data-target="#personal_msg" data-toggle="modal">授信</a>
                        <a href="" class="heightLight_txt pull-right" data-toggle="modal" data-target="#tixian" >审核提现</a>
                    </td>
                </tr>
                <tr>
                    <td>用电用户</td>
                    <td>张三</td>
                    <td>18718064782</td>
                    <td>100000</td>
                    <td>电能</td>
                    <td>D1231574744</td>
                    <td>5000</td>
                    <td>调度地区</td>
                    <td>
                        <a href="" class="heightLight_txt pull-left" data-target="#personal_msg" data-toggle="modal">授信</a>
                        <a href="" class="heightLight_txt pull-right" data-toggle="modal" data-target="#tixian" >审核提现</a>
                    </td>
                </tr>
                <tr>
                    <td>用电用户</td>
                    <td>张三</td>
                    <td>18718064782</td>
                    <td>100000</td>
                    <td>电能</td>
                    <td>D1231574744</td>
                    <td>5000</td>
                    <td>调度地区</td>
                    <td>
                        <a href="" class="heightLight_txt pull-left" data-target="#personal_msg" data-toggle="modal">授信</a>
                        <a href="" class="heightLight_txt pull-right" data-toggle="modal" data-target="#tixian" >审核提现</a>
                    </td>
                </tr>
                <tr>
                    <td>用电用户</td>
                    <td>张三</td>
                    <td>18718064782</td>
                    <td>100000</td>
                    <td>电能</td>
                    <td>D1231574744</td>
                    <td>5000</td>
                    <td>调度地区</td>
                    <td>
                        <a href="" class="heightLight_txt pull-left" data-target="#personal_msg" data-toggle="modal">授信</a>
                        <a href="" class="heightLight_txt pull-right" data-toggle="modal" data-target="#tixian" >审核提现</a>
                    </td>
                </tr>
                <tr>
                    <td>用电用户</td>
                    <td>张三</td>
                    <td>18718064782</td>
                    <td>100000</td>
                    <td>电能</td>
                    <td>D1231574744</td>
                    <td>5000</td>
                    <td>调度地区</td>
                    <td>
                        <a href="" class="heightLight_txt pull-left" data-target="#personal_msg" data-toggle="modal">授信</a>
                        <a href="" class="heightLight_txt pull-right" data-toggle="modal" data-target="#tixian" >审核提现</a>
                    </td>
                </tr>
                <tr>
                    <td>用电用户</td>
                    <td>张三</td>
                    <td>18718064782</td>
                    <td>100000</td>
                    <td>电能</td>
                    <td>D1231574744</td>
                    <td>5000</td>
                    <td>调度地区</td>
                    <td>
                        <a href="" class="heightLight_txt pull-left" data-target="#personal_msg" data-toggle="modal">授信</a>
                        <a href="" class="heightLight_txt pull-right" data-toggle="modal" data-target="#tixian" >审核提现</a>
                    </td>
                </tr>
                <tr>
                    <td>用电用户</td>
                    <td>张三</td>
                    <td>18718064782</td>
                    <td>100000</td>
                    <td>电能</td>
                    <td>D1231574744</td>
                    <td>5000</td>
                    <td>调度地区</td>
                    <td>
                        <a href="" class="heightLight_txt pull-left" data-target="#personal_msg" data-toggle="modal">授信</a>
                        <a href="" class="heightLight_txt pull-right" data-toggle="modal" data-target="#tixian" >审核提现</a>
                    </td>
                </tr>
                <tr>
                    <td>用电用户</td>
                    <td>张三</td>
                    <td>18718064782</td>
                    <td>100000</td>
                    <td>电能</td>
                    <td>D1231574744</td>
                    <td>5000</td>
                    <td>调度地区</td>
                    <td>
                        <a href="" class="heightLight_txt pull-left" data-target="#personal_msg" data-toggle="modal">授信</a>
                        <a href="" class="heightLight_txt pull-right" data-toggle="modal" data-target="#tixian" >审核提现</a>
                    </td>
                </tr>
                <tr>
                    <td>用电用户</td>
                    <td>张三</td>
                    <td>18718064782</td>
                    <td>100000</td>
                    <td>电能</td>
                    <td>D1231574744</td>
                    <td>5000</td>
                    <td>调度地区</td>
                    <td>
                        <a href="" class="heightLight_txt pull-left" data-target="#personal_msg" data-toggle="modal">授信</a>
                        <a href="" class="heightLight_txt pull-right" data-toggle="modal" data-target="#tixian" >审核提现</a>
                    </td>
                </tr>

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

        <div class="padBottom"></div>
    </div>




    <!--发行电资产-->
    <div role="tabpanel" class="tab-pane fade ld_content" id="publish">
        <h3 class="content_title margin_top20">发行电资产 <span class="content_tips">(您最多可以买1000度电)</span></h3>
        <div class="ld_content_list  margin_top20">
            <div class="clearfix">
                <div class="pull-left clearfix  margin_bottom18" id="faxingzichan">
                    <div class="leixing pull-left">电类型：
                        <select name="" >
                            <option value="平" selected="selected">平</option>
                            <option value="贵">贵</option>
                        </select>
                    </div>
                    <div class="pull-left dianliang">电量：
                        <div class="clearfix display_inlineBlock">
                            <input type="text" name="" id="" value="100000" class="div_input" />
                            <!--<span class="pull-left">100000</span>-->
                            <span class="setDanwei">度</span>
                        </div>
                    </div>
                    <div class="pull-left yonghu">售电用户：<div class="clearfix display_inlineBlock">
                        <input type="text" class="div_input" value="" />
                    </div></div>
                </div>

                <div class="pull-right text-right">
                    <input type="button" name="" id="" value="发行" class="ld_btn ld_btn_margin margin_top0" />
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
                    <input type="button" name="" id="" value="查询" class="ld_btn ld_btn_margin margin_top0" />
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
                            <option value="">aaaa</option>
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
                            <option value="">光伏电能</option>
                        </select>
                    </div>
                    <div class="form-group margin_top20">
                        <label for="user_yongdian">电表编号：</label>
                        <input type="text" class="type1" id="user_yongdian" placeholder="请输入电表编号">
                    </div>
                    <div class="form-group margin_top20">
                        <label for="dianleixing">所属调度地区：</label>
                        <select name="" id="" class="type3">
                            <option value="">调度地区S</option>
                        </select>
                    </div>
                    <div class="form-group margin_top20">
                        <div class="clearfix" id="addUser_xieyi">
                            <span class="pull-left">签订协议上传：</span>
                            <a href="javescript:;" class="xieyi text-center pull-left">+<input type="file" value="" /></a>
                        </div>

                    </div>

                </form>
            </div>
            <div class="modal-footer text-center">
                <button type="button" class="ld_btn padding_l_r">添加</button>
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
                        <img src="../../img/xieyi.fw.png"/>
                    </div>
                </div>
            </div>

        </div>
    </div>

    <script src="../views/lib/jquery.min.js"></script>
    <script src="../views/lib/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <script src="../views/js/common.js"></script>
    <script>
        var banner = $(".ld_banner");
        //	$(".ld_banner").height(bannerHeight);
        setEleHeight(banner,contentHeight);

        //		    setminHeight($(".tab-content"),contentHeight);

        $(".tab-pane").each(function(i,ele){
            setminHeight($(ele),contentHeight);
        });

    </script>
</body>
</html>
