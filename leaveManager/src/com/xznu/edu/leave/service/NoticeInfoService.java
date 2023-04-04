package com.xznu.edu.leave.service;

import com.xznu.edu.leave.model.NoticeInfo;
import com.xznu.edu.leave.utils.Pager;

public interface NoticeInfoService extends BaseService<NoticeInfo> {

	Pager<NoticeInfo> findPager(NoticeInfo noticeInfo);

	NoticeInfo loadNew();

}
