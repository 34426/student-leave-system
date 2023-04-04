package com.xznu.edu.leave.action;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.xznu.edu.leave.model.ClassRoom;
import com.xznu.edu.leave.model.Manage;
import com.xznu.edu.leave.model.NoticeInfo;
import com.xznu.edu.leave.model.Student;
import com.xznu.edu.leave.service.ClassRoomService;
import com.xznu.edu.leave.service.StudentService;
import com.xznu.edu.leave.utils.Pager;
@Controller("studentAction")
@Scope("prototype")
public class StudentAction extends ActionSupport implements ModelDriven<Student>{
  private Student student;
  @Autowired
  private ClassRoomService classRoomService;
  @Autowired
  private StudentService studentService;
	@Override
	public Student getModel() {
		if(student == null) student = new Student();
		return student;
	}
	/*private int id;
	private ClassRoom classRoom;//班级
	private String xh;//学好
	private String name;
	private String passWord;
	private String realName;//真实名
	private int sex;//1男 2女
	private int isDelete;//是否删除
		学生：<input type="radio" name="role" value="3">
			教师：<input type="radio" name="role" value="2">
			管理员：<input type="radio" name="role" value="1"> 
*/	 /**
	 * 列表
	 */
	public String student(){
		HttpSession session =	ServletActionContext.getRequest().getSession();
	   int type = Integer.parseInt( session.getAttribute("type").toString());
	   int userId = Integer.parseInt( session.getAttribute("userId").toString());
	   if(type == 2){//班主任只能看本班的
		   Pager<Student> pagers = studentService.findPagerByMid(student,userId);
		   ActionContext.getContext().put("pagers", pagers);
	   }else{
		   Pager<Student> pagers = studentService.findPager(student);
		   ActionContext.getContext().put("pagers", pagers);
	   }
	
	
	ActionContext.getContext().put("student", student);
	return SUCCESS;
	}
	
	/**
	 * 跳转到添加页面
	 * @return
	 */
	public String add(){
		//查询所有班级信息
		String hql="from ClassRoom where isDelete = 2";
		List<ClassRoom> clas = classRoomService.getByHQL(hql, null);
		ActionContext.getContext().put("clas", clas);
		return SUCCESS;
	}
	
	/**
	 * 添加
	 * @return
	 */
	public String exAdd(){
		student.setIsDelete(2);
		student.setPassWord("111111");
		studentService.save(student);
		ActionContext.getContext().put("url", "/student_student.do");
		return "redirect";
	}
	/**
	 * 跳转修改页面
	 * @return
	 */
	public String update(){
		String hql="from ClassRoom where isDelete = 2";
		List<Student> clas = studentService.getByHQL(hql, null);
		ActionContext.getContext().put("clas", clas);
		Student c = studentService.getById(student.getId());
		ActionContext.getContext().put("c", c);
		return SUCCESS;
	}
    
	public String exUpdate(){
		student.setIsDelete(2);
		student.setPassWord("111111");
		studentService.update(student);
		ActionContext.getContext().put("url", "/student_student.do");
		return "redirect";
	}
	public String exUpdate2(){
		student.setIsDelete(2);
		studentService.update(student);
		return "login";
	}
	/**
	 * 删除
	 * @return
	 */
	public String delete(){
		Student c = studentService.getById(student.getId());
		c.setIsDelete(1);
		studentService.update(c);
		ActionContext.getContext().put("url", "/student_student.do");
		return "redirect";
	}
}
