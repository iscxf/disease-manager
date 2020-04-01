package com.app.manager.domain;

public class Person {

    private String user;

    private String title;

    private String desc;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Person{" +
                "user='" + user + '\'' +
                ", title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
