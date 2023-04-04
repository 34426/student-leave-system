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
import com.xznu.edu.leave.model.Student;
import com.xznu.edu.leave.service.CommentService;
import com.xznu.edu.leave.utils.Pager;

@Controller("commentAction")
@Scope("prototype")
public class CommentAction extends ActionSupport implements ModelDriven<Comment>{
private Comment comment;
@Autowired
private CommentService commentService;


	public Comment getModel() {
		if(comment==null) comment = new Comment();
		return comment;
	}
	/**
	 * 列表
	 */
	public String comment(){
	Pager<Comment> pagers = commentService.findPager();
	ActionContext.getContext().put("pagers", pagers);
	ActionContext.getContext().put("comment", comment);
	return SUCCESS;
	}
	
	/**
	 * 跳转到添加页面
	 * @return
	 */
	public String add(){
		return SUCCESS;
	}
	
	/**
	 * 添加
	 * @return
	 */
	public String exAdd(){
		//当前是否有人
		HttpSession session =	ServletActionContext.getRequest().getSession();
		if(session.getAttribute("userId") != null){
			int userId = Integer.parseInt( session.getAttribute("userId").toString());
			comment.setCreateTime(new Date());
			comment.setIsDelete(2);
			Student s = new  Student();
			s.setId(userId);
			comment.setStudent(s);
			commentService.save(comment);
			
			ActionContext.getContext().put("url", "/comment_comment.do");
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
		Comment c = commentService.getById(comment.getId());
		ActionContext.getContext().put("c", c);
		return SUCCESS;
	}
    
	public String exUpdate(){
		commentService.update(comment);
		ActionContext.getContext().put("url", "/manage_manage.do");
		return "redirect";
	}
	/**
	 * 删除
	 * @return
	 */
	public String delete(){
		Comment c = commentService.getById(comment.getId());
		c.setIsDelete(1);
		commentService.update(c);
		ActionContext.getContext().put("url", "/comment_comment.do");
		return "redirect";
	}

	/**
	 * 评论
	 * @return
	 */
	/*public String comment() {
		HttpSession session =	ServletActionContext.getRequest().getSession();
		if(session.getAttribute("userId") != null){
			int userId = Integer.parseInt( session.getAttribute("userId").toString());
			CommentService.save(comment,userId,sayMoodId);
		}else{
			return "login";
		}
		ActionContext.getContext().put("url", "/sayMood_photoDetail.do?id="+sayMoodId+"&otherId="+otherId);
		return "redirect";
	}*/
	
	//评论说说 文字
	public String huifutext() {
		HttpSession session =	ServletActionContext.getRequest().getSession();
		if(session.getAttribute("userId") != null){
			int userId = Integer.parseInt( session.getAttribute("userId").toString());
		//	commentService.save(comment,userId,sayMoodId);
		}else{
			return "login";
		}
		ActionContext.getContext().put("url", "/sayMood_shuoshuoList.do");
		return "redirect";
	}
	/**
	 * 回复
	 * @return
	 */
	public String huifu() {
		HttpSession session =	ServletActionContext.getRequest().getSession();
		if(session.getAttribute("userId") != null){
			int userId = Integer.parseInt( session.getAttribute("userId").toString());
			commentService.savehuifu(comment,userId);
		}else{
			return "login";
		}
	//	ActionContext.getContext().put("url", "/sayMood_photoDetail.do?id="+sayMoodId+"&otherId="+otherId);
		return "redirect";
	}

	//说说文字回复
	public String huifuhft() {
		HttpSession session =	ServletActionContext.getRequest().getSession();
		if(session.getAttribute("userId") != null){
			int userId = Integer.parseInt( session.getAttribute("userId").toString());
			commentService.savehuifu(comment,userId);
		}else{
			return "login";
		}
		ActionContext.getContext().put("url", "/sayMood_shuoshuoList.do");
		return "redirect";
	}
	
	public String delComment(){
		//Pager<Comment> pager = CommentService.findComment(otherId);
		Comment c =	commentService.getById(comment.getId());
		commentService.deleteInfo(comment.getId());
		//ActionContext.getContext().put("url", "/comment_comment.do?otherId="+otherId);
		return "redirect";
	}
}
