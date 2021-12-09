package com.serveSide.servlet.packages;

import com.serveSide.pojo.Package;
import com.serveSide.service.packages.PackageService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PackageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        System.out.println("PackageServlet" + method);
        if ("query".equals(method)) {
            query(req, resp);
        } else if ("createView".equals(method)) {
            createView(req, resp);
        } else if ("createQrCode".equals(method)) {
            createQrCode(req, resp);
        } else if ("search".equals(method)) {
            search(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    public void query(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        PackageService packageService = new PackageService();
        List<Package> packages = packageService.getPackages(null);

        req.setAttribute("packageList", packages);

        req.getRequestDispatcher("/jsp/packages.jsp").forward(req, resp);


    }

    public void createView(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String orderNo = req.getParameter("OrderNo");
        String time = req.getParameter("time");
        String courierID = req.getParameter("courierID");
        String courierName = req.getParameter("courierName");
        String courierPhoneNum = req.getParameter("courierPhoneNum");
        String customerName = req.getParameter("customerName");
        String customerPhoneNum = req.getParameter("customerPhoneNum");
        String address = req.getParameter("address");

        List<String> options = new ArrayList<String>();
        if (orderNo != null) {
            options.add(orderNo);
        }
        if (time != null) {
            options.add(time);
        }
        if (courierID != null) {
            options.add(courierID);
        }
        if (courierName != null) {
            options.add(courierName);
        }
        if (courierPhoneNum != null) {
            options.add(courierPhoneNum);
        }
        if (customerName != null) {
            options.add(customerName);
        }
        if (customerPhoneNum != null) {
            options.add(customerPhoneNum);
        }
        if (address != null) {
            options.add(address);
        }
        PackageService packageService = new PackageService();
        if (packageService.createView(options)) {
            req.setAttribute("info", "视图生成成功，请重构二维码");
        } else {
            req.setAttribute("info", "视图生成失败，请查看原因");
        }
        req.getRequestDispatcher("/jsp/packages.do?method=query").forward(req, resp);
    }

    public void createQrCode(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PackageService packageService = new PackageService();
        boolean flag = packageService.createQrCode();
        System.out.println(flag);
        if (flag) {
            req.setAttribute("info", "本次二维码已成功构建");
        } else {
            req.setAttribute("info", "二维码构建失败，请查看原因");
        }
        req.getRequestDispatcher("/jsp/packages.do?method=query").forward(req, resp);
    }

    public void search(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String context = req.getParameter("context");
        System.out.println(context);

        PackageService packageService = new PackageService();
        List<Package> packages = packageService.getPackages(context);

        req.setAttribute("packageList", packages);

        req.getRequestDispatcher("/jsp/pwdModify.jsp").forward(req, resp);
    }


}
