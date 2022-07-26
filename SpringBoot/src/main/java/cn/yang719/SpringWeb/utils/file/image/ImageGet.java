package cn.yang719.SpringWeb.utils.file.image;

import cn.yang719.utils.image.Base64Tool;
import org.apache.tomcat.util.codec.binary.Base64;

import java.io.*;

public class ImageGet {

    public static String getBase64(String url) throws IOException {
        return Base64Tool.imgToBase64(url);
    }
}
