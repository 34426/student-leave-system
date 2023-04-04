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
                <form action="note_notes.do" method="post">
                    <table class="search-tab">
                    </table>
                </form>
            </div>
        </div>
 <!--  private int id;
		private int id;
	private Student student;
	private int type;//类型 1事假 2病假
	private String title;//请假标题
	private Date startTime;//开始时间
	private Date endTime;//结束时间
	private int status;//审批状态1待审核 2已审核
	private Date createTime;//撞见时间
	private String content;
	<!--  class ClassRoom {
	private int id;
	private String name;//String班级名称
	private String college;//学院
	private String major;//专业
	private int isDelete;//是否删除 1是 2否 -->
        <div class="result-wrap">
                <div class="result-content">
                    <table class="result-tab" width="100%">
                        <tr>
                            <th>学生姓名</th>
                            <th>学院</th>
                            <th>专业</th>
                            <th>班级名称</th>
                            <th>请假类别</th>
                            <th>开始时间</th>
                            <th>结束时间</th>
                            <th>审批状态</th>
                            <th>操作</th>
                        </tr>
                         <c:forEach items="${pagers.datas}" var="spe" varStatus="l">
                         <tr>
                             <td>${spe.student.realName}</td>
                             <td>${spe.student.classRoom.college}</td>
                             <td>${spe.student.classRoom.major}</td>
                              <td>${spe.student.classRoom.name}</td>
                             <td> 
                              <c:if test="${spe.type == 1 }">
                                                                                               事假
                              </c:if>
                             <c:if test="${spe.type == 2 }">
                                                                                                  病假
                              </c:if>
                             </td>
                               <td>${spe.startTime}</td>
                               <td>${spe.endTime}</td>
                               <td>
                                   <c:if test="${spe.status == 1}">
                                     待审
                                   </c:if>
                                   <c:if test="${spe.status == 2}">
                                   已审
                                   </c:if>
                                   <c:if test="${spe.status == 3}">
                                   驳回
                                   </c:if>
                               </td>
                            <td>
                                <a class="link-update" href="${ctx}/note_update.do?id=${spe.id}">修改</a>
                                <a class="link-del" href="${ctx}/note_delete.do?id=${spe.id}">删除</a>
                            </td>
                        </tr>
                         </c:forEach>
                    </table>
                    <!-- 分页开始 -->
                    <div class="list-page"> 
			       <div class="panel-foot text-center">
			      <pg:pager  url="${ctx}/note_notes.do" maxIndexPages="5" items="${pagers.total}"  maxPageItems="15" export="curPage=pageNumber" >
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
