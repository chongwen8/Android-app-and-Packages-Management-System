package com.serveSide.util;

import java.io.UnsupportedEncodingException;

public class DESUtil {
    public static byte[] myEncrypt(String context, int key) {
        // 加密
        char[] chars = context.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            chars[i] += key;
        }
        try {
            return new String(chars).getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}