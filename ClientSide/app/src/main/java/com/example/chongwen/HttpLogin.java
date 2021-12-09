package com.example.chongwen;

import android.util.Log;

import com.example.chongwen.util.Constant;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class HttpLogin {

    private static final String TAG = "HttpLogin";
    private static String address_head = "http://192.168.31.40:8080/ChongWen/";


    public static int LoginByPost(Courier courier) {

        String username = courier.getUserName();        // 用户名
        String password = courier.getPassword();        // 密码
        String address = address_head + "appLogin.do";  // 请求方法


        String result = "";                             // 返回结果
        try {
            URL url = new URL(address);//初始化URL
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");//请求方式

            //超时信息
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(5000);

            //post方式不能设置缓存，需手动设置为false
            conn.setUseCaches(false);

            //我们请求的数据
            String data = "username=" + URLEncoder.encode(username, "UTF-8") +
                    "&password=" + URLEncoder.encode(password, "UTF-8");

            //获取输出流
            OutputStream out = conn.getOutputStream();

            out.write(data.getBytes());
            out.flush();
            out.close();
            conn.connect();

            Log.d(TAG, "" + conn.getResponseCode());
            if (conn.getResponseCode() == 200) {
                // 获取响应的输入流对象
                InputStream is = conn.getInputStream();
                // 创建字节输出流对象
                ByteArrayOutputStream message = new ByteArrayOutputStream();
                // 定义读取的长度
                int len = 0;
                // 定义缓冲区
                byte buffer[] = new byte[1024];
                // 按照缓冲区的大小，循环读取
                while ((len = is.read(buffer)) != -1) {
                    // 根据读取的长度写入到os对象中
                    message.write(buffer, 0, len);
                }
                // 释放资源
                is.close();
                message.close();
                // 返回字符串
                result = new String(message.toByteArray());

                if ("failed".equals(result)){
                    return Constant.ERROR_COURIER;
                }else if ("repeat".equals(result)){
                    return Constant.ERROR_COURIER_REPEAT;
                }
                // 如果没有异常
                String[] strings = result.split("&");
                courier.setID(Integer.parseInt(strings[0]));        // 编号
                courier.setName(strings[1]);                        // 姓名
                courier.setPhoneNum(strings[2]);                    // 电话
                // 登录成功
                return Constant.SUCCESS_COURIER;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 登录成功
        return Constant.ERROR_COURIER;
    }

    public static int RegisterByPost(Courier courier) {
        String address = address_head + "appRegister.do";
        String result = "";

        try {
            URL url = new URL(address);//初始化URL
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");//请求方式

            //超时信息
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(5000);

            //post方式不能设置缓存，需手动设置为false
            conn.setUseCaches(false);

            //我们请求的数据
            String data = "name=" + URLEncoder.encode(courier.getName(), "UTF-8") +
                    "&username=" + URLEncoder.encode(courier.getUserName(), "UTF-8") +
                    "&password=" + URLEncoder.encode(courier.getPassword(), "UTF-8") +
                    "&phoneNum=" + URLEncoder.encode(courier.getPhoneNum(), "UTF-8");

            //获取输出流
            OutputStream out = conn.getOutputStream();

            out.write(data.getBytes());
            out.flush();
            out.close();
            conn.connect();

            if (conn.getResponseCode() == 200) {
                // 获取响应的输入流对象
                InputStream is = conn.getInputStream();
                // 创建字节输出流对象
                ByteArrayOutputStream message = new ByteArrayOutputStream();
                // 定义读取的长度
                int len = 0;
                // 定义缓冲区
                byte buffer[] = new byte[1024];
                // 按照缓冲区的大小，循环读取
                while ((len = is.read(buffer)) != -1) {
                    // 根据读取的长度写入到os对象中
                    message.write(buffer, 0, len);
                }
                // 释放资源
                is.close();
                message.close();
                // 返回字符串
                result = new String(message.toByteArray());
                if ("success".equals(result)){
                    return Constant.SUCCESS_COURIER;
                } else if ("repeat".equals(result)){
                    return Constant.ERROR_COURIER_REPEAT;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return Constant.ERROR_COURIER;

    }

}
