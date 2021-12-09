package com.serveSide.filter;

import com.serveSide.pojo.Manager;
import com.serveSide.util.Constant;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AccessControlFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        Manager manager = (Manager) request.getSession().getAttribute(Constant.USER_SESSION);
        if (manager == null){
            response.sendRedirect(request.getContextPath()+"/error.jsp");
        }else {
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }

}
