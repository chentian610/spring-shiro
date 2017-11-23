package com.cf.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 音频质检列表
 * 
 * @author cf
 * @email 100@qq.com
 * @date 2017-11-22 18:50:21
 */
public class BusVoiceListEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键，自增长
	private Integer id;
	//销售员姓名
	private String salerName;
	//规则ID
	private Integer ruleId;
	//规则名称
	private String ruleName;
	//音频url
	private String fileUrl;
	//是否已经识别
	private Integer isDone;
	//得分
	private String score;
	//识别时间
	private Date recgnizeTime;

	//识别时间
	private String recgnizeText;

	/**
	 * 设置：主键，自增长
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：主键，自增长
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：销售员姓名
	 */
	public void setSalerName(String salerName) {
		this.salerName = salerName;
	}
	/**
	 * 获取：销售员姓名
	 */
	public String getSalerName() {
		return salerName;
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
	 * 设置：音频url
	 */
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	/**
	 * 获取：音频url
	 */
	public String getFileUrl() {
		return fileUrl;
	}
	/**
	 * 设置：是否已经识别
	 */
	public void setIsDone(Integer isDone) {
		this.isDone = isDone;
	}
	/**
	 * 获取：是否已经识别
	 */
	public Integer getIsDone() {
		return isDone;
	}

	public String getRule_name() {
		return ruleName;
	}

	public void setRule_name(String ruleName) {
		this.ruleName = ruleName;
	}

	/**
	 * 设置：得分
	 */
	public void setScore(String score) {
		this.score = score;
	}
	/**
	 * 获取：得分
	 */
	public String getScore() {
		return score;
	}
	/**
	 * 设置：识别时间
	 */
	public void setRecgnizeTime(Date recgnizeTime) {
		this.recgnizeTime = recgnizeTime;
	}
	/**
	 * 获取：识别时间
	 */
	public Date getRecgnizeTime() {
		return recgnizeTime;
	}

	public String getRecgnizeText() {
		return recgnizeText;
	}

	public void setRecgnizeText(String recgnizeText) {
		this.recgnizeText = recgnizeText;
	}
}
