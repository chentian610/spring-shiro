package com.cf.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author cf
 * @email 100@qq.com
 * @date 2017-11-21 13:59:30
 */
public class BusRuleSttListEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键，自增长
	private Integer id;
	//分段ID
	private Integer ruleListId;
	//业务员ID
	private Integer salerId;
	//语音识别文本
	private String tts;
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
	 * 设置：业务员ID
	 */
	public void setSalerId(Integer salerId) {
		this.salerId = salerId;
	}
	/**
	 * 获取：业务员ID
	 */
	public Integer getSalerId() {
		return salerId;
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
}
