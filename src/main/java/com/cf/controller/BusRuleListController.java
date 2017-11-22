package com.cf.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cf.entity.BusRuleListEntity;
import com.cf.service.BusRuleListService;
import com.cf.utils.PageUtils;
import com.cf.utils.Query;
import com.cf.utils.R;


/**
 * 
 * 
 * @author cf
 * @email 100@qq.com
 * @date 2017-11-21 13:59:29
 */
@RestController
@RequestMapping("busrulelist")
public class BusRuleListController {
	@Autowired
	private BusRuleListService busRuleListService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("busrulelist:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<BusRuleListEntity> busRuleListList = busRuleListService.queryList(query);
		int total = busRuleListService.queryTotal(query);

		List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
		for (BusRuleListEntity item : busRuleListList) {
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("id",item.getId());
			map.put("ruleId",item.getRuleId());
			map.put("ruleText",item.getRuleText());
			map.put("demoText",item.getDemoText());
			map.put("weight",item.getWeight());
			list.add(map);
		}
		PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("busrulelist:info")
	public R info(@PathVariable("id") Integer id){
		BusRuleListEntity busRuleList = busRuleListService.queryObject(id);
		
		return R.ok().put("busRuleList", busRuleList);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("busrulelist:save")
	public R save(@RequestBody BusRuleListEntity busRuleList){
		busRuleListService.save(busRuleList);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("busrulelist:update")
	public R update(@RequestBody BusRuleListEntity busRuleList){
		busRuleListService.update(busRuleList);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("busrulelist:delete")
	public R delete(@RequestBody Integer[] ids){
		busRuleListService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
