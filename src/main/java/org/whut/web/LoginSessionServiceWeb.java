package org.whut.web;

import org.apache.ibatis.session.SqlSession;
import org.whut.entity.Account;
import org.whut.entity.LoginSession;
import org.whut.entity.User;
import org.whut.mapper.LoginSessionMapper;
import org.whut.mapper.UserMapper;
import org.whut.util.MD5Encoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by baisu on 15-5-15.
 */
@Path("account")
public class LoginSessionServiceWeb {

    @POST
    @Path("/getTicket")
    @Produces(MediaType.TEXT_PLAIN +";charset=UTF-8")
    @Consumes({"application/json","application/xml","application/x-www-form-urlencoded"})
    public String login(
            @FormParam("username") String username,
            @FormParam("password") String password,
            @Context HttpServletRequest request,
            @Context HttpServletResponse response) {

        if (username == null || username.trim().equals("")|| password==null|| password.trim().equals("")) {
            return null;
        }

        String mTicket = "";

        SqlSession sqlSession = BaseServiceWeb.getSessionFactory().openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.findByUserName(username);
        String passwd = MD5Encoder.GetMD5Code(password);
        if (passwd.equals(user.getPassword())) {

            Date now = new Date();
            SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = formatter.format(now);
            mTicket = MD5Encoder.GetMD5Code(username + time);

            String encodeStr = username + time;
            mTicket = new sun.misc.BASE64Encoder().encode(encodeStr.getBytes());

            //从HTTP Request中活取sessionId
            String JSESSIONID = request.getSession().getId();

            LoginSession newLoginSession = new LoginSession();
            newLoginSession.setJSESSIONID(JSESSIONID);
            newLoginSession.setUserName(username);
            newLoginSession.setTicket(mTicket);

            LoginSessionMapper loginSessionMapper = sqlSession.getMapper(LoginSessionMapper.class);
            LoginSession loginSession = loginSessionMapper.findBySessionId(JSESSIONID);
            if (loginSession != null) {
                loginSessionMapper.update(newLoginSession);
            } else {
                loginSessionMapper.add(newLoginSession);
            }

            sqlSession.commit();
            sqlSession.close();
        }
        return mTicket;
    }


    @GET
    @Path("getSessionId")
    @Produces(MediaType.TEXT_PLAIN +";charset=UTF-8")
    @Consumes({"application/json","application/xml","application/x-www-form-urlencoded"})
    public String getSessionId(
            @QueryParam("ticket") String ticket,
            @Context HttpServletRequest request,
            @Context HttpServletResponse response) {
        System.out.println("init ticket:" + ticket);
        if(request == null || ticket.equals("") || ticket == null){
            return null;
        }

        // 获取SessionID
        String JSESSIONID = request.getSession().getId();
        SqlSession sqlSession = BaseServiceWeb.getSessionFactory().openSession();
        LoginSessionMapper loginSessionMapper = sqlSession.getMapper(LoginSessionMapper.class);
        LoginSession loginSession = loginSessionMapper.findByTicket(ticket);
        if (loginSession == null || !(loginSession.getJSESSIONID().equals(JSESSIONID))) {
            return null;
        }

        Cookie cookid = new Cookie("JSESSIONID", JSESSIONID);
        response.addCookie(cookid);
        sqlSession.close();
        return "SUCCESS";
    }

    @GET
    @Path("/getCurrentUser")
    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    public User getCurrentUser(
            @Context HttpServletRequest request,
            @Context HttpServletResponse response) {

        String JSESSIONID = request.getSession().getId();
        SqlSession sqlSession = BaseServiceWeb.getSessionFactory().openSession();

        LoginSessionMapper loginSessionMapper = sqlSession.getMapper(LoginSessionMapper.class);
        LoginSession loginSession = loginSessionMapper.findBySessionId(JSESSIONID);

        if (loginSession == null) {
            User u = new User();
            return u;
        }
        String username = loginSession.getUserName();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.findByUserName(username);
        return user;
    }

    @POST
    @Path("/postJSON")
    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Consumes({"application/json","application/xml","application/x-www-form-urlencoded"})
    public Response postJSON(@HeaderParam("userName") String userName,
                             @HeaderParam("password") String password) {

        System.out.println("===================");

        System.out.println(userName);
        System.out.println(password);
        System.out.println("===================");

        String output = password.toString();
        return Response.status(200).entity(output).build();
    }

    @POST
    @Path("/postJSON2")
    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Consumes({"application/json","application/xml","application/x-www-form-urlencoded"})
    public Response postJSON2(Account account) {

        System.out.println("===================");
        System.out.println(account.getUserName());
        System.out.println(account.getPassword());
        System.out.println("===================");

        String output = account.toString();
        return Response.status(200).entity(output).build();
    }

}
