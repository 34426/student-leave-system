package com.xznu.edu.leave.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.xznu.edu.leave.base.dao.impl.BaseDaoImpl;
import com.xznu.edu.leave.dao.ManageDao;
import com.xznu.edu.leave.model.ClassRoom;
import com.xznu.edu.leave.model.Manage;
import com.xznu.edu.leave.utils.Pager;



@Repository("manageDao")
@SuppressWarnings("unchecked")
public class ManageDaoImpl extends BaseDaoImpl<Manage>  implements ManageDao{

	public Manage login(Manage manage) {
		String hql = "from Manage bean where bean.name =:userName and bean.passWord= :passWord";
		Query q =this.getSession().createQuery(hql);
		q.setParameter("userName", manage.getName());
		q.setParameter("passWord", manage.getPassWord());
		return (Manage)q.uniqueResult();
	}
//======================================================================================================
	public Pager<Manage> list() {
		String hql = "from Manage where type = 2 ";
		return findByAlias(hql, null);
	}
	public Pager<Manage> listBy(String name) {
		String hql = "from Manage  where  type = 2 and realName = :name ";
		Map<String,Object> alias = new HashMap<String,Object>();
		alias.put("name", name);
		return findByAlias(hql, alias);
	}
	//================================================================================
/*	private int id;
	private String name;
	private String passWord;
	private String realName;//真实名
	private int type;//管理员类型 1 是超级管理员 2 是普通管理员也就是班主任
	private int isDelete;//1是 2否
	private ClassRoom classRoom;*/
	@Override
	public Pager<Manage> findPager(Manage manage) {
		 if(manage.getRealName() !=null && !"".equals(manage.getRealName() )){
	    	   String hql = "from Manage where isDelete = 2 and realName = :realName and type = 2";
	    	   Map<String,Object> alias = new HashMap<String,Object>();
	   		   alias.put("realName", manage.getRealName() );
	   		  return findByAlias(hql, alias);
	       }else{
	    	   String hql = "from Manage where isDelete = 2  and type = 2";
				return findByAlias(hql, null);
	       }
	}

}
