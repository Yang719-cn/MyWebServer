package cn.yang719.SpringWeb.utils.file.image;

import cn.yang719.SpringWeb.dto.Result;
import cn.yang719.SpringWeb.entity.Picture;
import cn.yang719.SpringWeb.service.PicUserService;
import cn.yang719.SpringWeb.service.PictureService;
import cn.yang719.utils.Log;
import cn.yang719.utils.OSTool;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class ImageSave {

    /**
     *
     * @param file 需要
     * @param userName
     * @return 保存好图片的信息
     */
    public static Result saveAndInsertSQL(MultipartFile file,
                                           String userName,
                                           String windowsPath,
                                           String linuxPath,
                                           PictureService service) throws IOException {
        //获取图片长宽
        Integer width;
        Integer height;
        // 通过MultipartFile得到InputStream，从而得到BufferedImage
        BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
        if (bufferedImage == null) {
            // 证明上传的文件不是图片，获取图片流失败，不进行下面的操作
            Log.e("UserController","获取图片流失败");
        }
        assert bufferedImage != null;
        width = bufferedImage.getWidth();
        height = bufferedImage.getHeight();
        String fileSize = "";
        Long filesize = file.getSize();
        if ((filesize / 1024) < 1024)
            fileSize = Long.toString(filesize / 1024) + "B";
        else if ((filesize / 1048576) < 1024)
            fileSize = Long.toString(filesize / 1048576) + "KB";
        else if ((filesize / 1073741824) < 1024)
            fileSize = Long.toString(filesize / 1073741824) + "MB";

        //开始准备上传文件
        Date date = new Date();
        String fileName = userName +
                "$"+file.getOriginalFilename()+"$"+
                (new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(date));

        System.out.println("文件名 = " + file.getOriginalFilename());
        System.out.println("文件类型 = " + file.getContentType());

        //保存路径，win系统和linux下路径不同
        String filePath = null;
        if (OSTool.isWindows())
            filePath = windowsPath;
        if (OSTool.isLinux())
            filePath = linuxPath;

        assert filePath != null;
        if (!new File(filePath).exists()) {
            new File(filePath).mkdirs();
        }
        String pathName = filePath
                + fileName
                + "."
                + Objects.requireNonNull(file.getContentType()).split("/")[1];
        //复制图片到该路径下
        file.transferTo(new File(pathName));

        //图片上传完了后开始保存图片信息
        Picture pic = new Picture();
        pic.setFilesize(fileSize);
        pic.setPicheight(height);
        pic.setPicwidth(width);
        pic.setPicurl(pathName);
        Log.i("UserController",pic.toString());
        Result addPicRes = service.add(pic);
        addPicRes.data(pic);

        return addPicRes;
    }
}
