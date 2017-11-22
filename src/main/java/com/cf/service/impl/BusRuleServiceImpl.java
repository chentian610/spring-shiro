package com.cf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.cf.dao.BusRuleDao;
import com.cf.entity.BusRuleEntity;
import com.cf.service.BusRuleService;



@Service("busRuleService")
public class BusRuleServiceImpl implements BusRuleService {
	@Autowired
	private BusRuleDao busRuleDao;
	
	@Override
	public BusRuleEntity queryObject(Integer id){
		return busRuleDao.queryObject(id);
	}
	
	@Override
	public List<BusRuleEntity> queryList(Map<String, Object> map){
		return busRuleDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return busRuleDao.queryTotal(map);
	}
	
	@Override
	public void save(BusRuleEntity busRule){
		busRuleDao.save(busRule);
	}
	
	@Override
	public void update(BusRuleEntity busRule){
		busRuleDao.update(busRule);
	}
	
	@Override
	public void delete(Integer id){
		busRuleDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		busRuleDao.deleteBatch(ids);
	}
	
}
