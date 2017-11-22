package com.cf.service;

import com.cf.entity.BusRuleSttEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author cf
 * @email 100@qq.com
 * @date 2017-11-21 13:59:30
 */
public interface BusRuleSttService {
	
	BusRuleSttEntity queryObject(Integer id);
	
	List<BusRuleSttEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(BusRuleSttEntity busRuleStt);
	
	void update(BusRuleSttEntity busRuleStt);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
