package com.serveSide.servlet.customer;

import com.mysql.cj.x.protobuf.MysqlxCrud;
import com.serveSide.dao.packages.PackageDaoIml;
import com.serveSide.pojo.Customer;
import com.serveSide.service.customer.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CustomerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        System.out.println(method);
        if ("query".equals(method)){
            query(req, resp);
        }else if ("delete".equals(method)){
            System.out.println(req.getParameter("courier"));
            undistributed(req, resp);
        }else if ("distribute".equals(method)){
            System.out.println(req.getParameter("courier"));
            distribute(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    public void query(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CustomerService customerService = new CustomerService();
        List<Customer> customers = customerService.getCustomers();
        req.setAttribute("customerList", customers);

        if ("success".equals(req.getParameter("res"))){
            req.setAttribute("info", "分配成功");
        }else if ("deleted".equals(req.getParameter("res"))){
            req.setAttribute("info", "取消分配成功");
        }

        req.getRequestDispatcher("/jsp/customers.jsp").forward(req, resp);
    }

    public void distribute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String courier = req.getParameter("courier");
        String customer = req.getParameter("customer");

        PackageDaoIml packageDaoIml = new PackageDaoIml();
        packageDaoIml.addPackage(Integer.parseInt(courier), Integer.parseInt(customer));

        resp.sendRedirect(req.getContextPath() + "/jsp/customers.do?method=query&res=success");
    }
    public void undistributed(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String courier = req.getParameter("courier");
        String customer = req.getParameter("customer");

        PackageDaoIml packageDaoIml = new PackageDaoIml();
        packageDaoIml.delPackage(Integer.parseInt(courier), Integer.parseInt(customer));

        resp.sendRedirect(req.getContextPath() + "/jsp/customers.do?method=query&res=deleted");
    }

}
