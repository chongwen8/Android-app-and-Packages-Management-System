<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
    <title>快递信息管理系统</title>
    <link rel="stylesheet" href="css/login.css">
    <script src="js/login.js"></script>
</head>

<body>
<input id="PageContext" type="hidden" value="${pageContext.request.contextPath}"/>

<div class="wrap">
    <ul class="list">
        <li class="item active"><img src="images/1.jpg" alt=""></li>
        <li class="item"><img src="images/2.jpg" alt=""></li>
        <li class="item"><img src="images/3.jpg" alt=""></li>
        <li class="item"><img src="images/4.jpg" alt=""></li>
        <li class="item"><img src="images/5.png" alt=""></li>
    </ul>
    <ul class="pointList">
        <li class="point active" data-index="0"></li>
        <li class="point" data-index="1"></li>
        <li class="point" data-index="2"></li>
        <li class="point" data-index="3"></li>
        <li class="point" data-index="4"></li>
    </ul>
    <button type="button" class="btn" id="goPre">&lt</button>
    <button type="button" class="btn" id="goNext">&gt</button>
    <script src="js/banner.js"></script>
</div>

<div class="dzwrap">
    <div class="container">
            <div class="change_mode" id="left" onclick="show_account_div()">账号登录</div>
        <div class="login_main">
            <!-- 使用账号进行登录 -->
            <form class="account_div" id="use_account" method="POST" action="login.do">
                <input type="hidden" name="message" value="account">
                <div>
                    <div class="sign_item">
                            <span>
                                <img src="images/username.png" alt="img">
                            </span>
                        <input class="sign_text" type="text" name="username" id="username" placeholder="请输入账号">
                    </div>
                    <div class="sign_item">
                            <span>
                                <img src="images/password.png" alt="img">
                            </span>
                        <input class="sign_text" type="password" name="password" id="password" placeholder="请输入密码">
                    </div>
                    <div class="sign_btn">
                        <a href="javascript:void(0);" onclick="checkAccountForm()">登录</a>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>

</html>