package com.xznu.edu.leave.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;


import com.xznu.edu.leave.base.dao.impl.BaseDaoImpl;
import com.xznu.edu.leave.dao.NoticeInfoDao;
import com.xznu.edu.leave.model.NoticeInfo;
import com.xznu.edu.leave.utils.Pager;
@Repository
public class NoticeInfoDaoImpl extends BaseDaoImpl<NoticeInfo> implements NoticeInfoDao {

	@Override
	public Pager<NoticeInfo> findPager(NoticeInfo noticeInfo) {
		 if(noticeInfo.getContent() !=null && !"".equals(noticeInfo.getContent())){
	    	   String hql = "from NoticeInfo where content like :content order by createTime desc";
	    	   Map<String,Object> alias = new HashMap<String,Object>();
	   		   alias.put("content", "%" + noticeInfo.getContent() + "%");
	   		  return findByAlias(hql, alias);
	       }else{
	    	   String hql = "from NoticeInfo order by createTime desc";
				return findByAlias(hql, null);
	       }
	}

	@Override
	public NoticeInfo loadNew() {
		// TODO Auto-generated method stub
		 String hql = "from NoticeInfo order by createTime desc";
		 List<NoticeInfo> ns = list(hql);
		  if(ns != null && ns.size()>0){
			  return ns.get(0);
		  }
		return null;
	}


}
