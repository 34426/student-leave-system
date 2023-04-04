package com.xznu.edu.leave.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xznu.edu.leave.service.BaseService;
import com.xznu.edu.leave.dao.ManageDao;
import com.xznu.edu.leave.model.Manage;
import com.xznu.edu.leave.service.ManageService;
import com.xznu.edu.leave.utils.Pager;


@Service("manageService")
public class ManageServiceImpl extends BaseServiceImpl<Manage> implements ManageService {
     @Autowired
	 private ManageDao manageDao;
   	public Manage login(Manage manage) {
		 Manage m = new Manage();
		 m.setName(manage.getName());
		 m.setPassWord(manage.getPassWord());
		return manageDao.login(m);
	}
	public Pager<Manage> list(Manage manage) {
		if(manage.getName()!=null && !"".equals(manage.getName())){
			return manageDao.listBy(manage.getName());
		}else{
			return manageDao.list();
		}
		 
	}
	public void updateInfo(Manage manage) {
		manageDao.update(manage);
	}
	public void deleteInfo(int id) {
		manageDao.delete(id);
	}
	@Override
	public Pager<Manage> findPager(Manage manage) {
		return manageDao.findPager(manage);
	}
	

}
