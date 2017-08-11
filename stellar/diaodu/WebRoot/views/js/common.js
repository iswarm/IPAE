//	设置高度
	function setEleHeight(ele,eleHeight){
		ele.height(eleHeight);
	}
//	设置页面高度
	function setbodyHeight(windowWidth,windowHeight){
		$("body").width(windowWidth).height(windowHeight);
	}
//	设置最小高度
	function setminHeight(ele,minHeight){
		ele.css("min-height",minHeight);
	}
	
	//屏幕大小改变，重载页面
    $(window).resize(function(){
	    location.reload()
	});
	
	//	获取
    var windowHeight= $(document).height();
	var windowWidth = $(document).width();
	var headerHeight=$(".ld_header").height();
	var logoHeight=$(".ld_logo").height();
	var footerHeight=$(".ld_footer").height();
	var contentHeight=windowHeight-headerHeight-logoHeight-footerHeight;
	
	//导航栏
//	点击移除导航类
	$(".ld_logo li").each(function(i,ele){
		$(ele).click(function(){
			$(".ld_active").removeClass("ld_active");
		})
	})
