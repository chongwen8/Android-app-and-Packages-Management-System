<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head lang="en">
    <meta charset="UTF-8">
    <title>快递信息管理系统</title>
    <link rel="stylesheet" href="../css/public.css">
    <link rel="stylesheet" href="../css/packages.css">
    <link rel="stylesheet" href="../layui/css/layui.css">
    <style type="text/css">
        .layui-layout-admin .layui-header {
            background-color: #2aade3;
        }

        .layui-btn {
            background-color: #2aade3;
        }
    </style>
    <script src="../js/public.js"></script>
</head>

<body>
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div style="color: #ffffff" class="layui-logo">
            快递信息保护系统
        </div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item">
                <a style="color: #ffffff"href="${pageContext.request.contextPath}/jsp/manager.do?method=query">账户管理</a>
            </li>
            <li class="layui-nav-item">
                <a style="color: #ffffff" href="${pageContext.request.contextPath}/jsp/couriers.do?method=query">快递员信息管理</a>

            </li>
<%--            <li class="layui-nav-item" id="item4">--%>
<%--                <a style="color: #ffffff" href="${pageContext.request.contextPath}/jsp/customers.do?method=query">用户信息管理</a>--%>
<%--            </li>--%>
            <li class="layui-nav-item" id="item5">
                <a style="color: black" href="${pageContext.request.contextPath}/jsp/packages.do?method=query">包裹信息管理</a>
            </li>
        </ul>

        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a id="uname">系统设置 </a>
                <dl class="layui-nav-child">
                    <dd>
                        <a href="../login.jsp">退出</a>
                    </dd>
                </dl>
            </li>
        </ul>
    </div>
</div>

<section>
    <div class="createView">
        <form class="view" method="get" action="${pageContext.request.contextPath}/jsp/packages.do">
            <input type="hidden" name="method" value="createView">

            <label for="orderNo">订单号
                <input type="checkbox" name="OrderNo" value="OrderNo" checked="checked" readonly="readonly" onclick="return false;" id="orderNo">
            </label>

            <label for="time">下单时间
                <input type="checkbox" name="time" value="time" id="time">
            </label>


            <label for="courierNo">负责人编号
                <input type="checkbox" name="courierID" value="couriers.courierID courierID" checked="checked" readonly="readonly" onclick="return false;" id="courierNo">
            </label>

            <label for="courierName">负责人
                <input type="checkbox" name="courierName" value="couriers.name courierName" id="courierName">
            </label>

            <label for="courierPhoneNum">负责人联系方式
                <input type="checkbox" name="courierPhoneNum" value="couriers.phoneNum courierPhoneNum" id="courierPhoneNum">
            </label>

            <label for="customerName">收货人
                <input type="checkbox" name="customerName" value="customers.name customerName" id="customerName">
            </label>

            <label for="customerPhoneNum">收货人电话
                <input type="checkbox" name="customerPhoneNum" value="customers.phoneNum customerPhoneNum" id="customerPhoneNum">
            </label>

            <label for="customerAddress">收货地址
                <input type="checkbox" name="address" value="address" id="customerAddress">
            </label>

            <input type="submit" value="生成视图">
        </form>

        <div class="info">${info}</div>

        <form class="QrCode">
            <input type="hidden" name="method" value="createQrCode">
            <input type="submit" value="生成二维码">
        </form>
    </div>

    <table>
        <%--        表头--%>
        <tr>
            <th>订单号</th>
            <th>下单时间</th>
            <th>负责人编号</th>
            <th>负责人</th>
            <th>负责人联系方式</th>
            <th>收货人</th>
            <th>收货人电话</th>
            <th>收货地址</th>
        </tr>

        <c:forEach var="apackage" items="${packageList}">
            <tr>
                <td>${apackage.orderNo}</td>
                <td>${apackage.time}</td>
                <td>${apackage.courierID}</td>
                <td>${apackage.courierName}</td>
                <td>${apackage.courierPhoneNum}</td>
                <td>${apackage.customerName}</td>
                <td>${apackage.customerPhoneNum}</td>
                <td>${apackage.customerAddress}</td>
            </tr>

        </c:forEach>
    </table>
</section>


</body>

</html>
