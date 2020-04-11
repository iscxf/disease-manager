package com.app.manager.domain.model;

import java.util.Date;

/**
 * @author chenxf
 * Created on 2020/3/25
 */
public class PredictParam {
    //性别
    private String sex;
    //出生日期
    private Date birth;
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
    //症状
    private String symptom;

    public PredictParam() {
    }

    public PredictParam(String sex, Date birth, Double height, Double weight, Double systolicPressure, Double diastolicPressure, Double fastingBloodGlucose, Double postprandialBloodGlucose,
                        Double totalCholesterol, Double triglyceride, String whetherDiabetes, String familialInheritance, String thickSebum, String symptom) {
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
        this.symptom = symptom;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getSystolicPressure() {
        return systolicPressure;
    }

    public void setSystolicPressure(Double systolicPressure) {
        this.systolicPressure = systolicPressure;
    }

    public Double getDiastolicPressure() {
        return diastolicPressure;
    }

    public void setDiastolicPressure(Double diastolicPressure) {
        this.diastolicPressure = diastolicPressure;
    }

    public Double getFastingBloodGlucose() {
        return fastingBloodGlucose;
    }

    public void setFastingBloodGlucose(Double fastingBloodGlucose) {
        this.fastingBloodGlucose = fastingBloodGlucose;
    }

    public Double getPostprandialBloodGlucose() {
        return postprandialBloodGlucose;
    }

    public void setPostprandialBloodGlucose(Double postprandialBloodGlucose) {
        this.postprandialBloodGlucose = postprandialBloodGlucose;
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

    public String getWhetherDiabetes() {
        return whetherDiabetes;
    }

    public void setWhetherDiabetes(String whetherDiabetes) {
        this.whetherDiabetes = whetherDiabetes;
    }

    public String getFamilialInheritance() {
        return familialInheritance;
    }

    public void setFamilialInheritance(String familialInheritance) {
        this.familialInheritance = familialInheritance;
    }

    public String getThickSebum() {
        return thickSebum;
    }

    public void setThickSebum(String thickSebum) {
        this.thickSebum = thickSebum;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }
}
