package com.xznu.edu.leave.action;

import java.util.Date;
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
import com.xznu.edu.leave.model.Comment;
import com.xznu.edu.leave.model.Manage;
import com.xznu.edu.leave.model.NoteInfo;
import com.xznu.edu.leave.model.Student;
import com.xznu.edu.leave.service.NoteInfoService;
import com.xznu.edu.leave.utils.Pager;
@Controller("noteAction")
@Scope("prototype")
public class NoteInfoAction extends ActionSupport implements ModelDriven<NoteInfo>{
  private NoteInfo noteInfo;
  private int studentId;
  public int getStudentId() {
	return studentId;
}
public void setStudentId(int studentId) {
	this.studentId = studentId;
}

@Autowired
  private NoteInfoService NoteInfoService;
	@Override
	public NoteInfo getModel() {
		if(noteInfo == null) noteInfo = new NoteInfo();
		return noteInfo;
	}
/*	te Student student;
	private int type;//类型 1事假 2病假
	private String title;//请假标题
	private Date startTime;//开始时间
	private Date endTime;//结束时间
	private int status;//审批状态1待审核 2已审核
	private Date createTime;//撞见时间
*/	
	/**
	 * 列表
	 */
	public String note(){
	Pager<NoteInfo> pagers = NoteInfoService.findPager(noteInfo);
	ActionContext.getContext().put("pagers", pagers);
	ActionContext.getContext().put("noteInfo", noteInfo);
	return SUCCESS;
	}
	
	//学生查看自己的记录
	public String notes(){
		HttpSession session =	ServletActionContext.getRequest().getSession();
		int userId = Integer.parseInt( session.getAttribute("userId").toString());
		Student s = new Student();
		s.setId(userId);
		noteInfo.setStudent(s);
		Pager<NoteInfo> pagers = NoteInfoService.findPagers(noteInfo);
		ActionContext.getContext().put("pagers", pagers);
		ActionContext.getContext().put("noteInfo", noteInfo);
		return SUCCESS;
		}
	/**
	 * 跳转到添加页面
	 * @return
	 */
	public String exAdd(){
		//查询所有班级信息
		return SUCCESS;
	}
	
	/**
	 * 添加
	 * @return
	 */
	public String add(){
//		<!-- private int id;
//		private Student student;
//		private int type;//类型 1事假 2病假
//		private String title;//请假标题
//		private Date startTime;//开始时间
//		private Date endTime;//结束时间
//		private int status;//审批状态1待审核 2已审核
//		private Date createTime;//撞见时间
//		private String content; -->
		HttpSession session =	ServletActionContext.getRequest().getSession();
		if(session.getAttribute("userId") != null){
			int userId = Integer.parseInt( session.getAttribute("userId").toString());
			Student s = new Student();
			s.setId(userId);
			noteInfo.setStudent(s);
			noteInfo.setCreateTime(new Date());
			noteInfo.setStatus(1);
			noteInfo.setIsDelete(2);
			NoteInfoService.save(noteInfo);
			ActionContext.getContext().put("url", "/note_notes.do");
			return "redirect";
		}else{
			return "login";
		}
		
	}
	/**
	 * 跳转修改页面
	 * @return
	 */
	public String update(){
//		<!-- private int id;
//		private Student student;
//		private int type;//类型 1事假 2病假
//		private String title;//请假标题
//		private Date startTime;//开始时间
//		private Date endTime;//结束时间
//		private int status;//审批状态1待审核 2已审核
//		private Date createTime;//撞见时间
//		private String content; -->
		NoteInfo c = NoteInfoService.getById(noteInfo.getId());
		ActionContext.getContext().put("c", c);
		return SUCCESS;
	}
    
	public String exUpdate(){
		NoteInfo c = NoteInfoService.getById(noteInfo.getId());
		c.setTitle(noteInfo.getTitle());
c.setEndTime(noteInfo.getEndTime());
c.setStartTime(noteInfo.getStartTime());
c.setType(noteInfo.getType());
c.setContent(noteInfo.getContent());
c.setIsDelete(2);
NoteInfoService.update(c);
		ActionContext.getContext().put("url", "/note_notes.do");
		return "redirect";
	}
	/**
	 * 删除
	 * @return
	 */
	public String delete(){
		NoteInfo c = NoteInfoService.getById(noteInfo.getId());
		c.setIsDelete(1);
		NoteInfoService.update(c);
		ActionContext.getContext().put("url", "/note_notes.do");
		return "redirect";
	}
	
	public String detail(){
		NoteInfo c = NoteInfoService.getById(noteInfo.getId());
		ActionContext.getContext().put("c", c);
		return SUCCESS;
	}
	
	public String shenhe(){
		NoteInfo c = NoteInfoService.getById(noteInfo.getId());
		c.setStatus(noteInfo.getStatus());
		NoteInfoService.update(c);
		ActionContext.getContext().put("url", "/note_note.do");
		return "redirect";
	}
	
	/**
	 * 查询这个用户的所有请假记录
	 * @return
	 */
	public String studentList(){
		Pager<NoteInfo> pagers =NoteInfoService.studentList(studentId);
		ActionContext.getContext().put("pagers", pagers);
		ActionContext.getContext().put("studentId", studentId);
		return SUCCESS;
		
	}
}
