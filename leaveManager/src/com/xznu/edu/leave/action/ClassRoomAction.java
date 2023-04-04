package com.xznu.edu.leave.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.xznu.edu.leave.model.ClassRoom;
import com.xznu.edu.leave.model.Manage;
import com.xznu.edu.leave.service.ClassRoomService;
import com.xznu.edu.leave.utils.Pager;

@Controller("classRoomAction")
@Scope("prototype")
public class ClassRoomAction extends ActionSupport implements ModelDriven<ClassRoom>{
	@Autowired
    private ClassRoomService classRoomService;
	private ClassRoom classRoom;
	
	@Override
	public ClassRoom getModel() {
		if(classRoom == null) classRoom = new ClassRoom();
		return classRoom;
	}
	
	/**
	 * 查询班级列表
	 */
	public String classRoom(){
	Pager<ClassRoom> pagers = classRoomService.findPager(classRoom);
	ActionContext.getContext().put("pagers", pagers);
	ActionContext.getContext().put("classRoom", classRoom);
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
		classRoom.setIsDelete(2);
		classRoomService.save(classRoom);
		ActionContext.getContext().put("url", "/classRoom_classRoom.do");
		return "redirect";
	}
	/**
	 * 跳转修改页面
	 * @return
	 */
	public String update(){
		ClassRoom c = classRoomService.getById(classRoom.getId());
		ActionContext.getContext().put("c", c);
		return SUCCESS;
	}
    
	public String exUpdate(){
		classRoom.setIsDelete(2);
		classRoomService.update(classRoom);
		ActionContext.getContext().put("url", "/classRoom_classRoom.do");
		return "redirect";
	}
	/**
	 * 删除
	 * @return
	 */
	public String delete(){
		ClassRoom c = classRoomService.getById(classRoom.getId());
		c.setIsDelete(1);
		classRoomService.update(c);
		ActionContext.getContext().put("url", "/classRoom_classRoom.do");
		return "redirect";
	}
}
