package com.cf.service.impl;

import com.cf.dao.BusVoiceListDao;
import com.cf.entity.BusRuleEntity;
import com.cf.entity.BusRuleListEntity;
import com.cf.entity.BusRuleSttListEntity;
import com.cf.entity.BusVoiceListEntity;
import com.cf.service.BusRuleListService;
import com.cf.service.BusRuleService;
import com.cf.service.BusRuleSttListService;
import com.cf.service.BusVoiceListService;
import com.cf.utils.BaiduApiUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



@Service("busVoiceListService")
public class BusVoiceListServiceImpl implements BusVoiceListService {
	protected static Logger logger = LoggerFactory.getLogger(BusVoiceListService.class);
	@Autowired
	private BusVoiceListDao busVoiceListDao;
	@Autowired
	private BusRuleListService busRuleListService;
	@Autowired
	private BusRuleService busRuleService;
	@Autowired
	private BaiduApiUtil baiduApiUtil;
	@Autowired
	private BusRuleSttListService busRuleSttListService;

	@Override
	public BusVoiceListEntity queryObject(Integer id){
		return busVoiceListDao.queryObject(id);
	}
	
	@Override
	public List<BusVoiceListEntity> queryList(Map<String, Object> map){
		List<BusVoiceListEntity> list = busVoiceListDao.queryList(map);
		for (BusVoiceListEntity item: list) {
			BusRuleEntity rule = busRuleService.queryObject(item.getRuleId());
			item.setRule_name(rule.getInsuranceType());
		}
		return list;
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return busVoiceListDao.queryTotal(map);
	}
	
	@Override
	public void save(BusVoiceListEntity busVoiceList){
		busVoiceListDao.save(busVoiceList);
	}
	
	@Override
	public void update(BusVoiceListEntity busVoiceList){
		busVoiceListDao.update(busVoiceList);
	}
	
	@Override
	public void delete(Integer id){
		busVoiceListDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		busVoiceListDao.deleteBatch(ids);
	}

	@Override
	public void recognize(Integer[] ids) {
		Map map = new HashMap();
		for (Integer id:ids) {
			BusVoiceListEntity entity = busVoiceListDao.queryObject(id);
			String stt = baiduApiUtil.Speed2Text(entity.getFileUrl());
			map.put("ruleId",entity.getRuleId());
			List<BusRuleListEntity> ruleList = busRuleListService.queryList(map);
			//保存的明细得分
			List<BusRuleSttListEntity> list = baiduApiUtil.compareSimilarDegree(ruleList,stt);
			Map<Integer,BusRuleSttListEntity> weightMap = new HashMap();
			Double totalScore = 0.0;//权重总分
			for (BusRuleSttListEntity weightEntity :list) {
				weightMap.put(weightEntity.getRuleListId(),weightEntity);
				totalScore+=weightEntity.getWeight();
			}
			Double targetScore = 0.0;
			for (BusRuleSttListEntity map1 : list) {
				BusRuleSttListEntity weightEntity = weightMap.get(map1.getRuleListId());
				map1.setWeight(weightEntity.getWeight());
				map1.setDemoText(weightEntity.getDemoText());
				map1.setVoiceId(id);
				busRuleSttListService.save(map1);
				targetScore+=weightEntity.getWeight()*map1.getScore();
			}
			Integer score = (int) Math.rint(targetScore*100/totalScore);
			BusVoiceListEntity busVoiceListEntity = new BusVoiceListEntity();
			busVoiceListEntity.setId(id);
			busVoiceListEntity.setIsDone(1);
			busVoiceListEntity.setScore(score+"");
			busVoiceListEntity.setRecgnizeText(stt);
			busVoiceListDao.update(busVoiceListEntity);
		}
	}
	
}
