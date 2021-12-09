package com.example.chongwen.util;

import org.apache.commons.codec.binary.Base64;


public class DESUtil {
    public static String myDecrypt(String context, int key) {
        String temp = new String(Base64.decodeBase64(context));
        char[] chars = temp.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            chars[i] -= key;
        }
        return new String(chars);
    }

}