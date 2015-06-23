package org.whut.service;

import org.whut.entity.User;
import org.whut.mapper.UserMapper;

import java.util.List;

/**
 * Created by baisu on 15-5-12.
 */
public class UserService {
    private UserMapper mapper;

    public User findById(long id) {
        return mapper.findById(id);
    }

    public List<User> findByName(String name) {
        return mapper.findByName(name);
    }

    public void add(User user) {
        mapper.add(user);
    }

    public List<User> getUsers() {
        return mapper.getUsers();
    }

    public void delete(User user) {
        mapper.delete(user);
    }

    public void update(User user) {
        mapper.update(user);
    }

    public long getIdByName(String userName) {
        return mapper.getIdByName(userName);
    }

    public User findByUserName(String userName) {
        return mapper.findByUserName(userName);
    }
}
