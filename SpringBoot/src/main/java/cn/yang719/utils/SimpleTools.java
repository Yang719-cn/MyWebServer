package cn.yang719.utils;

import java.util.Random;

/**
 * @author Yang719 <br/>
 * @version code>=jdk1.8,build in java17 <br/>
 * 一些基本工具 <br/>
 */
public class SimpleTools {

    /**
     * 获取自定义长度的随机字符串 <br/>
     * 本质上通过在全字符中通过随机数找位置拼接 <br/>
     * 只有大小写字母和数字，不支持特殊字符 <br/>
     * @param length 你所需要随机字符串的长度
     * @return 根据你输入长度生产的随机字符串
     */
    public static String getRandomString(int length){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

}
