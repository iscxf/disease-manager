package com.app.manager.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
* @author fei
 * @date 2020-03-30 21:46:03
 */
public class TrainDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//性别
	private String sex;
	//是否高血压
	private String hightPressure;
	//是否高血脂
	private String cholesterol;
	//过厚皮脂
	private String thickSebum;
	//是否家族遗传
	private String familialInheritance;
	//是否糖尿病
	private String whetherDiabetes;

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
	 * 设置：是否高血压
	 */
	public void setHightPressure(String hightPressure) {
		this.hightPressure = hightPressure;
	}
	/**
	 * 获取：是否高血压
	 */
	public String getHightPressure() {
		return hightPressure;
	}
	/**
	 * 设置：是否高血脂
	 */
	public void setCholesterol(String cholesterol) {
		this.cholesterol = cholesterol;
	}
	/**
	 * 获取：是否高血脂
	 */
	public String getCholesterol() {
		return cholesterol;
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
	 * 设置：是否家族遗传
	 */
	public void setFamilialInheritance(String familialInheritance) {
		this.familialInheritance = familialInheritance;
	}
	/**
	 * 获取：是否家族遗传
	 */
	public String getFamilialInheritance() {
		return familialInheritance;
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
}
