<script type="text/javascript" src="http://s.maiyaole.com/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="http://s.maiyaole.com/js/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript" src="http://s.maiyaole.com/js/jquery.ui.datepicker-zh-CN.js"></script>
<script type="text/javascript" src="http://s.maiyaole.com/js/model-alert1.js"></script>
<script type="text/javascript" src="http://s.maiyaole.com/js/public.js"></script>
<script type="text/javascript" src="http://s.maiyaole.com/js/util.js"></script>
<script type="text/javascript" src="http://s.maiyaole.com/js/ajaxfileupload.js"></script>


<script type="text/javascript">
$(document).ready(function () {	
	$('#nav dl').css({"display":"none"})
	$('#nav li').hover(
		function () {
			$(this).addClass("now");
			$('dl', this).show();
		}, 
		function () {
			$(this).removeClass("now");
			$('dl', this).hide();			
		}
	);
});
</script>

<script type="text/javascript">	
	$(document).ready(function(){		
	//hide
	closeLeft();
	   $("#showitems").attr("hide","h");
		$("#showpin").attr("hide","h");
		$("#showmuban").attr("hide","h");
		$("#showorder").attr("hide","h");
		$("#showkui").attr("hide","h");
		$("#showquan").attr("hide","h");
		$("#showyan").attr("hide","h");
		$("#showgao").attr("hide","h");
		$("#showuser").attr("hide","h");
		$("#showapply").attr("hide","h");
		$("#showcampay").attr("hide","h");
		$("#showping").attr("hide","h"); 
		$("#showaudit").attr("hide","h");
		$("#showcx").attr("hide","h");
		$("#showpt").attr("hide","h");
		
	function openMenu(classid,domid){
	
		closeLeft();
		closeimg();
		//$(".slideitems").toggle();
		if($("#"+domid).attr("hide")=='h'){
		 $("#showitems").attr("hide","h");
		$("#showpin").attr("hide","h");
		$("#showmuban").attr("hide","h");
		$("#showorder").attr("hide","h");
		$("#showkui").attr("hide","h");
		$("#showquan").attr("hide","h");
		$("#showyan").attr("hide","h");
		$("#showgao").attr("hide","h");
		$("#showuser").attr("hide","h");
		$("#showapply").attr("hide","h");
		$("#showcampay").attr("hide","h"); 
        $("#showping").attr("hide","h");
		$("#showaudit").attr("hide","h");
		$("#showcx").attr("hide","h");
		$("#showpt").attr("hide","h");
		
		$("."+classid).show();	
		var simg="http://p1.maiyaole.com/images/nav_0.gif";
		$("#"+domid).parents("td").prev().children("img").attr("src",simg);
		var param=$("#"+domid).attr("id");	
		$("#"+domid).attr("hide","s");
		}else if($("#"+domid).attr("hide")=='s'){
		$("."+classid).hide();	
		var simg="http://p1.maiyaole.com/images/nav_1.gif";
		$("#"+domid).parents("td").prev().children("img").attr("src",simg);
		var param=$("#"+domid).attr("id");	
		setCookie("daohang",param);
		$("#"+domid).attr("hide","h");
		
		}
	}
	/*
	function closeMenu(classid,domid){
		closeLeft();
		closeimg();
		$("#"+domid).one("click", function(){
			openMenu(classid,domid);
		});
	}*/
	
	$("#showitems").bind("click", function(){
		openMenu("slideitems","showitems");
	});
	$("#showpin").bind("click", function(){
		openMenu("slidepin","showpin");
	});
	$("#showmuban").bind("click", function(){
		openMenu("slidemuban","showmuban");
	});
	$("#showorder").bind("click", function(){
		openMenu("slideorder","showorder");
		
	});
	$("#showkui").bind("click", function(){
		openMenu("slidekui","showkui");
	});
	$("#showquan").bind("click", function(){
		openMenu("slidequan","showquan");
	});
	$("#showyan").bind("click", function(){
		openMenu("slideyan","showyan");
	});
	$("#showgao").bind("click", function(){
		openMenu("slidegao","showgao");
	});
	$("#showuser").bind("click", function(){
		openMenu("slideuser","showuser");
	});
	$("#showapply").bind("click", function(){
		openMenu("slideapply","showapply");
	});
	$("#showcampay").bind("click", function(){
		openMenu("slidecampay","showcampay");
	});
	$("#showping").bind("click", function(){
		openMenu("slideping","showping");
	});
	$("#showaudit").bind("click", function(){
		openMenu("slideaudit","showaudit");
	});
	$("#showcx").bind("click", function(){
		openMenu("slidecx","showcx");
	});
	$("#showpt").bind("click", function(){
		openMenu("slidept","showpt");
	});
	//show
	/*$("#showitems").click(function(){
		if(!$(this).attr("hide")) {
			closeLeft();
			closeimg();
			//$(".slideitems").toggle();
			$(".slideitems").show();
			var simg="http://p1.maiyaole.com/images/nav_0.gif";
			$("#showitems").parents("td").prev().children("img").attr("src",simg);
			var param=$(this).attr("id");		
			setCookie("daohang",param);
			$(this).attr("hide","t");
		} else { 
			closeLeft();
			closeimg();
			$(this).attr("hide",null);
		}
	});*/
	
	/*
	
	$("#showpin").click(function(){
		closeLeft();
		closeimg();
		$(".slidepin").show();
		var simg="http://p1.maiyaole.com/images/nav_0.gif";
		$("#showpin").parents("td").prev().children("img").attr("src",simg);
		var param=$(this).attr("id");
		setCookie("daohang",param);
	});
	*/
	
	/*
	$("#showmuban").click(function(){
		closeLeft();
		closeimg();
		$(".slidemuban").show();
		var simg="http://p1.maiyaole.com/images/nav_0.gif";
		$("#showmuban").parents("td").prev().children("img").attr("src",simg);
		var param=$(this).attr("id");
		setCookie("daohang",param);
	});
	*/
	/*
	
	$("#showorder").click(function(){
		closeLeft();
		closeimg();
		$(".slideorder").show();
		var simg="http://p1.maiyaole.com/images/nav_0.gif";
		$("#showorder").parents("td").prev().children("img").attr("src",simg);
		var param=$(this).attr("id");
		setCookie("daohang",param);
	});
	$("#showkui").click(function(){
		closeLeft();
		closeimg();
		$(".slidekui").show();
		var simg="http://p1.maiyaole.com/images/nav_0.gif";
		$("#showkui").parents("td").prev().children("img").attr("src",simg);
		var param=$(this).attr("id");
		setCookie("daohang",param);
	});
	$("#showquan").click(function(){
		closeLeft();
		closeimg();
		$(".slidequan").show();
		var simg="http://p1.maiyaole.com/images/nav_0.gif";
		$("#showquan").parents("td").prev().children("img").attr("src",simg);
		var param=$(this).attr("id");
		setCookie("daohang",param);
	});
	$("#showyan").click(function(){
		closeLeft();
		closeimg();
		$(".slideyan").show();
		var simg="http://p1.maiyaole.com/images/nav_0.gif";
		$("#showyan").parents("td").prev().children("img").attr("src",simg);
		var param=$(this).attr("id");
		setCookie("daohang",param);
	});
	$("#showgao").click(function(){
		closeLeft();
		closeimg();
		$(".slidegao").show();
		var simg="http://p1.maiyaole.com/images/nav_0.gif";
		$("#showgao").parents("td").prev().children("img").attr("src",simg);
		var param=$(this).attr("id");
		setCookie("daohang",param);
	});
	$("#showuser").click(function(){
		closeLeft();
		closeimg();
		$(".slideuser").show();
		var simg="http://p1.maiyaole.com/images/nav_0.gif";
		$("#showuser").parents("td").prev().children("img").attr("src",simg);
		var param=$(this).attr("id");
		setCookie("daohang",param);
	});
	$("#showapply").click(function(){
		closeLeft();
		closeimg();
		$(".slideapply").show();
		var simg="http://p1.maiyaole.com/images/nav_0.gif";
		$("#showapply").parents("td").prev().children("img").attr("src",simg);
		var param=$(this).attr("id");
		setCookie("daohang",param);
	});
	*/
	//end
	function closeLeft(){
		//$(".slideitems").toggle();
		$(".slideitems").hide();
		$(".slidepin").hide();
		$(".slidemuban").hide();
		$(".slideorder").hide();
		$(".slidekui").hide();
		$(".slidequan").hide();
		$(".slideyan").hide();
		$(".slidegao").hide();
		$(".slideuser").hide();
		$(".slideapply").hide();
		$(".slidecampay").hide();
		$(".slideping").hide();
		$(".slideaudit").hide();
		$(".slidecx").hide();
		$(".slidept").hide();
	}
	function closeimg(){
		var simg="http://p1.maiyaole.com/images/nav_1.gif";
		
		$("#showitems").parents("td").prev().children("img").attr("src",simg);
		$("#showpin").parents("td").prev().children("img").attr("src",simg);
		$("#showmuban").parents("td").prev().children("img").attr("src",simg);
		$("#showorder").parents("td").prev().children("img").attr("src",simg);
		$("#showkui").parents("td").prev().children("img").attr("src",simg);
		$("#showquan").parents("td").prev().children("img").attr("src",simg);
		$("#showyan").parents("td").prev().children("img").attr("src",simg);
		$("#showgao").parents("td").prev().children("img").attr("src",simg);
		$("#showuser").parents("td").prev().children("img").attr("src",simg);
		$("#showapply").parents("td").prev().children("img").attr("src",simg);
		$("#showcampay").parents("td").prev().children("img").attr("src",simg);
		$("#showping").parents("td").prev().children("img").attr("src",simg);
		$("#showaudit").parents("td").prev().children("img").attr("src",simg);
		$("#showcx").parents("td").prev().children("img").attr("src",simg);
		$("#showpt").parents("td").prev().children("img").attr("src",simg);
	}
	});
	</script>
	
	<script type="text/javascript">
	function setCookie(name,value)
	{	    
	    var exp  = new Date();    
	    exp.setTime(exp.getTime() + 5*24*60*60*1000);
	    document.cookie=name+"="+ escape(value)+";expires="+exp.toGMTString()+";path=/admin";
	    
	}
	function clickchild(obj){		
		setCookie("daochild",obj.id);
	}
	
    </script>
   <script type="text/javascript">
    $(document).ready(function(){
        // dao hang cookie
        getCookie();
    });

	function showCookie(name){
	
	    var arr = document.cookie.match(new RegExp("(^| )"+name+"=([^;]*)(;|$)"));
	     if(arr != null) 
		     return unescape(arr[2]); 
	     return null;
	}
    
    function getCookie(){		
		
		var param=showCookie("daohang");
		//alert("daohang..."+param);
		var child=showCookie("daochild");
		//alert("daochild..."+child);
		if(param!=null){			
			
			if(param=="showitems"){					
			   $(".slideitems").show();
			   //alert(child);
			   $(document.getElementById(child)).css("font-weight","bold");
			   var simg="http://p1.maiyaole.com/images/nav_0.gif";
			   $("#showitems").parents("td").prev().children("img").attr("src",simg);
			}
			else
			if(param=="showpin"){
				   $(".slidepin").show();
				   $(document.getElementById(child)).css("font-weight","bold");
				   var simg="http://p1.maiyaole.com/images/nav_0.gif";
				   $("#showpin").parents("td").prev().children("img").attr("src",simg);
			}
			else
			if(param=="showmuban"){
					 $(".slidemuban").show();
				   $(document.getElementById(child)).css("font-weight","bold");
				   var simg="http://p1.maiyaole.com/images/nav_0.gif";
				   $("#showmuban").parents("td").prev().children("img").attr("src",simg);
			}
			else
			if(param=="showorder"){
				   $(".slideorder").show();
				   $(document.getElementById(child)).css("font-weight","bold");
				   var simg="http://p1.maiyaole.com/images/nav_0.gif";
				   $("#showorder").parents("td").prev().children("img").attr("src",simg);
			}
			else
			if(param=="showkui"){
				   $(".slidekui").show();
				   $(document.getElementById(child)).css("font-weight","bold");
				   var simg="http://p1.maiyaole.com/images/nav_0.gif";
				   $("#showkui").parents("td").prev().children("img").attr("src",simg);
			}
			   
			else
			if(param=="showquan"){
				   $(".slidequan").show();
				   $(document.getElementById(child)).css("font-weight","bold");
				   var simg="http://p1.maiyaole.com/images/nav_0.gif";
				   $("#showquan").parents("td").prev().children("img").attr("src",simg);
			}
			else
			if(param=="showyan"){
				   $(".slideyan").show();
				   $(document.getElementById(child)).css("font-weight","bold");
				   var simg="http://p1.maiyaole.com/images/nav_0.gif";
				   $("#showyan").parents("td").prev().children("img").attr("src",simg);
			}
			else
			if(param=="showgao"){
				   $(".slidegao").show();
				   $(document.getElementById(child)).css("font-weight","bold");
				   var simg="http://p1.maiyaole.com/images/nav_0.gif";
				   $("#showgao").parents("td").prev().children("img").attr("src",simg);
			}
			else
			if(param=="showuser"){
				   $(".slideuser").show();
				   $(document.getElementById(child)).css("font-weight","bold");
				   var simg="http://p1.maiyaole.com/images/nav_0.gif";
				   $("#showuser").parents("td").prev().children("img").attr("src",simg);
			}
			else
				if(param=="showapply"){
					   $(".slideapply").show();
					   $(document.getElementById(child)).css("font-weight","bold");
					   var simg="http://p1.maiyaole.com/images/nav_0.gif";
					   $("#showapply").parents("td").prev().children("img").attr("src",simg);
				}
			else
				if(param=="showcampay"){
					   $(".slidecampay").show();
					   $(document.getElementById(child)).css("font-weight","bold");
					   var simg="http://p1.maiyaole.com/images/nav_0.gif";
					   $("#showcampay").parents("td").prev().children("img").attr("src",simg);
				}
		  else
				if(param=="showping"){
					   $(".slideping").show();
					   $(document.getElementById(child)).css("font-weight","bold");
					   var simg="http://p1.maiyaole.com/images/nav_0.gif";
					   $("#showping").parents("td").prev().children("img").attr("src",simg);
				}

				else if (param=="showaudit") {
					$(".slideaudit").show();
				   	$(document.getElementById(child)).css("font-weight","bold");
				   	var simg="http://p1.maiyaole.com/images/nav_0.gif";
				   	$("#showaudit").parents("td").prev().children("img").attr("src",simg);
				}
				else if (param=="showcx") {
					$(".slidecx").show();
				   	$(document.getElementById(child)).css("font-weight","bold");
				   	var simg="http://p1.maiyaole.com/images/nav_0.gif";
				   	$("#showcx").parents("td").prev().children("img").attr("src",simg);
				}
				else if (param=="showpt") {
					$(".slidept").show();
				   	$(document.getElementById(child)).css("font-weight","bold");
				   	var simg="http://p1.maiyaole.com/images/nav_0.gif";
				   	$("#showpt").parents("td").prev().children("img").attr("src",simg);
				}
		}
	}
	</script>