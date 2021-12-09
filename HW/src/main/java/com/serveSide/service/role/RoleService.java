package com.serveSide.service.role;

import com.serveSide.dao.role.RoleDao;
import com.serveSide.pojo.Role;

import java.util.List;

public class RoleService {
    private RoleDao roleDao;
    public RoleService(){
        roleDao = new RoleDao();
    }
    public List<Role> getRoleList() {
        return roleDao.getRoleList();
    }
}
