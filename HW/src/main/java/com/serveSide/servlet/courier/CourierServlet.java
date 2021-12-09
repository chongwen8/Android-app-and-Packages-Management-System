package com.serveSide.servlet.courier;

import com.serveSide.pojo.Courier;
import com.serveSide.service.courier.CourierService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CourierServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        System.out.println("CourierServlet" + method);
        if ("query".equals(method)){
            query(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    public void query(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CourierService courierService = new CourierService();
        List<Courier> couriers = courierService.getCouriers();

        req.setAttribute("courierList", couriers);

        req.getRequestDispatcher("/jsp/couriers.jsp").forward(req, resp);
    }
}
