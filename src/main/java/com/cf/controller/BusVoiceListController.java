package com.cf.controller;

import java.util.List;
import java.util.Map;

import com.cf.service.BusRuleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cf.entity.BusVoiceListEntity;
import com.cf.service.BusVoiceListService;
import com.cf.utils.PageUtils;
import com.cf.utils.Query;
import com.cf.utils.R;


/**
 * 音频质检列表
 * 
 * @author cf
 * @email 100@qq.com
 * @date 2017-11-22 18:50:21
 */
@RestController
@RequestMapping("busvoicelist")
public class BusVoiceListController {
	@Autowired
	private BusVoiceListService busVoiceListService;

	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("busvoicelist:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<BusVoiceListEntity> busVoiceListList = busVoiceListService.queryList(query);
		int total = busVoiceListService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(busVoiceListList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("busvoicelist:info")
	public R info(@PathVariable("id") Integer id){
		BusVoiceListEntity busVoiceList = busVoiceListService.queryObject(id);
		
		return R.ok().put("busVoiceList", busVoiceList);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("busvoicelist:save")
	public R save(@RequestBody BusVoiceListEntity busVoiceList){
		busVoiceListService.save(busVoiceList);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("busvoicelist:update")
	public R update(@RequestBody BusVoiceListEntity busVoiceList){
		busVoiceListService.update(busVoiceList);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("busvoicelist:delete")
	public R delete(@RequestBody Integer[] ids){
		busVoiceListService.deleteBatch(ids);
		
		return R.ok();
	}

	/**
	 * 删除
	 */
	@RequestMapping("/recognize")
	@RequiresPermissions("busvoicelist:recognize")
	public R recognize(@RequestBody Integer[] ids){
		busVoiceListService.recognize(ids);

		return R.ok();
	}
	
}
