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

import com.cf.entity.BusRuleSttListEntity;
import com.cf.service.BusRuleSttListService;
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
@RequestMapping("busrulesttlist")
public class BusRuleSttListController {
	@Autowired
	private BusRuleSttListService busRuleSttListService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("busrulesttlist:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<BusRuleSttListEntity> busRuleSttListList = busRuleSttListService.queryList(query);
		int total = busRuleSttListService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(busRuleSttListList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("busrulesttlist:info")
	public R info(@PathVariable("id") Integer id){
		BusRuleSttListEntity busRuleSttList = busRuleSttListService.queryObject(id);
		
		return R.ok().put("busRuleSttList", busRuleSttList);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("busrulesttlist:save")
	public R save(@RequestBody BusRuleSttListEntity busRuleSttList){
		busRuleSttListService.save(busRuleSttList);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("busrulesttlist:update")
	public R update(@RequestBody BusRuleSttListEntity busRuleSttList){
		busRuleSttListService.update(busRuleSttList);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("busrulesttlist:delete")
	public R delete(@RequestBody Integer[] ids){
		busRuleSttListService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
