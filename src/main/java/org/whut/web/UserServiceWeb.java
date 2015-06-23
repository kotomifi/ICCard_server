package org.whut.web;

import org.apache.ibatis.session.SqlSession;
import org.whut.entity.User;
import org.whut.mapper.UserMapper;
import org.whut.util.MD5Encoder;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by baisu on 15-5-12.
 */
@Path("user")
public class UserServiceWeb {

    @GET
    @Path("/allUser")
    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    public List<User> allUser() {
        SqlSession sqlSession = BaseServiceWeb.getSessionFactory().openSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = userMapper.getUsers();
        sqlSession.close();
        return users;
    }

    @GET
    @Path("/findById/{id}")
    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    public User findById(@PathParam("id") long id) {
        SqlSession sqlSession = BaseServiceWeb.getSessionFactory().openSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.findById(id);
        sqlSession.close();
        return user;
    }

    @GET
    @Path("/findByName/{name}")
    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    public List<User> findByName(@PathParam("name") String name) {
        SqlSession sqlSession = BaseServiceWeb.getSessionFactory().openSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = userMapper.findByName(name);
        sqlSession.close();
        return users;
    }

    @POST
    @Path("/add")
    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public String add(@FormParam("userName") String userName,
                    @FormParam("password") String password,
                    @FormParam("workId") String workId,
                    @FormParam("sex") String sex,
                    @FormParam("phoneNum") String phoneNum,
                    @FormParam("email") String email,
                    @FormParam("name") String name) {


        SqlSession sqlSession = BaseServiceWeb.getSessionFactory().openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        if (userName == null || userName.trim().equals("") ||
            password == null || password.trim().equals("") ||
            workId == null || workId.trim().equals("") ||
            sex == null || sex.trim().equals("") ||
                phoneNum == null || phoneNum.trim().equals("") ||
                name == null || name.trim().equals("")) {
            return "参数不能为空!";
        }

        long id;
        try {
            id = userMapper.getIdByName(userName);
        } catch (Exception ex) {
            id = 0;
        }
        if (0 == id) {
            User user = new User();
            user.setUserName(userName.trim());
            user.setPassword(MD5Encoder.GetMD5Code(password.trim()));
            user.setWorkId(workId.trim());
            user.setSex(sex.trim());
            user.setPhoneNum(phoneNum.trim());
            user.setName(name.trim());
            if (email != null || !email.trim().equals("")) {
                user.setEmail(email.trim());
            }
            userMapper.add(user);
            sqlSession.commit();
            sqlSession.close();
            return "添加成功";
        } else {
            return "用户名已存在";
        }
    }

}
