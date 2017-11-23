package com.cf.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 识别结果明细列表
 * 
 * @author cf
 * @email 100@qq.com
 * @date 2017-11-22 19:25:59
 */
public class BusRuleSttListEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键，自增长
	private Integer id;
	//分段ID
	private Integer voiceId;
	//分段ID
	private Integer ruleListId;
	//业务员姓名
	private Integer salerName;
	//
	private String demoText;
	//语音识别文本
	private String tts;
	//权重
	private Integer weight;
	//得分
	private Double score;
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
	 * 设置：分段ID
	 */
	public void setRuleListId(Integer ruleListId) {
		this.ruleListId = ruleListId;
	}
	/**
	 * 获取：分段ID
	 */
	public Integer getRuleListId() {
		return ruleListId;
	}
	/**
	 * 设置：业务员姓名
	 */
	public void setSalerName(Integer salerName) {
		this.salerName = salerName;
	}
	/**
	 * 获取：业务员姓名
	 */
	public Integer getSalerName() {
		return salerName;
	}
	/**
	 * 设置：
	 */
	public void setDemoText(String demoText) {
		this.demoText = demoText;
	}
	/**
	 * 获取：
	 */
	public String getDemoText() {
		return demoText;
	}
	/**
	 * 设置：语音识别文本
	 */
	public void setTts(String tts) {
		this.tts = tts;
	}
	/**
	 * 获取：语音识别文本
	 */
	public String getTts() {
		return tts;
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
	 * 设置：得分
	 */
	public void setScore(Double score) {
		this.score = score;
	}
	/**
	 * 获取：得分
	 */
	public Double getScore() {
		return score;
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

	public Integer getVoiceId() {
		return voiceId;
	}

	public void setVoiceId(Integer voiceId) {
		this.voiceId = voiceId;
	}
}
