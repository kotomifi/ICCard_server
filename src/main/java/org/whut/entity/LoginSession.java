package org.whut.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by baisu on 15-5-15.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class LoginSession {
    private long id;
    private String JSESSIONID;
    private String userName;
    private String ticket;

    public long getId() {
        return id;
    }

    public String getJSESSIONID() {
        return JSESSIONID;
    }

    public String getUserName() {
        return userName;
    }

    public String getTicket() {
        return ticket;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setJSESSIONID(String JSESSIONID) {
        this.JSESSIONID = JSESSIONID;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }
}
