package com.app.manager.domain.vo;


public class PatientInfoVo {

    //名称
    private String name;
    //身份证号码
    private String identity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    @Override
    public String toString() {
        return "PatientInfoVo{" +
                "name='" + name + '\'' +
                ", identity='" + identity + '\'' +
                '}';
    }
}
