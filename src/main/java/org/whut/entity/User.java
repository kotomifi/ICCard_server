package org.whut.entity;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by baisu on 15-5-12.
 */
@XmlRootElement
public class User {

    private long id;
    private String userName;
    private String password;
    private String workId;
    private String sex;
    private String name;
    private String phoneNum;
    private String email;

    public User() {
    }

    public User(long id, String userName, String password, String workId, String sex, String name, String phoneNum, String email) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.workId = workId;
        this.sex = sex;
        this.name = name;
        this.phoneNum = phoneNum;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getWorkId() {
        return workId;
    }

    public String getSex() {
        return sex;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setWorkId(String workId) {
        this.workId = workId;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
