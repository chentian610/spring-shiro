package com.cf.service;

import com.cf.entity.BusVoiceListEntity;

import java.util.List;
import java.util.Map;

/**
 * 音频质检列表
 * 
 * @author cf
 * @email 100@qq.com
 * @date 2017-11-22 18:50:21
 */
public interface BusVoiceListService {
	
	BusVoiceListEntity queryObject(Integer id);
	
	List<BusVoiceListEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(BusVoiceListEntity busVoiceList);
	
	void update(BusVoiceListEntity busVoiceList);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

	void recognize(Integer[] ids);
}
