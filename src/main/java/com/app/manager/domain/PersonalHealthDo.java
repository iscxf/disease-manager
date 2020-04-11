package com.app.manager.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 个人健康档案详情
 * 
 * @author admin
 * @email 1992lcg@163.com
 * @date 2020-03-24 22:01:56
 */
public class PersonalHealthDo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//名称
	private String name;
	//身份证号码
	private String identity;
	//性别
	private String sex;
	//出生日期
	private Date birth;
	//平均吸烟 (支/天)
	private Integer averageSmoking;
	//身高
	private Double height;
	//体重
	private Double weight;
	//BMI
	private Double bmi;
	//详细地址
	private String address;
	//联系电话
	private String moblie;
	//文化程度
	private String educationalLevel;
	//婚姻状况
	private String maritalStatus;
	//收缩压
	private Double systolicPressure;
	//舒张压
	private Double diastolicPressure;
	//空腹血糖
	private Double fastingBloodGlucose;
	//餐后血糖
	private Double postprandialBloodGlucose;
	//	血脂总胆固醇
	private Double totalCholesterol;
	//	甘油三酯
	private Double triglyceride;
	//是否糖尿病
	private String whetherDiabetes;
	//是否家族遗传
	private String familialInheritance;
	//过厚皮脂
	private String thickSebum;
	//创建时间
	private Date createTime;
	//修改时间
	private Date updateTime;

	public PersonalHealthDo() {
	}

	public PersonalHealthDo(String sex, Date birth, Double height, Double weight, Double systolicPressure, Double diastolicPressure, Double fastingBloodGlucose,
							Double postprandialBloodGlucose, Double totalCholesterol, Double triglyceride, String whetherDiabetes, String familialInheritance, String thickSebum) {
		this.sex = sex;
		this.birth = birth;
		this.height = height;
		this.weight = weight;
		this.systolicPressure = systolicPressure;
		this.diastolicPressure = diastolicPressure;
		this.fastingBloodGlucose = fastingBloodGlucose;
		this.postprandialBloodGlucose = postprandialBloodGlucose;
		this.totalCholesterol = totalCholesterol;
		this.triglyceride = triglyceride;
		this.whetherDiabetes = whetherDiabetes;
		this.familialInheritance = familialInheritance;
		this.thickSebum = thickSebum;
	}

	public PersonalHealthDo(String name, String identity, String sex, Date birth, Integer averageSmoking, Double height, Double weight, String address, String moblie, String educationalLevel,
							String maritalStatus, Double systolicPressure, Double diastolicPressure, Double fastingBloodGlucose, Double postprandialBloodGlucose,
							Double totalCholesterol, Double triglyceride, String whetherDiabetes, String familialInheritance, String thickSebum) {
		this.name = name;
		this.identity = identity;
		this.sex = sex;
		this.birth = birth;
		this.averageSmoking = averageSmoking;
		this.height = height;
		this.weight = weight;
		this.address = address;
		this.moblie = moblie;
		this.educationalLevel = educationalLevel;
		this.maritalStatus = maritalStatus;
		this.systolicPressure = systolicPressure;
		this.diastolicPressure = diastolicPressure;
		this.fastingBloodGlucose = fastingBloodGlucose;
		this.postprandialBloodGlucose = postprandialBloodGlucose;
		this.totalCholesterol = totalCholesterol;
		this.triglyceride = triglyceride;
		this.whetherDiabetes = whetherDiabetes;
		this.familialInheritance = familialInheritance;
		this.thickSebum = thickSebum;
	}

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
	 * 设置：名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：名称
	 */
	public String getName() {
		return name;
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
	/**
	 * 设置：性别
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	/**
	 * 获取：性别
	 */
	public String getSex() {
		return sex;
	}
	/**
	 * 设置：出生日期
	 */
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	/**
	 * 获取：出生日期
	 */
	public Date getBirth() {
		return birth;
	}
	/**
	 * 设置：平均吸烟 (支/天)
	 */
	public void setAverageSmoking(Integer averageSmoking) {
		this.averageSmoking = averageSmoking;
	}
	/**
	 * 获取：平均吸烟 (支/天)
	 */
	public Integer getAverageSmoking() {
		return averageSmoking;
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
	 * 设置：BMI
	 */
	public void setBmi(Double bmi) {
		this.bmi = bmi;
	}
	/**
	 * 获取：BMI
	 */
	public Double getBmi() {
		return bmi;
	}
	/**
	 * 设置：详细地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 获取：详细地址
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * 设置：联系电话
	 */
	public void setMoblie(String moblie) {
		this.moblie = moblie;
	}
	/**
	 * 获取：联系电话
	 */
	public String getMoblie() {
		return moblie;
	}
	/**
	 * 设置：文化程度
	 */
	public void setEducationalLevel(String educationalLevel) {
		this.educationalLevel = educationalLevel;
	}
	/**
	 * 获取：文化程度
	 */
	public String getEducationalLevel() {
		return educationalLevel;
	}
	/**
	 * 设置：婚姻状况
	 */
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	/**
	 * 获取：婚姻状况
	 */
	public String getMaritalStatus() {
		return maritalStatus;
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

	public Double getTotalCholesterol() {
		return totalCholesterol;
	}

	public void setTotalCholesterol(Double totalCholesterol) {
		this.totalCholesterol = totalCholesterol;
	}

	public Double getTriglyceride() {
		return triglyceride;
	}

	public void setTriglyceride(Double triglyceride) {
		this.triglyceride = triglyceride;
	}

	/**
	 * 设置：是否糖尿病
	 */
	public void setWhetherDiabetes(String whetherDiabetes) {
		this.whetherDiabetes = whetherDiabetes;
	}
	/**
	 * 获取：是否糖尿病
	 */
	public String getWhetherDiabetes() {
		return whetherDiabetes;
	}

	public String getFamilialInheritance() {
		return familialInheritance;
	}

	public void setFamilialInheritance(String familialInheritance) {
		this.familialInheritance = familialInheritance;
	}

	/**
	 * 设置：过厚皮脂
	 */
	public void setThickSebum(String thickSebum) {
		this.thickSebum = thickSebum;
	}
	/**
	 * 获取：过厚皮脂
	 */
	public String getThickSebum() {
		return thickSebum;
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

	@Override
	public String toString() {
		return "PersonalHealthDo{" +
				"id=" + id +
				", name='" + name + '\'' +
				", identity='" + identity + '\'' +
				", sex='" + sex + '\'' +
				", birth=" + birth +
				", averageSmoking=" + averageSmoking +
				", height=" + height +
				", weight=" + weight +
				", bmi=" + bmi +
				", address='" + address + '\'' +
				", moblie='" + moblie + '\'' +
				", educationalLevel='" + educationalLevel + '\'' +
				", maritalStatus='" + maritalStatus + '\'' +
				", systolicPressure=" + systolicPressure +
				", diastolicPressure=" + diastolicPressure +
				", fastingBloodGlucose=" + fastingBloodGlucose +
				", postprandialBloodGlucose=" + postprandialBloodGlucose +
				", totalCholesterol=" + totalCholesterol +
				", triglyceride=" + triglyceride +
				", whetherDiabetes='" + whetherDiabetes + '\'' +
				", familialInheritance='" + familialInheritance + '\'' +
				", thickSebum='" + thickSebum + '\'' +
				", createTime=" + createTime +
				", updateTime=" + updateTime +
				'}';
	}
}
