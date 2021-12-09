package com.serveSide.servlet.manager;

import com.serveSide.pojo.Manager;
import com.serveSide.pojo.Role;
import com.serveSide.service.manager.ManagerService;
import com.serveSide.service.role.RoleService;
import com.mysql.cj.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ManagerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        System.out.println(method);
        if ("query".equals(method)){
            query(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    public void query(HttpServletRequest req, HttpServletResponse resp){
        String selectedPow = req.getParameter("selectedPow");
        int power = 0;
        if (!StringUtils.isNullOrEmpty(selectedPow)){
            power = Integer.parseInt(selectedPow);
        }
        ManagerService managerService = new ManagerService();
        List<Manager> managerList = managerService.getManagers(power);
        RoleService roleService = new RoleService();
        List<Role> roleList = roleService.getRoleList();

        req.setAttribute("managerList", managerList);
        req.setAttribute("roleList", roleList);

        try {
            req.getRequestDispatcher("/jsp/managers.jsp").forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
