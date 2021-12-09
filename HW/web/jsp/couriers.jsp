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
<%--    <script>--%>
<%--        function getTabValue(index){--%>
<%--            let tableID = document.getElementById("tab");--%>
<%--            // 获得编号--%>
<%--            alert(tableID.rows[index].cells[0].innerHTML);--%>
<%--        }--%>
<%--    </script>--%>
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
                <a style="color: #ffffff" href="${pageContext.request.contextPath}/jsp/manager.do?method=query">账户管理</a>
            </li>
            <li class="layui-nav-item">
                <a style="color: black"
                   href="${pageContext.request.contextPath}/jsp/couriers.do?method=query">快递员信息管理</a>

            </li>
<%--            <li class="layui-nav-item" id="item4">--%>
<%--                <a style="color: #ffffff" href="${pageContext.request.contextPath}/jsp/customers.do?method=query">用户信息管理</a>--%>
<%--            </li>--%>
            <li class="layui-nav-item" id="item5">
                <a style="color: #ffffff"
                   href="${pageContext.request.contextPath}/jsp/packages.do?method=query">包裹信息管理</a>
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

    <div>
        <!-- 内容主体区域 -->
        <div style="padding: 15px;">
            <div>
                <button class="layui-btn">
                    添加快递员
                </button>
            </div>
        </div>
    </div>
</div>
<section>
    <table id="tab">
        <%--        表头--%>
        <tr>
            <th>编号</th>
            <th>姓名</th>
            <th>用户名</th>
            <th>电话号码</th>
            <th>管理</th>
        </tr>
            <% int i = 0;%>
        <c:forEach var="courier" items="${courierList}">
            <%
                i++;
                request.setAttribute("i", i);
            %>
            <tr>
                <td>${courier.ID}</td>
                <td>${courier.name}</td>
                <td>${courier.userName}</td>
                <td>${courier.phoneNum}</td>
                <td>
                    <a class="layui-btn layui-btn-xs" href="${pageContext.request.contextPath}/jsp/customers.do?method=query&courier=${i}">分配用户</a>
                    <a class="layui-btn layui-btn-xs">编辑</a>
                    <a class="layui-btn layui-btn-danger layui-btn-xs">删除</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</section>


</body>

</html>
