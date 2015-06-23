package org.whut.web;

import org.apache.ibatis.session.SqlSession;
import org.whut.entity.LoginSession;
import org.whut.entity.Repair;
import org.whut.mapper.LoginSessionMapper;
import org.whut.mapper.RepairMapper;

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
@Path("repair")
public class RepairServiceWeb {

    @GET
    @Path("/findById/{id}")
    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    public Repair findById(@PathParam("id") long id) {
        SqlSession sqlSession = BaseServiceWeb.getSessionFactory().openSession();

        RepairMapper repairMapper = sqlSession.getMapper(RepairMapper.class);
        Repair repair = repairMapper.findById(id);
        sqlSession.close();
        return repair;
    }

    @GET
    @Path("/allRepair")
    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    public List<Repair> allRepair() {
        SqlSession sqlSession = BaseServiceWeb.getSessionFactory().openSession();

        RepairMapper repairMapper = sqlSession.getMapper(RepairMapper.class);
        List<Repair> repairs = repairMapper.getRepairs();
        sqlSession.close();
        return repairs;
    }

    @GET
    @Path("/getRepairTasks")
    @Produces(MediaType.APPLICATION_XML)
    public List<Repair> getRepariTask(
            @Context HttpServletRequest request,
            @Context HttpServletResponse response) {

        SqlSession sqlSession = BaseServiceWeb.getSessionFactory().openSession();
        // 检查是否登录
        String JSESSIONID = request.getSession().getId();
        LoginSessionMapper loginSessionMapper = sqlSession.getMapper(LoginSessionMapper.class);
        LoginSession loginSession = loginSessionMapper.findBySessionId(JSESSIONID);
        String userName = loginSession.getUserName();
        if (loginSession != null) {
            RepairMapper repairMapper = sqlSession.getMapper(RepairMapper.class);
            List<Repair> tasks = repairMapper.findByUser(userName, false);

            return tasks;
        }
        return null;
    }

    @POST
    @Path("/postRepairTasks")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes({"application/json","application/xml","application/x-www-form-urlencoded"})
    public String postRepairTask(
            @FormParam("id") String id,
            @FormParam("type") String type,
            @FormParam("description") String description,
            @FormParam("oldBarCode") String oldBarCode,
            @FormParam("oldIndication") String oldIndication,
            @FormParam("newBarCode") String newBarCode,
            @FormParam("newIndication") String newIndication,
            @Context HttpServletRequest request,
            @Context HttpServletResponse response) {

        SqlSession sqlSession = BaseServiceWeb.getSessionFactory().openSession();
        // 获取SessionId
        String JSESSIONID = request.getSession().getId();
        System.out.println("ÈÎÎñ£º " +JSESSIONID);
        LoginSessionMapper loginSessionMapper = sqlSession.getMapper(LoginSessionMapper.class);
        LoginSession loginSession = loginSessionMapper.findBySessionId(JSESSIONID);
        String userName = loginSession.getUserName();
        if (loginSession == null)
            return "NOT LOGIN";

        // 更新维修任务
        RepairMapper repairMapper = sqlSession.getMapper(RepairMapper.class);
        Repair re = repairMapper.findById(Long.parseLong(id));
        re.setIsComplete(1);
        re.setUploadFlag(1);
        Date d = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String time = sd.format(d);
        re.setCompleteDate(time);
        if (description != null) {
            re.setDescription(description);
        }
        re.setType(type);
        if (oldIndication != null && !(oldIndication.equals(""))
                && newIndication != null && !(newIndication.equals(""))
                && oldBarCode != null && !(oldBarCode.equals(""))
                && newBarCode != null && !(newBarCode.equals(""))) {

            re.setOldBarCode(oldBarCode);
            re.setOldIndication(Integer.parseInt(oldIndication));
            re.setNewBarCode(newBarCode);
            re.setNewIndication(Integer.parseInt(newIndication));
            re.setIsUpdate(1);
        }
        repairMapper.update(re);
        sqlSession.commit();
        sqlSession.close();

        return "SUCCESS";
    }
}
