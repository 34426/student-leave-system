package com.xznu.edu.leave.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xznu.edu.leave.base.dao.impl.BaseDaoImpl;
import com.xznu.edu.leave.dao.CommentDao;
import com.xznu.edu.leave.model.Comment;
import com.xznu.edu.leave.model.Student;
import com.xznu.edu.leave.utils.Pager;

@Repository("commentDao")
public class CommentDaoImpl extends BaseDaoImpl<Comment>  implements CommentDao{

	//根据shuoshuoid查询一级评论
	public Pager<Comment> commentDao(int id) {
		String hql = "from Comment where sayMood.id = :id and fatherComment is null order by createTime desc";
		Map<String,Object> alias = new HashMap<String,Object>();
		alias.put("id", id);
		return findByAlias(hql, alias);
	}

	public List<Comment> listNextComByFatherId(int id) {
		String hql = "from Comment where fatherComment.id = :id order by createTime desc";
		Map<String,Object> alias = new HashMap<String,Object>();
		alias.put("id", id);
		return listByAlias(hql, alias);
	}

	public List<Comment> findBySayIdList(int id) {
		String hql = "from Comment where sayMood.id = :id and fatherComment is null order by createTime desc";
		Map<String,Object> alias = new HashMap<String,Object>();
		alias.put("id", id);
		return listByAlias(hql, alias);
	}

	public Pager<Comment> findComment(int otherId) {
		String hql = "from Comment where news.id = :id  order by createTime desc";
		Map<String,Object> alias = new HashMap<String,Object>();
		alias.put("id", otherId);
		return findByAlias(hql, alias);
	}
/*	private int id;
	private String content;//内容
	private Student student;//评论人
	private Date createTime;
	private int isDelete;*/
	@Override
	public Pager<Comment> findPager() {
		String hql = "from Comment where isDelete = 2  order by createTime desc";
		Map<String,Object> alias = new HashMap<String,Object>();
		return findByAlias(hql, null);
	}

	@Override
	public Comment loadNew() {
		// TODO Auto-generated method stub
		String hql = "from Comment where isDelete = 2  order by createTime desc";
	List<Comment> 	ss = list(hql);
	if(ss!=null && ss.size()>0){
		return ss.get(0);
	}else{
		return null;
	}
		
	}

}
