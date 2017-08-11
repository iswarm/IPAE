$(function(){
	
	var banner = $(".ld_banner");
//	$(".ld_banner").height(bannerHeight);
//  setbodyHeight(windowWidth,windowHeight);
    setEleHeight(banner,contentHeight);

    //显示选择密钥路径
    $("#file").change(function(){
        var file = $(this).val();
        console.log(file);
        var showFile = $("#showFile");
        showFile.html(file);
        showFile.attr("title",file);
    });

    $("#toCreate").click(function(){
        $("#login").css("display","none");
        $("#createNewAccount").css("display","block");
    })
});