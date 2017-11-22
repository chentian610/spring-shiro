package com.cf.service;

import com.cf.entity.BusRuleListEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author cf
 * @email 100@qq.com
 * @date 2017-11-21 13:59:29
 */
public interface BusRuleListService {
	
	BusRuleListEntity queryObject(Integer id);
	
	List<BusRuleListEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(BusRuleListEntity busRuleList);
	
	void update(BusRuleListEntity busRuleList);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
