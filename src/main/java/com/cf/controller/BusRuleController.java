package com.cf.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cf.entity.BusRuleEntity;
import com.cf.service.BusRuleService;
import com.cf.utils.PageUtils;
import com.cf.utils.Query;
import com.cf.utils.R;


/**
 * 
 * 
 * @author cf
 * @email 100@qq.com
 * @date 2017-11-17 13:51:14
 */
@RestController
@RequestMapping("busrule")
public class BusRuleController {
	@Autowired
	private BusRuleService busRuleService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("busrule:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<BusRuleEntity> busRuleList = busRuleService.queryList(query);
		int total = busRuleService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(busRuleList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("busrule:info")
	public R info(@PathVariable("id") Integer id){
		BusRuleEntity busRule = busRuleService.queryObject(id);
		
		return R.ok().put("busRule", busRule);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("busrule:save")
	public R save(@RequestBody BusRuleEntity busRule){
		busRuleService.save(busRule);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("busrule:update")
	public R update(@RequestBody BusRuleEntity busRule){
		busRuleService.update(busRule);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("busrule:delete")
	public R delete(@RequestBody Integer[] ids){
		busRuleService.deleteBatch(ids);
		return R.ok();
	}
	
}
