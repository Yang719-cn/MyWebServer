package cn.yang719.utils.Properties;


import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class PropertiesRead {

    /**
     * 通过自定义读取路径和要读取的项目获取目标数据
     * @param path 配置文件的路径
     * @param key 所要搜寻的键值
     * @return value
     */
    public static String getData(String path,String key){
        Resource resource = new ClassPathResource("resource.properties");
        String value = "未获取到value";

        try {
            InputStream is = resource.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            br.close();
            isr.close();
            is.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



        return value;
    }
}
