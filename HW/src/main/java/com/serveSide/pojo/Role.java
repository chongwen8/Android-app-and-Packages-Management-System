package com.serveSide.pojo;

public class Role {
    private int power;
    private String roleName;

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "Role{" +
                "power=" + power +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
