package com.xznu.edu.leave.dao;

import com.xznu.edu.leave.base.dao.BaseDao;
import com.xznu.edu.leave.model.Manage;
import com.xznu.edu.leave.utils.Pager;

public interface ManageDao extends BaseDao<Manage>{

	Manage login(Manage manage);

	Pager<Manage> list();

	Pager<Manage> listBy(String name);

	Pager<Manage> findPager(Manage manage);

}
