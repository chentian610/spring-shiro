package com.cf.service;

import com.cf.entity.BusCheckEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author cf
 * @email 100@qq.com
 * @date 2017-11-17 15:20:26
 */
public interface BusCheckService {
	
	BusCheckEntity queryObject(Integer id);
	
	List<BusCheckEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(BusCheckEntity busCheck);
	
	void update(BusCheckEntity busCheck);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
