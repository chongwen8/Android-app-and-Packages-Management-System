package com.serveSide.service.courier;

import com.serveSide.dao.courier.CourierDao;
import com.serveSide.pojo.Courier;
import com.serveSide.util.Constant;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.List;

public class CourierService {
    private final CourierDao courierDao;
    public CourierService(){
        courierDao = new CourierDao();
    }
    public Courier login(String username, String password) {
        // 账号密码不能为空
        if (username==null || "".equals(username) || password==null || "".equals(password)) {
            return null;
        }
        // 对密码进行加密
        String pwd = DigestUtils.md5Hex(DigestUtils.md5Hex(password) + Constant.SALT);
        System.out.println(pwd);
        return courierDao.getCourier(username, pwd);
    }

    public int register(String name, String username, String password, String phoneNum) {
        // 如果注册信息中含空
        if (name==null || "".equals(name)
         || username==null || "".equals(username)
         || password==null || "".equals(password)
         || phoneNum==null || "".equals(phoneNum)) {
            return Constant.ERROR_COURIER_EMPTY;
        }

        // 如果用户已存在
        if (courierDao.getCourier(username, null)!=null) {
            return Constant.ERROR_COURIER_REPEAT;
        }

        // MD5算法加密
        password = DigestUtils.md5Hex(DigestUtils.md5Hex(password) + Constant.SALT);
        Courier courier = new Courier(name, username, password, phoneNum);
        // 如果注册成功
        if (courierDao.addCourier(courier)){
            return Constant.SUCCESS_COURIER;
        }

        // 未知错误
        return Constant.ERROR_COURIER_UNKNOWN;
    }

    public List<Courier> getCouriers(){
        return courierDao.getCourierList();
    }
}
