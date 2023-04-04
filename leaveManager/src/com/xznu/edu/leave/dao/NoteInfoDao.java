package com.xznu.edu.leave.dao;

import com.xznu.edu.leave.base.dao.BaseDao;
import com.xznu.edu.leave.model.Comment;
import com.xznu.edu.leave.model.NoteInfo;
import com.xznu.edu.leave.utils.Pager;

public interface NoteInfoDao extends BaseDao<NoteInfo>{

	Pager<NoteInfo> findPager(NoteInfo noteInfo);

	Pager<NoteInfo> findPagers(NoteInfo noteInfo);

	Pager<NoteInfo> studentList(int studentId);

}
