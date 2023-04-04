package com.xznu.edu.leave.service;

import java.util.List;
import java.util.Map;

public interface BaseService<T> {
	    public void save(T entity);  
	  
	    public void update(T entity);  
	  
	    public void delete(int id);  
	  
	    public T getById(int id);  
	  
	    public List<T> getByHQL(String hql, Map<String, Object> alias); 
	    
	    public T getOneByHql(String hql, Map<String, Object> alias);
}
