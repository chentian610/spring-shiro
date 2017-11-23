package com.cf.dao;

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
public interface BusRuleSttListDao extends BaseDao<BusRuleSttListEntity> {

    List<BusRuleSttListEntity> queryListByRuleID(Map<String, Object> map);

    Double queryScoreByRuleListID(Map<String, Object> map);
}
