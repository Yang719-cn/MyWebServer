package cn.yang719.SpringWeb.utils.file.image;

import org.apache.tomcat.util.codec.binary.Base64;

import java.io.*;

public class ImageGet {

    public static String getBase64(String url) throws IOException {
        InputStream in = new FileInputStream(url);
        byte[] data = new byte[in.available()];
        in.read();
        in.close();
        return Base64.encodeBase64String(data);
    }
}
