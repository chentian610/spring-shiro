package com.cf.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author cf
 * @email 100@qq.com
 * @date 2017-11-17 13:51:14
 */
public class BusRuleEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private Integer id;
	//
	private String insuranceType;
	//
	private String ruleText;

	private String demoText;
	//
	private String standardText;


	public String getStandardText() {
		return standardText;
	}

	public void setStandardText(String standardText) {
		this.standardText = standardText;
	}

	public String getDemoText() {
		return demoText;
	}

	public void setDemoText(String demoText) {
		this.demoText = demoText;
	}

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
	 * 设置：
	 */
	public void setInsuranceType(String insuranceType) {
		this.insuranceType = insuranceType;
	}
	/**
	 * 获取：
	 */
	public String getInsuranceType() {
		return insuranceType;
	}
	/**
	 * 设置：
	 */
	public void setRuleText(String ruleText) {
		this.ruleText = ruleText;
	}
	/**
	 * 获取：
	 */
	public String getRuleText() {
		return ruleText;
	}
}
