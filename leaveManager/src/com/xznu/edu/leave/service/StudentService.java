package com.xznu.edu.leave.service;

import com.xznu.edu.leave.model.Student;
import com.xznu.edu.leave.utils.Pager;

public interface StudentService extends BaseService<Student> {

	Pager<Student> findPager(Student student);

	Pager<Student> findPagerByMid(Student student, int userId);

}
