package com.cf.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author cf
 * @email 100@qq.com
 * @date 2017-11-21 13:59:29
 */
public class BusRuleListEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private Integer id;
	//规则ID
	private Integer ruleId;
	//规则文本
	private String ruleText;
	//例子
	private String demoText;
	//标准校验文本
	private String standardText;
	//权重
	private Integer weight;
	//创建时间
	private Date createTime;
	//创建人
	private String createBy;
	//更新时间
	private Date updateTime;
	//更新人
	private Integer updateBy;
	//版本号
	private Integer version;

	/**
	 * 设置：主键
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：主键
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：规则ID
	 */
	public void setRuleId(Integer ruleId) {
		this.ruleId = ruleId;
	}
	/**
	 * 获取：规则ID
	 */
	public Integer getRuleId() {
		return ruleId;
	}
	/**
	 * 设置：规则文本
	 */
	public void setRuleText(String ruleText) {
		this.ruleText = ruleText;
	}
	/**
	 * 获取：规则文本
	 */
	public String getRuleText() {
		return ruleText;
	}
	/**
	 * 设置：例子
	 */
	public void setDemoText(String demoText) {
		this.demoText = demoText;
	}
	/**
	 * 获取：例子
	 */
	public String getDemoText() {
		return demoText;
	}
	/**
	 * 设置：标准校验文本
	 */
	public void setStandardText(String standardText) {
		this.standardText = standardText;
	}
	/**
	 * 获取：标准校验文本
	 */
	public String getStandardText() {
		return standardText;
	}
	/**
	 * 设置：权重
	 */
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	/**
	 * 获取：权重
	 */
	public Integer getWeight() {
		return weight;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：创建人
	 */
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	/**
	 * 获取：创建人
	 */
	public String getCreateBy() {
		return createBy;
	}
	/**
	 * 设置：更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * 设置：更新人
	 */
	public void setUpdateBy(Integer updateBy) {
		this.updateBy = updateBy;
	}
	/**
	 * 获取：更新人
	 */
	public Integer getUpdateBy() {
		return updateBy;
	}
	/**
	 * 设置：版本号
	 */
	public void setVersion(Integer version) {
		this.version = version;
	}
	/**
	 * 获取：版本号
	 */
	public Integer getVersion() {
		return version;
	}
}
