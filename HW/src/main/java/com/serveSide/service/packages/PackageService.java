package com.serveSide.service.packages;

import com.serveSide.dao.packages.PackageDaoIml;
import com.serveSide.pojo.Package;
import com.serveSide.util.DESUtil;
import com.serveSide.util.GenerateQrcode;
import com.google.zxing.WriterException;
import org.apache.commons.codec.binary.Base64;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class PackageService {
    private PackageDaoIml packageDaoIml;
    public PackageService(){
        packageDaoIml = new PackageDaoIml();
    }
    public List<Package> getPackages(String cusName) {
        return packageDaoIml.getPackagesList(cusName);
    }

    public boolean createView(List<String> options){
        // 必须至少勾选一个
        if (options.size() == 0) {
            return false;
        }

        return packageDaoIml.createView(options);
    }

    public boolean createQrCode() {

        // 得到所有的包裹信息
        List<String> columnsOfView = packageDaoIml.getColumnsOfView();
        StringBuilder stringBuilder = new StringBuilder();
        for (String s:columnsOfView){
            if ("OrderNo".equals(s)){
                stringBuilder.append("订单号："+ "&");
            } else if ("time".equals(s)){
                stringBuilder.append("下单日期：" + "&");
            } else if ("courierID".equals(s)){
                stringBuilder.append("负责人编号：" + "&");
            } else if ("courierName".equals(s)){
                stringBuilder.append("负责人姓名：" + "&");
            } else if ("courierPhoneNum".equals(s)){
                stringBuilder.append("负责人手机号：" + "&");
            } else if ("customerName".equals(s)){
                stringBuilder.append("客户姓名：" + "&");
            } else if ("customerPhoneNum".equals(s)){
                stringBuilder.append("客户手机号：" + "&");
            } else if ("address".equals(s)){
                stringBuilder.append("客户地址：" + "&");
            }
        }
        String msgHeader = stringBuilder.toString();
        List<String> qrCodeInfo = packageDaoIml.getQrCodeInfo();
        // 先将信息加密 再将密文转换为二维码
        for (String content : qrCodeInfo) {
            // 取出快递员编号， 并当作 KEY
            int index = content.indexOf("&");
            String courierNo = content.substring(0, index);
            int DESKey = Integer.parseInt(courierNo);
            // 剔除冗余数据 ——> 快递员编号
            String temp = content.substring(index + 1);
            // 取出订单号  用于创建二维码文件名
            int j = temp.indexOf("&");
            String orderNo = temp.substring(0, j);
            // 加上消息头
            temp = msgHeader + temp;
            System.out.println(DESKey);
            byte[] encryptByte = DESUtil.myEncrypt(temp, DESKey);
            String encrypt = Base64.encodeBase64String(encryptByte);
            // 生成二维码
            File file = new File("/Users/chong/IdeaProjects/HW/QRcode/" + courierNo);
            if (!file.exists()) {
                file.mkdir();
            }
            try {
                GenerateQrcode qrcode = new GenerateQrcode();
                qrcode.createQR(file + "/" + orderNo + ".jpg", encrypt, 200, 200);
//                QrCodeUtil.createQrCode(new FileOutputStream(new File(file+"/"+orderNo+".jpg")),encrypt,2500,"JPEG");
            } catch (WriterException | IOException e) {
                e.printStackTrace();
                return false;
            }
        }

        return true;
    }

}
