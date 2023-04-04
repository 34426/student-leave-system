package com.xznu.edu.leave.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xznu.edu.leave.base.dao.impl.BaseDaoImpl;
import com.xznu.edu.leave.dao.ClassRoomDao;
import com.xznu.edu.leave.dao.ManageDao;
import com.xznu.edu.leave.model.ClassRoom;
import com.xznu.edu.leave.model.Manage;
import com.xznu.edu.leave.utils.Pager;
@Repository
public class ClassRoomDaoImpl extends BaseDaoImpl<ClassRoom>  implements ClassRoomDao{
	/*private int id;
	private String college;//学院
	private String major;//专业
	private int isDelete;//是否删除 1是 2否
	private Manage manage;//班级的班主任
*/	@Override
	public Pager<ClassRoom> findPager(ClassRoom classRoom) {
	       if(classRoom.getName() !=null && !"".equals(classRoom.getName())){
	    	   String hql = "from ClassRoom where isDelete = 2 and name = :name";
	    	   Map<String,Object> alias = new HashMap<String,Object>();
	   		   alias.put("name", classRoom.getName());
	   		  return findByAlias(hql, alias);
	       }else{
	    	   String hql = "from ClassRoom where isDelete = 2 ";
				return findByAlias(hql, null);
	       }
			
	}

}
