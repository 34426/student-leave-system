package com.xznu.edu.leave.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xznu.edu.leave.dao.ClassRoomDao;
import com.xznu.edu.leave.model.ClassRoom;
import com.xznu.edu.leave.service.ClassRoomService;
import com.xznu.edu.leave.utils.Pager;
@Service
public class ClassRoomServiceImpl extends BaseServiceImpl<ClassRoom> implements ClassRoomService{
	@Autowired
   private ClassRoomDao classRoomDao;
	@Override
	public Pager<ClassRoom> findPager(ClassRoom classRoom) {
		// TODO Auto-generated method stub
		return classRoomDao.findPager(classRoom);
	}

}
