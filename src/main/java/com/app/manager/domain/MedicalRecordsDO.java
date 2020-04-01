package com.app.manager.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 会诊记录
 * 
 * @author fei
 * @email 1992lcg@163.com
 * @date 2020-03-29 10:28:05
 */
public class MedicalRecordsDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//身份证号码
	private String identity;
	//姓名
	private String name;
	//身高
	private Double height;
	//体重
	private Double weight;
	//收缩压
	private Double systolicPressure;
	//舒张压
	private Double diastolicPressure;
	//空腹血糖
	private Double fastingBloodGlucose;
	//餐后血糖
	private Double postprandialBloodGlucose;
	//血脂总胆固醇
	private Double totalCholesterol;
	//甘油三酯
	private Double triglyceride;
	//症状描述
	private String symptom;
	//创建时间
	private Date createTime;
	//修改时间
	private Date updateTime;

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
	 * 设置：身份证号码
	 */
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	/**
	 * 获取：身份证号码
	 */
	public String getIdentity() {
		return identity;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 设置：身高
	 */
	public void setHeight(Double height) {
		this.height = height;
	}
	/**
	 * 获取：身高
	 */
	public Double getHeight() {
		return height;
	}
	/**
	 * 设置：体重
	 */
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	/**
	 * 获取：体重
	 */
	public Double getWeight() {
		return weight;
	}
	/**
	 * 设置：收缩压
	 */
	public void setSystolicPressure(Double systolicPressure) {
		this.systolicPressure = systolicPressure;
	}
	/**
	 * 获取：收缩压
	 */
	public Double getSystolicPressure() {
		return systolicPressure;
	}
	/**
	 * 设置：舒张压
	 */
	public void setDiastolicPressure(Double diastolicPressure) {
		this.diastolicPressure = diastolicPressure;
	}
	/**
	 * 获取：舒张压
	 */
	public Double getDiastolicPressure() {
		return diastolicPressure;
	}
	/**
	 * 设置：空腹血糖
	 */
	public void setFastingBloodGlucose(Double fastingBloodGlucose) {
		this.fastingBloodGlucose = fastingBloodGlucose;
	}
	/**
	 * 获取：空腹血糖
	 */
	public Double getFastingBloodGlucose() {
		return fastingBloodGlucose;
	}
	/**
	 * 设置：餐后血糖
	 */
	public void setPostprandialBloodGlucose(Double postprandialBloodGlucose) {
		this.postprandialBloodGlucose = postprandialBloodGlucose;
	}
	/**
	 * 获取：餐后血糖
	 */
	public Double getPostprandialBloodGlucose() {
		return postprandialBloodGlucose;
	}
	/**
	 * 设置：血脂总胆固醇
	 */
	public void setTotalCholesterol(Double totalCholesterol) {
		this.totalCholesterol = totalCholesterol;
	}
	/**
	 * 获取：血脂总胆固醇
	 */
	public Double getTotalCholesterol() {
		return totalCholesterol;
	}
	/**
	 * 设置：甘油三酯
	 */
	public void setTriglyceride(Double triglyceride) {
		this.triglyceride = triglyceride;
	}
	/**
	 * 获取：甘油三酯
	 */
	public Double getTriglyceride() {
		return triglyceride;
	}
	/**
	 * 设置：症状描述
	 */
	public void setSymptom(String symptom) {
		this.symptom = symptom;
	}
	/**
	 * 获取：症状描述
	 */
	public String getSymptom() {
		return symptom;
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
	 * 设置：修改时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：修改时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
}
