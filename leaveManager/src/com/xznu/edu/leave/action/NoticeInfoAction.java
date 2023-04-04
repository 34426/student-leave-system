package com.xznu.edu.leave.action;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.xznu.edu.leave.model.ClassRoom;
import com.xznu.edu.leave.model.Manage;
import com.xznu.edu.leave.model.NoteInfo;
import com.xznu.edu.leave.model.NoticeInfo;
import com.xznu.edu.leave.service.NoticeInfoService;
import com.xznu.edu.leave.utils.Pager;

@Controller("noticeAction")
@Scope("prototype")
public class NoticeInfoAction extends ActionSupport implements ModelDriven<NoticeInfo>{
   private NoticeInfo noticeInfo;
   @Autowired
   private NoticeInfoService noticeInfoService;
	@Override
	public NoticeInfo getModel() {
		if(noticeInfo == null) noticeInfo = new NoticeInfo();
		return noticeInfo;
	}
	/*private int id;
	private String content;
	private Date createTime;*/
	/**
	 * 列表
	 */
	public String notice(){
	Pager<NoticeInfo> pagers = noticeInfoService.findPager(noticeInfo);
	ActionContext.getContext().put("pagers", pagers);
	ActionContext.getContext().put("noticeInfo", noticeInfo);
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
		noticeInfo.setCreateTime(new Date());
		noticeInfoService.save(noticeInfo);
		ActionContext.getContext().put("url", "/notice_notice.do");
		return "redirect";
	}
	/**
	 * 跳转修改页面
	 * @return
	 */
	public String update(){
		NoticeInfo c = noticeInfoService.getById(noticeInfo.getId());
		ActionContext.getContext().put("c", c);
		return SUCCESS;
	}
    
	public String exUpdate(){
		NoticeInfo c = noticeInfoService.getById(noticeInfo.getId());
		c.setContent(noticeInfo.getContent());
		noticeInfoService.update(c);
		ActionContext.getContext().put("url", "/notice_notice.do");
		return "redirect";
	}
	/**
	 * 删除
	 * @return
	 */
	public String delete(){
		noticeInfoService.delete(noticeInfo.getId());
		ActionContext.getContext().put("url", "/notice_notice.do");
		return "redirect";
	}

}
