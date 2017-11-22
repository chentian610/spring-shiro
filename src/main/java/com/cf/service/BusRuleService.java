package com.cf.service;

import com.cf.entity.BusRuleEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author cf
 * @email 100@qq.com
 * @date 2017-11-17 13:51:14
 */
public interface BusRuleService {
	
	BusRuleEntity queryObject(Integer id);
	
	List<BusRuleEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(BusRuleEntity busRule);
	
	void update(BusRuleEntity busRule);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
