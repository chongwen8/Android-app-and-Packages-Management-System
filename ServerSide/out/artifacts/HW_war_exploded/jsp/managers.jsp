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
        <div style="color: #ffffff" class="layui-logo">
            快递信息保护系统
        </div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item">
                <a style="color: black"href="${pageContext.request.contextPath}/jsp/manager.do?method=query">账户管理</a>
            </li>
            <li class="layui-nav-item">
                <a style="color: #ffffff" href="${pageContext.request.contextPath}/jsp/couriers.do?method=query">快递员信息管理</a>

            </li>
<%--            <li class="layui-nav-item" id="item4">--%>
<%--                <a style="color: #ffffff" href="${pageContext.request.contextPath}/jsp/customers.do?method=query">用户信息管理</a>--%>
<%--            </li>--%>
            <li class="layui-nav-item" id="item5">
                <a style="color: #ffffff" href="${pageContext.request.contextPath}/jsp/packages.do?method=query">包裹信息管理</a>
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
    <div class="search" style="text-align: center">
        <form method="get" action="${pageContext.request.contextPath}/jsp/manager.do">
            <input type="hidden" name="method" value="query">
            <span>权限等级：</span>
            <select name="selectedPow">
                <option value="0">不限</option>
                <c:if test="${roleList != null}">
                    <c:forEach var="role" items="${roleList}">
                        <option
                                <c:if test="${role.power == selectedPow}">selected="selected"</c:if>
                                value="${role.power}">
                                ${role.roleName}
                        </option>
                    </c:forEach>
                </c:if>
            </select>
            <input type="submit" value="查询">
        </form>
    </div>

    <table>
        <%--表头--%>
        <tr>
            <th>账号</th>
            <th>电话号码</th>
            <th>权限等级</th>
            <th>上次登录时间</th>
            <th>创建时间</th>
        </tr>

        <c:forEach var="manager" items="${managerList}">
            <tr>
                <td>${manager.account}</td>
                <td>${manager.phoneNum}</td>
                <td>${manager.power}</td>
                <td>${manager.lastLoginTime}</td>
                <td>${manager.creationDate}</td>
            </tr>

        </c:forEach>
    </table>
</section>

<script src="../js/jquery.min.js"></script>
<script src="../layui/layui.js"></script>
</body>

</html>
