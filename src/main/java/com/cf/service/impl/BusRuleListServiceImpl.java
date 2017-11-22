package com.cf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.cf.dao.BusRuleListDao;
import com.cf.entity.BusRuleListEntity;
import com.cf.service.BusRuleListService;



@Service("busRuleListService")
public class BusRuleListServiceImpl implements BusRuleListService {
	@Autowired
	private BusRuleListDao busRuleListDao;
	
	@Override
	public BusRuleListEntity queryObject(Integer id){
		return busRuleListDao.queryObject(id);
	}
	
	@Override
	public List<BusRuleListEntity> queryList(Map<String, Object> map){
		return busRuleListDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return busRuleListDao.queryTotal(map);
	}
	
	@Override
	public void save(BusRuleListEntity busRuleList){
		busRuleListDao.save(busRuleList);
	}
	
	@Override
	public void update(BusRuleListEntity busRuleList){
		busRuleListDao.update(busRuleList);
	}
	
	@Override
	public void delete(Integer id){
		busRuleListDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		busRuleListDao.deleteBatch(ids);
	}
	
}
