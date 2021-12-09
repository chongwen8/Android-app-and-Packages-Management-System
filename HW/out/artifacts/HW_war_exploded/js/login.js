function show_account_div() {
    let account_div = document.getElementsByClassName("account_div");
    let left = document.getElementById("left");
    let phone_div = document.getElementsByClassName("phone_div");
    let right = document.getElementById("right")
    account_div[0].style.display = "block";
    left.style.color = "#2B6CDB";
    left.style.borderColor = "#2B6CDB"
    phone_div[0].style.display = "none";
    right.style.color = "black";
    right.style.borderColor = "black"
}

function show_phone_div() {
    let account_div = document.getElementsByClassName("account_div");
    let left = document.getElementById("left");
    let phone_div = document.getElementsByClassName("phone_div");
    let right = document.getElementById("right")
    account_div[0].style.display = "none";
    left.style.color = "black";
    left.style.borderColor = "black"
    phone_div[0].style.display = "block";
    right.style.color = "#2B6CDB";
    right.style.borderColor = "#2B6CDB";
}

// 判断表单是否为空
function checkPhoneForm() {
    let phone = document.getElementById("phone").value;
    let checkCode = document.getElementById("checkCode").value;
    if (phone === "") {
        window.alert("手机号不能为空");
        return;
    } else if (checkCode === "") {
        window.alert("验证码不能为空");
        return;
    }
    document.getElementById('use_phone').submit();
}

function checkAccountForm() {
    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;
    if (username === "") {
        window.alert("用户名不能为空");
        return;
    } else if (password === "") {
        window.alert("密码不能为空")
        return;
    }
    document.getElementById('use_account').submit();
}

// 申请验证码
function getCode() {
    let xhr = new XMLHttpRequest();
    let phone = document.getElementById("phone").value;
    if ("" === phone) {
        console.log("手机号为空");
        return;
    }
    let data = "message=getCode&phone=" + phone;
    // data = JSON.stringify(data);
    // xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    xhr.open("POST", document.getElementById("PageContext").value + "/login.do", true);
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            console.log(xhr.responseText);
            document.getElementById("getCode").innerText = "已发送";
        }
    }
    console.log(data);
    xhr.send(data);
}

