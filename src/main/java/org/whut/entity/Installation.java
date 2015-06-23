package org.whut.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by baisu on 15-5-12.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Installation {

    private long id;
    private String address;
    private String userName;
    private String postDate;

    private String completeDate;
    private int isComplete;
    private int uploadFlag;
    private String barCode;
    private int indication;

    public long getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public String getUserName() {
        return userName;
    }

    public String getPostDate() {
        return postDate;
    }

    public String getCompleteDate() {
        return completeDate;
    }

    public int getIsComplete() {
        return isComplete;
    }

    public int getUploadFlag() {
        return uploadFlag;
    }

    public String getBarCode() {
        return barCode;
    }

    public int getIndication() {
        return indication;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public void setCompleteDate(String completeDate) {
        this.completeDate = completeDate;
    }

    public void setIsComplete(int isComplete) {
        this.isComplete = isComplete;
    }

    public void setUploadFlag(int uploadFlag) {
        this.uploadFlag = uploadFlag;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public void setIndication(int indication) {
        this.indication = indication;
    }
}
