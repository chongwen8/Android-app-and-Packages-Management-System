package com.serveSide.servlet.courier;

import com.serveSide.service.courier.CourierService;
import com.serveSide.util.Constant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        String name = req.getParameter("name");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String phoneNum = req.getParameter("phoneNum");

        System.out.println("name:" + name + " username" + username + " password" + password + " phoneNum" + phoneNum);

        CourierService courierService = new CourierService();
        int code = courierService.register(name, username, password, phoneNum);

        PrintWriter writer = resp.getWriter();

        if (code == Constant.SUCCESS_COURIER){
            // 注册成功
            writer.write("success");
        }else if (code == Constant.ERROR_COURIER_UNKNOWN){
            // 注册失败
            writer.write("error");
        }else if (code == Constant.ERROR_COURIER_REPEAT){
            // 用户名重复
            writer.write("repeat");
        }else if (code == Constant.ERROR_COURIER_EMPTY){
            // 注册信息不能为空
            writer.write("empty");
        }
        writer.close();
    }
}
