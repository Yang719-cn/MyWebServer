package cn.yang719.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @version 0.2
 * 在服务器控制台操作时发现有所欠缺，于是根据mc的spogit服务端的log改进了一下
 * @version 0.1
 * 基于我在Android Studio中的习惯自己用的小Log
 */
public class Log {

    /**
     * 当前log输出的时间，静态规定此格式
     */
    private static SimpleDateFormat dateformat
            = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E");

    /**
     * 所有tag的基础部位
     * @param tag 信息的标签
     * @param msg 信息的提示
     * @return 拼接好的Log语句
     */
    static public String base(String tag,String msg){
        return dateformat.format(new Date())
                + " >> "
                + "[" + tag + "]"
                + " : "
                + msg;
    }

    /**
     * 所有tag的基础部位
     * 但这用于由一个目标跳往另一个目标时用
     * @param start
     * @param to
     * @param msg
     * @return 拼接好的Log语句
     */
    static public String base(String start,String to,String msg){
        String tag = start+" --> "+to;
        return base(tag,msg);}

    /**
     * 此处用于红色的警告
     * @param tag
     * @param msg
     */
    static public void e(String tag,String msg){
        System.out.println("\033[31m"+base(tag,msg)+"\33[0m");
    }

    static public void i(String tag,String msg){
        System.out.println(base(tag,msg));
    }

    /**
     * 此处用于绿色的成功
     * @param tag
     * @param msg
     */
    static public void s(String tag,String msg){
        System.out.println("\033[32m"+base(tag,msg)+"\33[0m");
    }

    /**
     * 此处用于蓝色的暂时成功并准备进入下一步
     * @param tag
     * @param msg
     */
    static public void c(String tag,String msg){System.out.println("\033[94m"+base(tag,msg)+"\33[0m");}

    /**
     * 此处用于丛当前目标跳转至指定目标的标签提示
     * @param start 当前目标
     * @param to 要跳转的目标
     * @param msg
     */
    static public void c(String start,String to,String msg){System.out.println("\033[94m"+base(start,to,msg)+"\33[0m");}
}
