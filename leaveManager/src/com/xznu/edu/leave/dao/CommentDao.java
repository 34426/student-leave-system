package com.xznu.edu.leave.dao;

import java.util.List;

import com.xznu.edu.leave.base.dao.BaseDao;
import com.xznu.edu.leave.model.Comment;
import com.xznu.edu.leave.utils.Pager;


public interface CommentDao extends BaseDao<Comment>{

	Pager<Comment> commentDao(int id);

	List<Comment> listNextComByFatherId(int id);

	List<Comment> findBySayIdList(int id);

	Pager<Comment> findComment(int otherId);

	Pager<Comment> findPager();

	Comment loadNew();

}
