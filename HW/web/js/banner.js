let items = document.getElementsByClassName("item");//获取图片
let goPreBtn = document.getElementById("goPre");//获取左边按钮
let goNextBtn = document.getElementById("goNext");//获取右边按钮
let points = document.getElementsByClassName("point");//获取小圆点
let time = 0;//小圆点图片定时器

let index = 0;//表示第几张图片在展示

// 清楚激活项
let clearActive = function () {
    for (let i = 0; i < items.length; i++) {
        items[i].className = "item";
    }
    for (let i = 0; i < points.length; i++) {
        points[i].className = "point";
    }
}

// 设置激活项
let goIndex = function () {
    clearActive();
    items[index].className = "item active";
    points[index].className = "point active";
    time = 0;//这里的time是定时器的时间
}

let goNext = function () {
    if (index < items.length - 1) {
        index++;
    } else {
        index = 0;
    }
    goIndex();
}

let goPre = function () {
    if (index == 0) {
        index = 4;
    } else {
        index--;
    }
    goIndex();
}


//挂载事件
goNextBtn.addEventListener("click", function () {
    goNext();
})
goPreBtn.addEventListener("click", function () {
    goPre();
})

// 为每个小圆点添加 事件监听器
for (let i = 0; i < points.length; i++) {
    points[i].addEventListener("click", function () {
        let pointIndex = this.getAttribute("data-index");
        index = pointIndex;
        goIndex();
    })
}


//定时轮播
// Windows 对象
// 设置每3秒，换一张图
setInterval(function () {
    time++;
    if (time == 30) {
        goNext();
        time = 0;
    }
}, 100)