package com.xznu.edu.leave.service;

import java.util.List;

import com.xznu.edu.leave.model.Comment;
import com.xznu.edu.leave.utils.Pager;



public interface CommentService extends BaseService<Comment>{

	Pager<Comment> findBySayId(int id);

	List<Comment> listNextComByFatherId(int id);

	void comment(Comment comment, int userId, int sayMoodId);

	void save(Comment comment, int userId, int sayMoodId);

	void savehuifu(Comment comment, int userId);

	List<Comment> findBySayIdList(int id);

	Pager<Comment> findComment(int otherId);

	void deleteInfo(int id);

	Pager<Comment> findPager();

	Comment loadNew();

}
