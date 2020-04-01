package com.app.manager.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 疾病百科
 * 
 * @author fei
 * @email 1992lcg@163.com
 * @date 2020-03-28 11:19:48
 */
public class DiseaseDO implements Serializable {
	private static final long serialVersionUID = 1L;

	//疾病名称
	private String disease;
	//科室
	private String field;
	//简介
	private String general;
	//症状
	private String symptom;
	//病因
	private String reason;
	//诊断
	private String diagnosis;
	//治疗
	private String treatment;
	//生活建议
	private String life;
	//预防
	private String prevention;
	//相关医生
	private String doctors;
	//相关医院
	private String hospitals;

	public DiseaseDO() {
	}

	public DiseaseDO(List<String> list) {
		this.disease = list.get(1);
		this.field = list.get(2);
		this.general = list.get(3);
		this.symptom = list.get(4);
		this.reason = list.get(5);
		this.diagnosis = list.get(6);
		this.treatment = list.get(7);
		this.life = list.get(8);
		this.prevention = list.get(9);
		this.doctors = list.get(10);
		this.hospitals = list.get(11);
	}

	public DiseaseDO(Map<Integer, String> map) {
		this.disease = map.get(1);
		this.field = map.get(2);
		this.general = map.get(3);
		this.symptom = map.get(4);
		this.reason = map.get(5);
		this.diagnosis = map.get(6);
		this.treatment = map.get(7);
		this.life = map.get(8);
		this.prevention = map.get(9);
		this.doctors = map.get(10);
		this.hospitals = map.get(11);
	}

	/**
	 * 设置：疾病名称
	 */
	public void setDisease(String disease) {
		this.disease = disease;
	}
	/**
	 * 获取：疾病名称
	 */
	public String getDisease() {
		return disease;
	}
	/**
	 * 设置：科室
	 */
	public void setField(String field) {
		this.field = field;
	}
	/**
	 * 获取：科室
	 */
	public String getField() {
		return field;
	}
	/**
	 * 设置：简介
	 */
	public void setGeneral(String general) {
		this.general = general;
	}
	/**
	 * 获取：简介
	 */
	public String getGeneral() {
		return general;
	}
	/**
	 * 设置：症状
	 */
	public void setSymptom(String symptom) {
		this.symptom = symptom;
	}
	/**
	 * 获取：症状
	 */
	public String getSymptom() {
		return symptom;
	}
	/**
	 * 设置：病因
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}
	/**
	 * 获取：病因
	 */
	public String getReason() {
		return reason;
	}
	/**
	 * 设置：诊断
	 */
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
	/**
	 * 获取：诊断
	 */
	public String getDiagnosis() {
		return diagnosis;
	}
	/**
	 * 设置：治疗
	 */
	public void setTreatment(String treatment) {
		this.treatment = treatment;
	}
	/**
	 * 获取：治疗
	 */
	public String getTreatment() {
		return treatment;
	}
	/**
	 * 设置：生活建议
	 */
	public void setLife(String life) {
		this.life = life;
	}
	/**
	 * 获取：生活建议
	 */
	public String getLife() {
		return life;
	}
	/**
	 * 设置：预防
	 */
	public void setPrevention(String prevention) {
		this.prevention = prevention;
	}
	/**
	 * 获取：预防
	 */
	public String getPrevention() {
		return prevention;
	}
	/**
	 * 设置：相关医生
	 */
	public void setDoctors(String doctors) {
		this.doctors = doctors;
	}
	/**
	 * 获取：相关医生
	 */
	public String getDoctors() {
		return doctors;
	}
	/**
	 * 设置：相关医院
	 */
	public void setHospitals(String hospitals) {
		this.hospitals = hospitals;
	}
	/**
	 * 获取：相关医院
	 */
	public String getHospitals() {
		return hospitals;
	}
}
