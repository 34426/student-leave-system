package com.xznu.edu.leave.dao;

import com.xznu.edu.leave.base.dao.BaseDao;
import com.xznu.edu.leave.model.ClassRoom;
import com.xznu.edu.leave.model.Manage;
import com.xznu.edu.leave.utils.Pager;

public interface ClassRoomDao extends BaseDao<ClassRoom>{

	Pager<ClassRoom> findPager(ClassRoom classRoom);

}
