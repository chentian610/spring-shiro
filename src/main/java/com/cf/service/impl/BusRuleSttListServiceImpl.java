package com.cf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.cf.dao.BusRuleSttListDao;
import com.cf.entity.BusRuleSttListEntity;
import com.cf.service.BusRuleSttListService;



@Service("busRuleSttListService")
public class BusRuleSttListServiceImpl implements BusRuleSttListService {
	@Autowired
	private BusRuleSttListDao busRuleSttListDao;
	
	@Override
	public BusRuleSttListEntity queryObject(Integer id){
		return busRuleSttListDao.queryObject(id);
	}
	
	@Override
	public List<BusRuleSttListEntity> queryList(Map<String, Object> map){
		return busRuleSttListDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return busRuleSttListDao.queryTotal(map);
	}
	
	@Override
	public void save(BusRuleSttListEntity busRuleSttList){
		busRuleSttListDao.save(busRuleSttList);
	}
	
	@Override
	public void update(BusRuleSttListEntity busRuleSttList){
		busRuleSttListDao.update(busRuleSttList);
	}
	
	@Override
	public void delete(Integer id){
		busRuleSttListDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		busRuleSttListDao.deleteBatch(ids);
	}
	
}
