package com.serveSide.util;// Java code to generate QR code

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class GenerateQrcode {

    // Function to create the QR code

    public void createQR(String data, String path, int height, int width) throws WriterException, IOException {
        Map<EncodeHintType, ErrorCorrectionLevel> hashMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
        hashMap.put(EncodeHintType.CHARACTER_SET,
                ErrorCorrectionLevel.L);
        BitMatrix matrix = new MultiFormatWriter().encode(data,
                BarcodeFormat.QR_CODE, width, height);
        MatrixToImageWriter.writeToPath(
                matrix,
                path.substring(path.lastIndexOf('.') + 1),
                Paths.get(path));
    }

    @Test
    public  void  test(){
        GenerateQrcode qrcode = new GenerateQrcode();
        try {
            qrcode.createQR("二维码测试", "/Users/chong/IdeaProjects/HW/QRcode/demo.jpg", 200, 200);
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
    }



}
