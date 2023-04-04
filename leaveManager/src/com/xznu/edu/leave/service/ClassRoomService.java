package com.xznu.edu.leave.service;

import com.xznu.edu.leave.model.ClassRoom;
import com.xznu.edu.leave.model.Manage;
import com.xznu.edu.leave.utils.Pager;

public interface ClassRoomService extends BaseService<ClassRoom> {

	Pager<ClassRoom> findPager(ClassRoom classRoom);

}
