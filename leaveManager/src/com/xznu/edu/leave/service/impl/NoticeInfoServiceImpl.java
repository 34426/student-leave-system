package com.xznu.edu.leave.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xznu.edu.leave.dao.NoticeInfoDao;
import com.xznu.edu.leave.model.NoteInfo;
import com.xznu.edu.leave.model.NoticeInfo;
import com.xznu.edu.leave.service.NoticeInfoService;
import com.xznu.edu.leave.utils.Pager;
@Service
public class NoticeInfoServiceImpl extends BaseServiceImpl<NoticeInfo> implements NoticeInfoService {
	@Autowired
    private NoticeInfoDao noticeInfoDao;
	@Override
	public Pager<NoticeInfo> findPager(NoticeInfo noticeInfo) {
		// TODO Auto-generated method stub
		return noticeInfoDao.findPager(noticeInfo);
	}
	@Override
	public NoticeInfo loadNew() {
		// TODO Auto-generated method stub
		return  noticeInfoDao.loadNew();
	}

}
