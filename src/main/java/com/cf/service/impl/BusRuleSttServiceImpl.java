package com.cf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.cf.dao.BusRuleSttDao;
import com.cf.entity.BusRuleSttEntity;
import com.cf.service.BusRuleSttService;



@Service("busRuleSttService")
public class BusRuleSttServiceImpl implements BusRuleSttService {
	@Autowired
	private BusRuleSttDao busRuleSttDao;
	
	@Override
	public BusRuleSttEntity queryObject(Integer id){
		return busRuleSttDao.queryObject(id);
	}
	
	@Override
	public List<BusRuleSttEntity> queryList(Map<String, Object> map){
		return busRuleSttDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return busRuleSttDao.queryTotal(map);
	}
	
	@Override
	public void save(BusRuleSttEntity busRuleStt){
		busRuleSttDao.save(busRuleStt);
	}
	
	@Override
	public void update(BusRuleSttEntity busRuleStt){
		busRuleSttDao.update(busRuleStt);
	}
	
	@Override
	public void delete(Integer id){
		busRuleSttDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		busRuleSttDao.deleteBatch(ids);
	}
	
}
