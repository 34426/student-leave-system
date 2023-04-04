package com.xznu.edu.leave.action;
/**
 * 和登陆有关的都在这里
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.xznu.edu.leave.dao.ManageDao;
import com.xznu.edu.leave.model.ClassRoom;
import com.xznu.edu.leave.model.Comment;
import com.xznu.edu.leave.model.Manage;
import com.xznu.edu.leave.model.NoticeInfo;
import com.xznu.edu.leave.model.Student;
import com.xznu.edu.leave.service.ClassRoomService;
import com.xznu.edu.leave.service.CommentService;
import com.xznu.edu.leave.service.ManageService;
import com.xznu.edu.leave.service.NoticeInfoService;
import com.xznu.edu.leave.service.StudentService;
import com.xznu.edu.leave.utils.TimeSetUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller("loginAction")
@Scope("prototype")
public class LoginAction extends ActionSupport {
	@Autowired
	private CommentService commentService;
	@Autowired
	private ManageService manageService;
	@Autowired
	private StudentService studentService;
	 @Autowired
	   private NoticeInfoService noticeInfoService;
	 @Autowired
	    private ClassRoomService classRoomService;
	
	 private int mid;
	 private int sid;
   public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}

private String name;
   private String passWord;
   private int role;
	public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getPassWord() {
	return passWord;
}
public void setPassWord(String passWord) {
	this.passWord = passWord;
}
public int getRole() {
	return role;
}
public void setRole(int role) {
	this.role = role;
}

/**
 * 用户登陆
 * @return
 */
	public String index(){
		//判断当前登陆的人事学生还是管理员或者是教师
	/*	学生：<input type="radio" name="role" value="3">
			教师：<input type="radio" name="role" value="2">
			管理员：<input type="radio" name="role" value="1"> */
		String hql="from ClassRoom where isDelete = 2";
		List<ClassRoom> clas = classRoomService.getByHQL(hql, null);
		ActionContext.getContext().put("clas", clas);
		NoticeInfo notice = noticeInfoService.loadNew();
		ActionContext.getContext().put("notice", notice);
		HttpSession session =	ServletActionContext.getRequest().getSession();
		String s =TimeSetUtil.startTime;
	    String e =	TimeSetUtil.endtTime;
	    session.setAttribute("s", s);
	    session.setAttribute("e", e);
		Map<String, Object> alias  = new HashMap<String, Object>();
		String hqls = "from Student where name = :name and passWord = :passWord";
		if(role == 3){
			alias.put("name", name);
			alias.put("passWord", passWord);
		Student stu =studentService.getOneByHql(hqls, alias);
		if(stu != null){
			session.setAttribute("userName", stu.getName());
			session.setAttribute("passWord", stu.getPassWord());
			session.setAttribute("userId", stu.getId());
			session.setAttribute("type", 3);
			session.setAttribute("roleType", 3);
			Student c = studentService.getById(stu.getId());
			ActionContext.getContext().put("c", c);
			
			
			return SUCCESS;
		 }else{
			return "login"; 
		 }
		}else{
			String hqlss = "from Manage where name = :name and passWord = :passWord and type = :type";
			alias.put("name", name);
			alias.put("passWord", passWord);
			alias.put("type", role);
		    Manage ma =	manageService.getOneByHql(hqlss, alias);
		    if(ma != null){
		    	session.setAttribute("userName", ma.getName());
				session.setAttribute("userId", ma.getId());
				session.setAttribute("type", role);
				session.setAttribute("roleType", role);
				Manage m= manageService.getById(ma.getId());
				ActionContext.getContext().put("m", m);
				return SUCCESS;
			 }else{
				return "login"; 
			 }
		}
		
		
		
	}
	//登陆页面
	public String login() {
		return "success";
	}
   //退出
	public String tuichu() {
		ActionContext ac = ActionContext.getContext();
		Map session = ac.getSession();
		session.remove("userName");
		session.remove("userId");
		return "login";
	}
	
}
