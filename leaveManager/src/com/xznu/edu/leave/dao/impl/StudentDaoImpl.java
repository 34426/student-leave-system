package com.xznu.edu.leave.dao.impl;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Id;

import org.springframework.stereotype.Repository;

import com.xznu.edu.leave.base.dao.impl.BaseDaoImpl;
import com.xznu.edu.leave.dao.StudentDao;
import com.xznu.edu.leave.model.ClassRoom;
import com.xznu.edu.leave.model.Student;
import com.xznu.edu.leave.utils.Pager;
@Repository
public class StudentDaoImpl extends BaseDaoImpl<Student>  implements StudentDao {

	@Override
	public Pager<Student> findPager(Student student) {
		/*//年级、专业、班级、学生学号、姓名、性别等
		private int id;
		private ClassRoom classRoom;//班级
		private String xh;//学好
		private String name;
		private String passWord;
		private String realName;//真实名
		private int sex;//1男 2女
		private int isDelete;//是否删除
		@Id*/
		 if(student.getRealName() !=null && !"".equals(student.getRealName())){
	    	   String hql = "from Student where isDelete = 2 and realName = :realName";
	    	   Map<String,Object> alias = new HashMap<String,Object>();
	   		   alias.put("realName", student.getRealName());
	   		  return findByAlias(hql, alias);
	       }else{
	    	   String hql = "from Student where isDelete = 2 ";
				return findByAlias(hql, null);
	       }
	}

	@Override
	public Pager<Student> findPagerByMid(Student student, int userId) {
		 if(student.getRealName() !=null && !"".equals(student.getRealName())){
		  String hql = "from Student where isDelete = 2 and realName = :realName and classRoom.id = :userId";
   	         Map<String,Object> alias = new HashMap<String,Object>();
  		      alias.put("realName", student.getRealName());
  		       alias.put("id",  userId);
  		        return findByAlias(hql, alias);
          }else{
        	  String hql = "from Student where isDelete = 2 and classRoom.id = :userId";
        	  Map<String,Object> alias = new HashMap<String,Object>();
        	     alias.put("userId",  userId);;
			return findByAlias(hql, alias);
      }
	}

}
