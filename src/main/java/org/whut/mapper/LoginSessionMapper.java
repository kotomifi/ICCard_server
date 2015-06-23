package org.whut.mapper;

import org.whut.entity.LoginSession;

/**
 * Created by baisu on 15-5-15.
 */
public interface LoginSessionMapper {
    public LoginSession findByTicket(String ticket);

    public LoginSession findBySessionId(String loginSession);

    public void add(LoginSession loginSession);

    public void update(LoginSession loginSession);

    public void delete(LoginSession loginSession);
}
