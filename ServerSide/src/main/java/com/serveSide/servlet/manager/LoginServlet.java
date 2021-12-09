package com.serveSide.servlet.manager;

import com.serveSide.pojo.Manager;
import com.serveSide.service.manager.ManagerService;
import com.serveSide.util.Constant;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String message = req.getParameter("message");
        System.out.println("本次收到的message是" + message);

        Manager manager = null;
        if ("account".equals(message)) {
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            password = DigestUtils.md5Hex(DigestUtils.md5Hex(password) + Constant.SALT);
            ManagerService managerService = new ManagerService();
            manager = managerService.login(username, password, null);
            if(manager !=null){
                req.getSession().setAttribute(Constant.USER_SESSION, manager);
                resp.sendRedirect("jsp/managers.jsp");
            }
        }
    }

}
