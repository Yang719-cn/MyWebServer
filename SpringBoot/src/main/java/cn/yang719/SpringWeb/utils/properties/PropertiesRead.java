package cn.yang719.SpringWeb.utils.properties;


import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class PropertiesRead {

    /**
     * 通过读取resource下自定义路径和要读取的项目获取目标数据
     * @param path 配置文件的路径（相对于项目文件resource下的路径）
     * @param key 所要搜寻的键值
     * @return value 获取到的值
     */
    public static String getValue(String path,String key){
        Resource resource = new ClassPathResource(path);
        String value = "未获取到value";

        try {
            //获取文件
            InputStream is = resource.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            //开始读取
            Properties properties = new Properties();
            properties.load(br);
            value = properties.getProperty(key);

            //流关闭
            br.close();
            isr.close();
            is.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return value;
    }
}
