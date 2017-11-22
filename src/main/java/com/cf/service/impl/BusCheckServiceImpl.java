package com.cf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.cf.dao.BusCheckDao;
import com.cf.entity.BusCheckEntity;
import com.cf.service.BusCheckService;



@Service("busCheckService")
public class BusCheckServiceImpl implements BusCheckService {
	@Autowired
	private BusCheckDao busCheckDao;
	
	@Override
	public BusCheckEntity queryObject(Integer id){
		return busCheckDao.queryObject(id);
	}
	
	@Override
	public List<BusCheckEntity> queryList(Map<String, Object> map){
		return busCheckDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return busCheckDao.queryTotal(map);
	}
	
	@Override
	public void save(BusCheckEntity busCheck){
		busCheckDao.save(busCheck);
	}
	
	@Override
	public void update(BusCheckEntity busCheck){
		busCheckDao.update(busCheck);
	}
	
	@Override
	public void delete(Integer id){
		busCheckDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		busCheckDao.deleteBatch(ids);
	}
	
}
