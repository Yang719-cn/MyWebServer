package cn.yang719.utils.PasswordTools;

import cn.yang719.utils.Log;
import cn.yang719.utils.SimpleTools;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Yang719 <br/>
 * @version code >= Java8,build in java17 <br/>
 * 用于服务器等密码的加工和处理 <br/>
 */
public class PasswordToMD5 {

    /**
     * 将输入的字符串进行MD5加密 <br/>
     * @param pwd 需要转换为MD5码的密码 <br/>
     * @return 经过加密的MD5字符串 <br/>
     */
    public static String pwd2MD5(String pwd) {
        MessageDigest md;
        String hashedPwd;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(pwd.getBytes());// 计算md5函数
            /**
             * digest()最后确定返回md5 hash值，返回值为8位字符串。
             * 因为md5 hash值是16位的hex值，实际上就是8位的字符
             * BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
             * 一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方）
             */
            hashedPwd = new BigInteger(1, md.digest()).toString(16);// 16是表示转换为16进制数
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return hashedPwd;
    }

    /**
     * 注意，此为静态方法，使用密码盐加密的方法建议使用生成类，不然会出现忘记保存密码盐错误现象<br/>
     * 根据自定义长度随机生成密码盐
     * @param len 你所需要的密码盐的长度
     * @return 自动生成的密码盐
     */
    public static String saltGenerate(int len){
        return SimpleTools.getRandomString(len);
    }

    /**
     *注意，此为静态方法，使用密码盐加密的方法建议使用生成类，不然会出现忘记保存密码盐错误现象<br/>
     * 随机生成密码盐，但总有懒狗不想手动规定密码盐的长度，所以这里有个不用自定义长度的方法
     * @return
     */
    public static String saltGenerate(){
        return saltGenerate(6);
    }

    /**
     * 注意，此为静态方法，使用密码盐加密的方法建议使用生成类，不然会出现误操作密码盐错误现象 <br/>
     * 根据用户原密码和系统随机生成的密码盐加起来后一起转换为MD5码 <br/>
     * 此举是为了进一步加强密码的安全性，而用户的密码盐建议
     * @param pwd 用户原密码
     * @param salt 用户的密码盐
     * @return 原密码和密码盐拼接在一起后转换的MD5码
     */
    public static String pwdSalt2MD5(String pwd,String salt){
        return pwd2MD5(pwd+salt);
    }

    /*
    以下是通过动态对象生成指定的密码处理对象，以来安全隔离
     */

    private String password;
    private String passwordSalt;
    private boolean ifUserGetSalt;

    /**
     * 首选MD5密码处理对象的构造方式<br/>
     * 你都使用密码处理对象的方式了<br/>
     * 那么获取的加密码肯定加了密码盐的<br/>
     * 一定记得保存你那密码盐
     * @param password 用户原密码明文
     * @param saltLen 自定义密码盐的长度
     */
    public PasswordToMD5(String password,int saltLen){
        this.password = password;
        passwordSalt = saltGenerate(saltLen);
        ifUserGetSalt = false;
    }

    /**
     * 首选MD5密码处理对象的构造方式<br/>
     * 你都使用密码处理对象的方式了<br/>
     * 那么获取的加密码肯定加了密码盐的<br/>
     * 而且你这个B又不想规定盐的长度，那么就默认6位了<br/>
     * 一定记得保存你那密码盐
     * @param password 用户原密码明文
     */
    public PasswordToMD5(String password){
        Log.i("PasswordToMD5","创建MD5密码处理对象");
        this.password = password;
        passwordSalt = saltGenerate();
        ifUserGetSalt = false;
    }

    /**
     * 获取加密后的MD5编码<br/>
     * 记得保存密码处理对象的密码盐，不然你拿不到密文
     * @return 转编好的密码加盐的MD5编码
     */
    public String getMD5Code(){
        if (!ifUserGetSalt){
            Log.e("PasswordToMD5","你要先保存了盐我才让你拿到MD5密文");
            return null;
        }else {
            Log.i("PasswordToMD5","你已经获取过了密码盐");
            Log.c("PasswordToMD5","准备返回转编好的密码加盐的MD5编码");
            return pwd2MD5(password+passwordSalt);
        }
    }

    /**
     * 获取每个密码处理对象随机生成的密码盐，在密码处理对象回收之前一定记得保存这个
     * @return 密码处理对象随机生成的密码盐
     */
    public String getPasswordSalt(){
        ifUserGetSalt = true;
        return passwordSalt;
    }

    @Override
    public String toString() {
        return "PasswordToMD5{" +
                "password='" + password + '\'' +
                ", passwordSalt='" + passwordSalt + '\'' +
                '}';
    }
}
