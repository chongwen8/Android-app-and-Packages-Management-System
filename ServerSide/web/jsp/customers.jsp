<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head lang="en">
    <meta charset="UTF-8">
    <title>快递信息管理系统</title>
    <link rel="stylesheet" href="../css/public.css">
    <link rel="stylesheet" href="../layui/css/layui.css">
    <style type="text/css">
        .layui-layout-admin .layui-header {
            background-color: #2aade3;
        }

        .layui-btn {
            background-color: #2aade3;
        }
    </style>
</head>

<body>

<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div style="color: #ffffff;" class="layui-logo">快递信息保护系统</div>
    </div>
    <div>
        <!-- 内容主体区域 -->
        <div style="padding: 15px;">

            <!-- 你的HTML代码 -->
            <button class="layui-btn" style="margin: 10px"
                    onclick="javascript :window.location.href='${pageContext.request.contextPath}/jsp/couriers.do?method=query'">
                <i class="layui-icon">&#xe65c;</i> 返回
            </button>
        </div>
    </div>
</div>

<section>
    <p style="color: red">${info}</p>
    <table>
        <tr>
            <th>编号</th>
            <th>姓名</th>
            <th>电话号码</th>
            <th>地址</th>
        </tr>
        <% int i = 0;%>
        <c:forEach var="customer" items="${customerList}">
            <%
                i++;
                request.setAttribute("i", i);
            %>
            <tr>
                <td>${customer.ID}</td>
                <td>${customer.name}</td>
                <td>${customer.phoneNum}</td>
                <td>${customer.address}</td>
                <td>
                    <a class="layui-btn layui-btn-danger layui-btn-xs" href="${pageContext.request.contextPath}/jsp/customers.do?method=distribute&courier=${param.courier}&customer=${i}">分配</a>
                    <a class="layui-btn layui-btn-danger layui-btn-xs" href="${pageContext.request.contextPath}/jsp/customers.do?method=delete&courier=${param.courier}&customer=${i}">取消分配</a>
                </td>
            </tr>

        </c:forEach>
    </table>
</section>


</body>

</html>
