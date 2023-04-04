package com.xznu.edu.leave.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xznu.edu.leave.service.BaseService;
import com.xznu.edu.leave.dao.CommentDao;
import com.xznu.edu.leave.model.Comment;
import com.xznu.edu.leave.service.CommentService;
import com.xznu.edu.leave.utils.Pager;

@Service("commentService")
public class CommentServiceImpl extends BaseServiceImpl<Comment> implements CommentService{
	@Autowired
	private CommentDao commentDao;

	public Pager<Comment> findBySayId(int id) {
		// TODO Auto-generated method stub
		return commentDao.commentDao(id);
	}

	public List<Comment> listNextComByFatherId(int id) {
		// TODO Auto-generated method stub
		return commentDao.listNextComByFatherId(id);
	}

	public void comment(Comment comment, int userId, int sayMoodId) {
		
	}

/*	@Override
	public void save(Comment comment, int userId, int sayMoodId) {
		//当前登陆 也就是评论人
				private User sayUser;//发表人
				private User comUser;//评论人
				User u =userDao.load(userId);
				SayMood say = sayMoodDao.load(sayMoodId);
				Comment co = new Comment();
				co.setComUser(u);
				co.setCreateTime(new Date());
				co.setFatherComment(null);
				co.setSayMood(say);
				co.setSayUser(say.getSayUser());
				co.setContent(comment.getContent());
				commentDao.add(co);
		
	}
*/

	public List<Comment> findBySayIdList(int id) {
		return commentDao.findBySayIdList(id);
	}

	public void save(Comment comment, int userId, int sayMoodId) {
		// TODO Auto-generated method stub
		
	}

	public Pager<Comment> findComment(int otherId) {
		// TODO Auto-generated method stub
		return commentDao.findComment(otherId);
	}

	public void deleteInfo(int id) {
		// TODO Auto-generated method stub
		commentDao.delete(id);
	}

	@Override
	public void savehuifu(Comment comment, int userId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Pager<Comment> findPager() {
		// TODO Auto-generated method stub
		return commentDao.findPager();
	}

	@Override
	public Comment loadNew() {
		// TODO Auto-generated method stub
		return commentDao.loadNew();
	}

}
