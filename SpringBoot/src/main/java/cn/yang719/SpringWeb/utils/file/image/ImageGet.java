package cn.yang719.SpringWeb.utils.file.image;

import cn.yang719.SpringWeb.dto.Result;
import cn.yang719.utils.image.Base64Tool;
import cn.yang719.utils.image.ImgsBase64;
import org.apache.tomcat.util.codec.binary.Base64;

import java.io.*;

public class ImageGet {

    //java读取本地文件再将其转换为base64发到前端
    public static Result getBase64(String url) throws IOException {
        String str;
        try {
            str = Base64Tool.imgToBase64(url);
            return Result.ok().data(str);
        }catch (IOException e){
            e.printStackTrace();
            return Result.fail().msg("未获取到图片").data(ImgsBase64.failToGetImg);
        }
    }

    public static Result imgDownload(){
        return Result.fail().msg("未完工");
    }
}
