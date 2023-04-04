package com.xznu.edu.leave.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xznu.edu.leave.dao.ClassRoomDao;
import com.xznu.edu.leave.dao.ManageDao;
import com.xznu.edu.leave.dao.StudentDao;
import com.xznu.edu.leave.model.ClassRoom;
import com.xznu.edu.leave.model.NoteInfo;
import com.xznu.edu.leave.model.Student;
import com.xznu.edu.leave.service.StudentService;
import com.xznu.edu.leave.utils.Pager;
@Service
public class StudentServiceImpl extends BaseServiceImpl<Student> implements StudentService {
	@Autowired
     private StudentDao studentDao;
	@Autowired
	 private ClassRoomDao classRoomDao;
	@Autowired
	 private ManageDao manageDao;
	@Override
	public Pager<Student> findPager(Student student) {
		return studentDao.findPager(student);
	}
	@Override
	public Pager<Student> findPagerByMid(Student student, int userId) {
		//查询班主任所在班级
		return studentDao.findPagerByMid(student,manageDao.load(userId).getClassRoom().getId());
	}


}
