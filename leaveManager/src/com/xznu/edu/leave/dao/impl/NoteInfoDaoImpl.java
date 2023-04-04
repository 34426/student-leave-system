package com.xznu.edu.leave.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xznu.edu.leave.base.dao.impl.BaseDaoImpl;
import com.xznu.edu.leave.dao.ManageDao;
import com.xznu.edu.leave.dao.NoteInfoDao;
import com.xznu.edu.leave.model.Manage;
import com.xznu.edu.leave.model.NoteInfo;
import com.xznu.edu.leave.utils.Pager;
import com.xznu.edu.leave.utils.TimeSetUtil;
@Repository
public class NoteInfoDaoImpl extends BaseDaoImpl<NoteInfo>  implements NoteInfoDao{

	/*te Student student;
	private int type;//类型 1事假 2病假
	private String title;//请假标题
	private Date startTime;//开始时间
	private Date endTime;//结束时间
	private int status;//审批状态1待审核 2已审核
	private Date createTime;//撞见时间
*/	@Override
	public Pager<NoteInfo> findPager(NoteInfo noteInfo) {
		// TODO Auto-generated method stub
	
	if(noteInfo.getStudent()!=null){
		if(noteInfo.getStudent().getRealName()!=null && !"".equals(noteInfo.getStudent().getRealName())){
			//学生名称
			String hql = "from NoteInfo where isDelete = 2 and student.realName = :realName order by createTime desc ";
			 Map<String,Object> alias = new HashMap<String,Object>();
	   		   alias.put("realName", noteInfo.getStudent().getRealName());
	   		  return findByAlias(hql, alias);
		}
	
	}else{
		//查询所有
		String hql = "from NoteInfo where isDelete = 2 order by createTime desc ";
		return findByAlias(hql, null);
	 }
	return null;
	}

@Override
public Pager<NoteInfo> findPagers(NoteInfo noteInfo) {
	if(noteInfo.getStudent()!=null){
		if(noteInfo.getStudent().getRealName()!=null && !"".equals(noteInfo.getStudent().getRealName())){
			//学生名称
			String hql = "from NoteInfo where isDelete = 2 and student.realName = :realName and student.id = :id order by createTime desc ";
			 Map<String,Object> alias = new HashMap<String,Object>();
	   		   alias.put("realName", noteInfo.getStudent().getRealName());
	   		 alias.put("id", noteInfo.getStudent().getId());
	   		  return findByAlias(hql, alias);
		
	}else{
		//查询所有
		String hql = "from NoteInfo where isDelete = 2 and student.id = :id order by createTime desc ";
		 Map<String,Object> alias = new HashMap<String,Object>();
 		 alias.put("id", noteInfo.getStudent().getId());
 		  return findByAlias(hql, alias);
	 }}
	return null;
}

@Override
public Pager<NoteInfo> studentList(int studentId) {
	String e = TimeSetUtil.endtTime;
	String s = TimeSetUtil.startTime;
	        //查询所有
			String hql = "from NoteInfo where isDelete = 2 and student.id = :id  and status = 2 and createTime between :s and :e  order by createTime desc ";
			 Map<String,Object> alias = new HashMap<String,Object>();
	 		 alias.put("id", studentId);
	 		 SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
	 		 try {
				alias.put("s", sd.parse(s));
				alias.put("e", sd.parse(e));
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	 		 
	 		  return findByAlias(hql, alias);
}

//=======================
/*if(noteInfo.getStudent().getId()!=0 && !"".equals(noteInfo.getStudent().getId())){
	//学生名id
	String hql = "from NoteInfo where isDelete = 2 and student.id = :id order by createTime desc ";
	 Map<String,Object> alias = new HashMap<String,Object>();
		   alias.put("id", noteInfo.getStudent().getId());
		  return findByAlias(hql, alias);
	
}
if(noteInfo.getStudent().getClassRoom()!=null){
	  if(noteInfo.getStudent().getClassRoom().getName()!=null && !"".equals(noteInfo.getStudent().getClassRoom().getName())){
			//班级名称
		  String hql = "from NoteInfo where isDelete = 2 and student.classRoom.name = :name order by createTime desc ";
			 Map<String,Object> alias = new HashMap<String,Object>();
	   		   alias.put("name", noteInfo.getStudent().getClassRoom().getName());
	   		  return findByAlias(hql, alias);
		}
}*/

}
