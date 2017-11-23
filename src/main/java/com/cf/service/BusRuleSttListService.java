package com.cf.service;

import com.cf.entity.BusRuleSttListEntity;

import java.util.List;
import java.util.Map;

/**
 * 识别结果明细列表
 * 
 * @author cf
 * @email 100@qq.com
 * @date 2017-11-22 19:25:59
 */
public interface BusRuleSttListService {
	
	BusRuleSttListEntity queryObject(Integer id);
	
	List<BusRuleSttListEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(BusRuleSttListEntity busRuleSttList);
	
	void update(BusRuleSttListEntity busRuleSttList);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

	List<BusRuleSttListEntity> queryListByRuleID(Map<String, Object> map);

}
