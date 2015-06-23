package org.whut.mapper;

import org.whut.entity.User;

import java.util.List;

/**
 * Created by baisu on 15-5-12.
 */
public interface UserMapper {

    public User findById(long id);

    public List<User> findByName(String name);

    public void add(User user);

    public List<User> getUsers();

    public void delete(User user);

    public void update(User user);

    public long getIdByName(String userName);

    public User findByUserName(String userName);
}
