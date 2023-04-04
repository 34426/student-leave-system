<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <link href="${ctx}/resource/css/login.css" rel="stylesheet" type="text/css" media="all"/>
<head>
<style>
#doc-topbar-collapse-3 a{
font-weight: bold;
font-size: 14px;
}
.headers{
height: 30px;width: 200px;
z-index:999;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台登陆</title>
</head>
<body>
<%-- <div class="login">
		 <div class="login-main">
		     <form action="login_index.do" method="post">
		 		<div class="login-top">
		 			<img src="${ctx}/resource/images/head-img.png" alt=""/>
		 			<h1>用户登录</h1>
		 			<input type="text" placeholder="用户名" required="true" name="name">
		 			<input type="password" placeholder="密码" required="true" name="passWord">
		 			<div class="login-check">
		 			  <span>选择登陆角色&nbsp;&nbsp;&nbsp;&nbsp;</span>
			 			学生：<input type="radio" name="role" value="3">
			 			教师：<input type="radio" name="role" value="2">
			 			管理员：<input type="radio" name="role" value="1"> 
			 		  </div>
			 			<div class="clear"> </div>
		 			<div class="login-bottom">
		 			  
		 			</div>
		 			<input type="submit" value="登录" />
		 		</div>
		 		</form>
		 	</div>
  </div> --%>
  <div class="login">
    <div class="message">品牌名称-管理登录</div>
    <div id="darkbannerwrap"></div>
    <form method="post" action="login_index.do">
		<input name="name" placeholder="用户名" required="" type="text">
		<hr class="hr15">
		<input name="passWord" placeholder="密码" required="" type="password">
		<hr class="hr15">
		<span>选择登陆角色&nbsp;&nbsp;&nbsp;&nbsp;</span>
			 			学生：<input type="radio" name="role" value="3">
			 			教师：<input type="radio" name="role" value="2">
			 			管理员：<input type="radio" name="role" value="1"> 
		<input value="登录" style="width:100%;" type="submit">
		<hr class="hr20">
		<!-- 帮助 <a onClick="alert('请联系管理员')">忘记密码</a> -->
	</form>
</div>
<!--header end here-->
<div style="text-align:center;">
</body>
</html>
