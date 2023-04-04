package com.xznu.edu.leave.service;

import com.xznu.edu.leave.model.Manage;
import com.xznu.edu.leave.model.NoteInfo;
import com.xznu.edu.leave.utils.Pager;

public interface NoteInfoService extends BaseService<NoteInfo>{

	Pager<NoteInfo> findPager(NoteInfo noteInfo);

	Pager<NoteInfo> findPagers(NoteInfo noteInfo);

	Pager<NoteInfo> studentList(int studentId);

}
