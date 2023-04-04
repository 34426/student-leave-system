package com.xznu.edu.leave.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xznu.edu.leave.dao.NoteInfoDao;
import com.xznu.edu.leave.dao.NoticeInfoDao;
import com.xznu.edu.leave.model.Manage;
import com.xznu.edu.leave.model.NoteInfo;
import com.xznu.edu.leave.service.NoteInfoService;
import com.xznu.edu.leave.utils.Pager;
@Service
public class NoteInfoServiceImpl extends BaseServiceImpl<NoteInfo> implements NoteInfoService {
	@Autowired
     private NoteInfoDao NoteInfoDao;
	@Override
	public Pager<NoteInfo> findPager(NoteInfo noteInfo) {
		// TODO Auto-generated method stub
		return NoteInfoDao.findPager(noteInfo);
	}
	@Override
	public Pager<NoteInfo> findPagers(NoteInfo noteInfo) {
		// TODO Auto-generated method stub
		return NoteInfoDao.findPagers(noteInfo);
	}
	@Override
	public Pager<NoteInfo> studentList(int studentId) {
		// TODO Auto-generated method stub
		return NoteInfoDao.studentList(studentId);
	}

}
