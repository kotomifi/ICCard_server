package org.whut.service;

import org.whut.entity.LoginSession;
import org.whut.mapper.LoginSessionMapper;

/**
 * Created by baisu on 15-5-15.
 */
public class LoginSessionService {
    private LoginSessionMapper mapper;

    public LoginSession findByTicket(String ticket) {
        return mapper.findByTicket(ticket);
    }

    public LoginSession findBySessionId(String loginSession) {
        return mapper.findBySessionId(loginSession);
    }

    public void add(LoginSession loginSession) {
        mapper.add(loginSession);
    }

    public void update(LoginSession loginSession) {
        mapper.update(loginSession);
    }

    public void delete(LoginSession loginSession) {
        mapper.delete(loginSession);
    }
}
