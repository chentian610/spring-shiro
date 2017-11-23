package com.cf.service.impl;

import com.cf.entity.BusRuleListEntity;
import com.cf.service.BusRuleListService;
import com.cf.utils.BaiduApiUtil;
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
	@Autowired
	private BaiduApiUtil baiduApiUtil;
	@Autowired
	private BusRuleListService busRuleListService;


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
		StringBuffer sbStandartText = baiduApiUtil.change2BasicSentence(busRule.getDemoText());
		busRule.setStandardText(sbStandartText.toString());
		busRuleDao.save(busRule);
		String[] contents = baiduApiUtil.replaceAllSigns(busRule.getDemoText()).split(",");
		String[] standard_texts = baiduApiUtil.replaceAllSigns(sbStandartText.toString()).split(",");
		for (int i=0;i<contents.length;i++) {
			BusRuleListEntity ruleListEntity = new BusRuleListEntity();
			ruleListEntity.setRuleId(busRule.getId());
			ruleListEntity.setDemoText(contents[i]);
			ruleListEntity.setStandardText(standard_texts[i]);
			ruleListEntity.setWeight(1);
			busRuleListService.save(ruleListEntity);
		}
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
