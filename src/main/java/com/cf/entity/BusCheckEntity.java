package com.cf.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author cf
 * @email 100@qq.com
 * @date 2017-11-17 15:20:26
 */
public class BusCheckEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//规则ID
	private Integer ruleId;
	//音频url
	private String voiceUrl;
	//识别文本
	private Double speedText;
	//
	private String matchDegree;
	//检测时间
	private Date createDate;

	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：
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
	 * 设置：音频url
	 */
	public void setVoiceUrl(String voiceUrl) {
		this.voiceUrl = voiceUrl;
	}
	/**
	 * 获取：音频url
	 */
	public String getVoiceUrl() {
		return voiceUrl;
	}
	/**
	 * 设置：识别文本
	 */
	public void setSpeedText(Double speedText) {
		this.speedText = speedText;
	}
	/**
	 * 获取：识别文本
	 */
	public Double getSpeedText() {
		return speedText;
	}
	/**
	 * 设置：
	 */
	public void setMatchDegree(String matchDegree) {
		this.matchDegree = matchDegree;
	}
	/**
	 * 获取：
	 */
	public String getMatchDegree() {
		return matchDegree;
	}
	/**
	 * 设置：检测时间
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * 获取：检测时间
	 */
	public Date getCreateDate() {
		return createDate;
	}
}
