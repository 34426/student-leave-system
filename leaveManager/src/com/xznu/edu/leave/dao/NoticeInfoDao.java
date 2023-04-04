package com.xznu.edu.leave.dao;

import com.xznu.edu.leave.base.dao.BaseDao;
import com.xznu.edu.leave.model.NoticeInfo;
import com.xznu.edu.leave.utils.Pager;

public interface NoticeInfoDao extends BaseDao<NoticeInfo> {

	Pager<NoticeInfo> findPager(NoticeInfo noticeInfo);

	NoticeInfo loadNew();

}
