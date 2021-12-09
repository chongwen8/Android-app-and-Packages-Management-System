<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head lang="en">
    <meta charset="UTF-8">
    <title>快递信息管理系统</title>
    <link rel="stylesheet" href="../css/public.css">
    <link rel="stylesheet" href="../css/pwdModify.css">
    <script src="../js/public.js"></script>
</head>

<body>
<div class="left">
    <h2>功能栏</h2>
    <nav>
        <ul class="list">
            <li class="func">
                <a href="${pageContext.request.contextPath}/jsp/manager.do?method=query">账户管理</a>
            </li>
            <li class="func active">
                <a href="#">修改密码</a>
            </li>
            <li class="func">
                <a href="${pageContext.request.contextPath}/jsp/couriers.do?method=query">快递员信息管理</a>
            </li>
            <li class="func">
                <a href="${pageContext.request.contextPath}/jsp/customers.jsp?method=query">用户信息管理</a>
            </li>
            <li class="func">
                <a href="${pageContext.request.contextPath}/jsp/packages.do?method=query">包裹信息管理</a>
            </li>
        </ul>
    </nav>
</div>
<div id="search">
    <form method="get" action="${pageContext.request.contextPath}/jsp/packages.do">
        <input type="hidden" name="method" value="search">
        <span id="s_kw_wrap" class="bg s_ipt_wr quickdelete-wrap">
            <span class="soutu-btn"></span>
            <input type="text" name="context" id="kw" maxlength="100" autocomplete="off">
            <a href="javascript:;" id="quickdelete" title="清空" class="quickdelete"
            style="top: 0px; right: 0px; display: none;"></a>
        <span class="soutu-hover-tip" style="display: none;">按图片搜索</span>
        </span>
        <span class="btn_wr s_btn_wr bg" id="s_btn_wr">
            <input type="submit" value="检索">
        </span>
    </form>
</div>


<section>

    <table>
        <%--        表头--%>
        <tr>
            <th>收货人</th>
            <th>订单号</th>
            <th>负责人</th>
            <th>订单时间</th>
            <th>负责人电话</th>
        </tr>

        <c:forEach var="apackage" items="${packageList}">
            <tr>
                <td>${apackage.customerName}</td>
                <td>${apackage.orderNo}</td>
                <td>${apackage.courierName}</td>
                <td>${apackage.time}</td>
                <td>${apackage.courierPhoneNum}</td>
            </tr>

        </c:forEach>
    </table>
</section>


</body>

</html>
