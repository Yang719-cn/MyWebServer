package cn.yang719.SpringWeb.utils.file;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class FileGet {

    /**
     * 通过response下载文件
     * @param response 服务器接受返回的response
     */
    public static void fileDownload(HttpServletResponse response,String path) throws IOException {
        InputStream in = new FileInputStream(new File(path));
        response.setCharacterEncoding("utf-8");
        OutputStream report = response.getOutputStream();

        report.write(in.read());
    }
}
