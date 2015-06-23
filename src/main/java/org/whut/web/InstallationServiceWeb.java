package org.whut.web;

import org.apache.ibatis.session.SqlSession;
import org.whut.entity.Installation;
import org.whut.entity.LoginSession;
import org.whut.mapper.InstallationMapper;
import org.whut.mapper.LoginSessionMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by baisu on 15-5-12.
 */
@Path("installation")
public class InstallationServiceWeb {

    @GET
    @Path("/findById/{id}")
    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    public Installation findById(@PathParam("id") long id) {
        SqlSession sqlSession = BaseServiceWeb.getSessionFactory().openSession();

        InstallationMapper installationMapper = sqlSession.getMapper(InstallationMapper.class);
        Installation installation = installationMapper.findById(id);
        sqlSession.close();
        return installation;
    }

    @GET
    @Path("/allInstallation")
    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    public List<Installation> allInstallation() {
        SqlSession sqlSession = BaseServiceWeb.getSessionFactory().openSession();

        InstallationMapper installationMapper = sqlSession.getMapper(InstallationMapper.class);
        List<Installation> installations = installationMapper.getInstallations();
        sqlSession.close();
        return installations;
    }

    @GET
    @Path("/getInstallationTasks")
    @Produces(MediaType.APPLICATION_XML)
    public List<Installation> getInstallationTask(
            @Context HttpServletRequest request,
            @Context HttpServletResponse response) {

        SqlSession sqlSession = BaseServiceWeb.getSessionFactory().openSession();
        // žùŸÝSessionID ²éÑ¯µÇÂœÓÃ»§Ãû
        String JSESSIONID = request.getSession().getId();
        System.out.println("ÈÎÎñ£º " +JSESSIONID);
        LoginSessionMapper loginSessionMapper = sqlSession.getMapper(LoginSessionMapper.class);
        LoginSession loginSession = loginSessionMapper.findBySessionId(JSESSIONID);
        String userName = loginSession.getUserName();
        if (loginSession != null) {
            InstallationMapper installationMapper = sqlSession.getMapper(InstallationMapper.class);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String t = sdf.format(new Date());
            List<Installation> tasks = installationMapper.findByUserName(userName, t, false);
            Installation is = tasks.get(0);

            System.out.println("-----" + is.getPostDate());

            return tasks;
        }

        return null;
    }

    @POST
    @Path("/postInstallationTasks")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes({"application/json","application/xml","application/x-www-form-urlencoded"})
    public String postInstallationTask(
            @FormParam("id") String id,
            @FormParam("barCode") String barCode,
            @FormParam("indication") String indication,
            @Context HttpServletRequest request,
            @Context HttpServletResponse response) {

        SqlSession sqlSession = BaseServiceWeb.getSessionFactory().openSession();
        // 活取SessionID
        String JSESSIONID = request.getSession().getId();

        LoginSessionMapper loginSessionMapper = sqlSession.getMapper(LoginSessionMapper.class);
        LoginSession loginSession = loginSessionMapper.findBySessionId(JSESSIONID);
        String userName = loginSession.getUserName();
        if (loginSession == null)
            return "NOT LOGIN";

        // 更新安装记录
        Installation installation = new Installation();
        installation.setUploadFlag(1);

        InstallationMapper installationMapper = sqlSession.getMapper(InstallationMapper.class);
        Installation st2 = installationMapper.findById(2);
        st2.setBarCode(barCode);
        st2.setIndication(Integer.parseInt(indication));
        st2.setUploadFlag(1);
        Date d = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String time = sd.format(d);
        st2.setCompleteDate(time);
        st2.setIsComplete(1);
        System.out.println(st2.toString());
        installationMapper.update(st2);
        sqlSession.commit();
        sqlSession.close();

        return "SUCCESS";
    }
}
