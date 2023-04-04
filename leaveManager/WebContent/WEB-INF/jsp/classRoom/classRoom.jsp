<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
   <link rel="stylesheet" type="text/css" href="${ctx}/resource/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/resource/css/main.css"/>
    <script type="text/javascript" src="${ctx}/resource/js/libs/modernizr.min.js"></script>
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
<div class="topbar-wrap white">
    <div class="topbar-inner clearfix">
        <div class="topbar-logo-wrap clearfix">
            <h1 class="topbar-logo none"><a href="index.html" class="navbar-brand">后台管理</a></h1>
            <ul class="navbar-list clearfix">
                <li><a class="on" href="index.html">首页</a></li>
                <li><a href="http://www.mycodes.net/" target="_blank">网站首页</a></li>
            </ul>
        </div>
        <div class="top-info-wrap">
            <ul class="top-info-list clearfix">
                <li><a href="login_index.do?role=${roleType}&name=${userName}&passWord=${passWord}">修改密码</a></li>
                <li><a href="login_tuichu.do">退出</a></li>
            </ul>
        </div>
    </div>
</div>
<div class="container clearfix">
    <div class="sidebar-wrap">
        <div class="sidebar-title">
            <h1>菜单</h1>
        </div>
        <div class="sidebar-content">
            <ul class="sidebar-list">
                <li>
                    <a href="#"><i class="icon-font">&#xe003;</i>菜单管理</a>
                    <ul class="sub-menu">
                     <c:if test="${roleType == 1}">
		                       <li><a href="manage_setTime.do"><i class="icon-font">&#xe008;</i>学期时间设置</a></li>
		                         <li><a href="classRoom_classRoom.do"><i class="icon-font">&#xe012;</i>班级管理</a></li>
		                        <li><a href="manage_manage.do"><i class="icon-font">&#xe005;</i>班主任管理</a></li>
		                        <li><a href="notice_notice.do"><i class="icon-font">&#xe006;</i>公告管理</a></li>
		                         <li><a href="comment_comment.do"><i class="icon-font">&#xe004;</i>留言板</a></li>
		                        <li><a href="student_student.do"><i class="icon-font">&#xe012;</i>学生管理</a></li>
                         </c:if>
                         <c:if test="${roleType == 2}">
                          <li><a href="note_note.do"><i class="icon-font">&#xe052;</i>请假管理</a></li>
                            <li><a href="student_student.do"><i class="icon-font">&#xe012;</i>学生管理</a></li>
                            <li><a href="comment_comment.do"><i class="icon-font">&#xe004;</i>留言板</a></li>
                         </c:if>
                        <c:if test="${roleType == 3}">
                          <li><a href="comment_comment.do"><i class="icon-font">&#xe004;</i>留言板</a></li>
                         <li><a href="note_exAdd.do"><i class="icon-font">&#xe052;</i>请假申请</a></li>
                        <li><a href="note_notes.do"><i class="icon-font">&#xe033;</i>我的请假</a></li>
                        </c:if>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
    <div class="main-wrap">
  <div class="search-wrap">
            <div class="search-content">
                <form action="classRoom_classRoom.do" method="post">
                    <table class="search-tab">
                        <tr>
                            <th width="70">专业:</th>
                            <td><input class="common-text" placeholder="名称" name="name" value="${classRoom.name}" id="" type="text"></td>
                            <td><input class="btn btn-primary btn2" name="sub" value="查询" type="submit"></td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
     <!--    private int id;
	private String college;//学院
	private String major;//专业
	private int isDelete;//是否删除 1是 2否 -->
        <div class="result-wrap">
                <div class="result-title">
                    <div class="result-list">
                        <a href="${ctx}/classRoom_add.do"><i class="icon-font"></i>新增</a>
                    </div>
                </div>
                <div class="result-content">
                    <table class="result-tab" width="100%">
                        <tr>
                            <th>学院</th>
                            <th>专业</th>
                            <th>班级名称</th>
                            <th>操作</th>
                        </tr>
                         <c:forEach items="${pagers.datas}" var="spe" varStatus="l">
                         <tr>
                            <td>${spe.college}</td>
                             <td>${spe.major}</td>
                               <td>${spe.name}</td>
                            <td>
                                <a class="link-update" href="${ctx}/classRoom_update.do?id=${spe.id}">修改</a>
                                <a class="link-del" href="${ctx}/classRoom_delete.do?id=${spe.id}">删除</a>
                            </td>
                        </tr>
                         </c:forEach>
                    </table>
                    <!-- 分页开始 -->
                    <div class="list-page"> 
			       <div class="panel-foot text-center">
			      <pg:pager  url="${ctx}/classRoom_classRoom.do" maxIndexPages="5" items="${pagers.total}"  maxPageItems="15" export="curPage=pageNumber" >
					<pg:param name="major" value="${classRoom.major}"/>
					<pg:last>  
						共${pagers.total}记录,共${pageNumber}页,  
					</pg:last>  
						当前第${curPage}页 
			  
			        <pg:first>  
			    		<a href="${pageUrl}">首页</a>  
					</pg:first>  
					<pg:prev>  
			    		<a href="${pageUrl}">上一页</a>  
					</pg:prev>  
			       	<pg:pages>  
			        	<c:choose>  
			            	<c:when test="${curPage eq pageNumber}">  
			                	<font color="red">[${pageNumber }]</font>  
			            	</c:when>  
			            	<c:otherwise>  
			               		<a href="${pageUrl}">${pageNumber}</a>  
			            	</c:otherwise>  
			        	</c:choose>  
			    	</pg:pages>
			             
			        <pg:next>  
			    		<a href="${pageUrl}">下一页</a>  
					</pg:next>  
					<pg:last>  
						<c:choose>  
			            	<c:when test="${curPage eq pageNumber}">  
			                	<font color="red">尾页</font>  
			            	</c:when>  
			            	<c:otherwise>  
			               		<a href="${pageUrl}">尾页</a>  
			            	</c:otherwise>  
			        	</c:choose> 
			    		  
					</pg:last>
				</pg:pager>
    </div>
	        
                    
                    
                    
                    </div>
                    <!-- 分页结束 -->
                </div>
        </div>
    </div>
    <!--/main-->
</div>
</body>
</html>
