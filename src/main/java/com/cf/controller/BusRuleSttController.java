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

import com.cf.entity.BusRuleSttEntity;
import com.cf.service.BusRuleSttService;
import com.cf.utils.PageUtils;
import com.cf.utils.Query;
import com.cf.utils.R;


/**
 * 
 * 
 * @author cf
 * @email 100@qq.com
 * @date 2017-11-21 13:59:30
 */
@RestController
@RequestMapping("busrulestt")
public class BusRuleSttController {
	@Autowired
	private BusRuleSttService busRuleSttService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("busrulestt:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<BusRuleSttEntity> busRuleSttList = busRuleSttService.queryList(query);
		int total = busRuleSttService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(busRuleSttList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("busrulestt:info")
	public R info(@PathVariable("id") Integer id){
		BusRuleSttEntity busRuleStt = busRuleSttService.queryObject(id);
		
		return R.ok().put("busRuleStt", busRuleStt);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("busrulestt:save")
	public R save(@RequestBody BusRuleSttEntity busRuleStt){
		busRuleSttService.save(busRuleStt);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("busrulestt:update")
	public R update(@RequestBody BusRuleSttEntity busRuleStt){
		busRuleSttService.update(busRuleStt);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("busrulestt:delete")
	public R delete(@RequestBody Integer[] ids){
		busRuleSttService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
