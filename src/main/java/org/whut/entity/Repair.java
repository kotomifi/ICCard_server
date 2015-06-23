package org.whut.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by baisu on 15-5-12.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Repair {

    private long id;
    private String type;
    private String description;
    private String userName;
    private String oldBarCode;
    private int oldIndication;
    private String newBarCode;
    private int newIndication;
    private String postDate;
    private String completeDate;
    private String address;
    private int isUpdate;
    private int uploadFlag;
    private int isComplete;

    public long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public String getUserName() {
        return userName;
    }

    public String getOldBarCode() {
        return oldBarCode;
    }

    public int getOldIndication() {
        return oldIndication;
    }

    public String getNewBarCode() {
        return newBarCode;
    }

    public int getNewIndication() {
        return newIndication;
    }

    public String getPostDate() {
        return postDate;
    }

    public String getCompleteDate() {
        return completeDate;
    }

    public String getAddress() {
        return address;
    }

    public int getIsUpdate() {
        return isUpdate;
    }

    public int getUploadFlag() {
        return uploadFlag;
    }

    public int getIsComplete() {
        return isComplete;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setOldBarCode(String oldBarCode) {
        this.oldBarCode = oldBarCode;
    }

    public void setOldIndication(int oldIndication) {
        this.oldIndication = oldIndication;
    }

    public void setNewBarCode(String newBarCode) {
        this.newBarCode = newBarCode;
    }

    public void setNewIndication(int newIndication) {
        this.newIndication = newIndication;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public void setCompleteDate(String completeDate) {
        this.completeDate = completeDate;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setIsUpdate(int isUpdate) {
        this.isUpdate = isUpdate;
    }

    public void setUploadFlag(int uploadFlag) {
        this.uploadFlag = uploadFlag;
    }

    public void setIsComplete(int isComplete) {
        this.isComplete = isComplete;
    }
}
