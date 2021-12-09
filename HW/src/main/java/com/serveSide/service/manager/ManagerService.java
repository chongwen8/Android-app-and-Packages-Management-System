package com.serveSide.service.manager;

import com.serveSide.dao.manager.ManagerDao;
import com.serveSide.pojo.Manager;

import java.util.List;

public class ManagerService {
    private ManagerDao managerDao = null;

    public ManagerService(){
        managerDao = new ManagerDao();
    }
    public Manager login(String account, String password, String phoneNum) {
        return managerDao.getManager(account, password, phoneNum);
    }

    public boolean deleteManager(String account) {
        return false;
    }

    public boolean addManager(Manager manager) {
        return false;
    }

    public List<Manager> getManagers(int power) {
        return managerDao.getManagerList(power);
    }

    public boolean updatePwd(String account, String phoneNum, String newPassword) {
        return false;
    }
}
