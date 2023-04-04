package com.xznu.edu.leave.service;

import com.xznu.edu.leave.model.Manage;
import com.xznu.edu.leave.utils.Pager;

public interface ManageService  extends BaseService<Manage>{

	Manage login(Manage manage);

	Pager<Manage> list(Manage manage);

	void updateInfo(Manage manage);

	void deleteInfo(int id);

	Pager<Manage> findPager(Manage manage);

}
