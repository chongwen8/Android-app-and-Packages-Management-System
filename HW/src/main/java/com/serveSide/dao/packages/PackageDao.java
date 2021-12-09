package com.serveSide.dao.packages;

import com.serveSide.pojo.Package;

import java.util.List;

public interface PackageDao {
    public Package getPackage();
    public boolean addPackage(int courier, int customer);
    public boolean delPackage(int courier, int customer);
    public List<Package> getPackagesList(String customerName);
    public boolean createView(List<String> options);
    public List<String> getQrCodeInfo();
    public List<String> getColumnsOfView();
}
