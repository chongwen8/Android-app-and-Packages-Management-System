package com.serveSide.servlet.courier;

import com.serveSide.pojo.Courier;
import com.serveSide.service.courier.CourierService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println("收到的账号为:" + username);
        System.out.println("收到的密码为：" + password);

        CourierService courierService = new CourierService();
        Courier courier = courierService.login(username, password);

        PrintWriter writer = resp.getWriter();
        // 登入失败
        if (courier == null){
            writer.write("failed");
        }
        // 登入成功
        else {
            // 向客户端传输 快递员的姓名和电话号码
            String message = courier.getID() + "&" + courier.getName() + "&" + courier.getPhoneNum();
            writer.write(message);
        }
        writer.close();
    }
}
