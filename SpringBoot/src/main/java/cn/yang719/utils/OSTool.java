package cn.yang719.utils;

public class OSTool {

    /**
     * 判断本系统是否未win
     * @return
     */
    public static boolean isWindows() {
        boolean b = System.getProperty("os.name").toUpperCase().indexOf("WINDOWS")>=0?true:false;
        Log.i("OSTool","isWindows->"+b);
        return b;
    }

    /**
     * 判断系统是否未linux
     * @return
     */
    public static boolean isLinux() {
        boolean b = System.getProperty("os.name").toUpperCase().indexOf("LINUX")>=0?true:false;
        Log.i("OSTool","isLinux->"+b);
        return b;
    }
}
