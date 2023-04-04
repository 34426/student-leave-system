package com.xznu.edu.leave.dao;

import com.xznu.edu.leave.base.dao.BaseDao;
import com.xznu.edu.leave.model.NoticeInfo;
import com.xznu.edu.leave.model.Student;
import com.xznu.edu.leave.utils.Pager;

public interface StudentDao  extends BaseDao<Student> {

	Pager<Student> findPager(Student student);

	Pager<Student> findPagerByMid(Student student, int userId);

}
