package cn.yang719.utils;

import java.util.Locale;
import java.util.Map;

/**
 * @author Yang
 * 2022-8-11
 * 目前支持linux，win和mac的判断
 */
public class OSTool {

    /**
     * 判断本系统是否未win
     * @return
     */
    public static boolean isWindows() {
        boolean b = System.getProperty("os.name").toUpperCase().indexOf("WINDOWS")>=0?true:false;
        Log.i("OSTool","isWindows -> "+b);
        return b;
    }

    /**
     * 判断系统是否未linux
     * @return
     */
    public static boolean isLinux() {
        boolean b = System.getProperty("os.name").toUpperCase().indexOf("LINUX")>=0?true:false;
        Log.i("OSTool","isLinux -> "+b);
        return b;
    }

    /**
     * 判断系统是否为macOS
     * @return
     */
    public static boolean isMac() {
        Log.e("OSTool","老杨这重下等人怎么可能用得上高贵mac了?");
        return System.getProperty("os.name").toUpperCase().indexOf("MAC")>=0?true:false;
    }

    /**
     * 获取本机系统名称
     * @return 系统名称
     */
    public static String getOsName() {
        String osName = System.getProperty("os.name");
        Log.i("OSTool","你的xp系统为 -> "+osName);
        return osName;
    }

    /**
     * 预先设定好一个map用以关联操作系统用什么文件保存路径<br>
     * 只要你预先设定好，你的程序在哪儿跑此方法就会按照你的系统返回对应的路径<br>
     * (属实跨平台了（误）)
     * @param map_osName_path key为操作系统，value为此操作系统下应保存的路径
     * @return 应保存的路径
     */
    public static String autoChoosePath(Map<String,String> map_osName_path){
        String os = null;
        if (isLinux())
            os = "LINUX";
        if (isWindows())
            os = "WINDOWS";
        if (isMac())
            os = "MAC";
        for (Map.Entry<String, String> entry : map_osName_path.entrySet()){
            if (os.equals(entry.getKey().toUpperCase()))
                return entry.getValue();
        }
        Log.e("OSTool","注意，暂未检测到你是什么系统");
        return null;
    }
}
