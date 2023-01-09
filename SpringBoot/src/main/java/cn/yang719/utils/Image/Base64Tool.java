package cn.yang719.utils.Image;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

public class Base64Tool {

    public static String imgToBase64(String imgFilePath) throws IOException{
        // 将图片文件转化为字节数组字符串，并进行 Base64 编码处理
        byte[] data = null;

        // 读取图片字节数组
        InputStream in = new FileInputStream(imgFilePath);
        data = new byte[in.available()];
        in.read(data);
        in.close();

        // 对字节数组Base64编码
        Base64.Encoder encoder = Base64.getEncoder();
        // 返回字节数组的 Base64 编码字符串
        return "data:image/png;base64," + new String(encoder.encode(data));
    }

}
