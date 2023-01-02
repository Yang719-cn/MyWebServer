package cn.yang719.utils.mail;

import cn.yang719.utils.Log;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class MailInfo {
    /**
     * 标准的SMTP端口
     */
    public static Integer port = 25;

    /**
     * 收件人
     */
    private String to;

    /**
     * 发件人
     */
    private String from;

    /**
     * 邮件服务器主机地址
     */
    private String host;

    /**
     * 发件人邮件服务器的密码
     */
    private String fromPwd;

    /**
     * 邮寄大标题
     */
    private String title;

    /**
     * 邮件信息
     */
    private String mailMsg;

    /**
     * 用于记录发送纯文本的简单邮件的信息
     * @param to 收件人
     * @param from 发件人
     * @param host 邮件服务器主机地址
     * @param fromPwd 发件人邮件服务器的密码
     * @param title 邮寄大标题
     * @param mailMsg 邮件信息
     */
    public MailInfo(String to, String from, String host, String fromPwd, String title, String mailMsg) {
        this.to = to;
        this.from = from;
        this.host = host;
        this.fromPwd = fromPwd;
        this.title = title;
        this.mailMsg = mailMsg;
    }

    public String getTo() {
        return to;
    }

    public String getFrom() {
        return from;
    }

    public String getHost() {
        return host;
    }

    public String getFromPwd() {
        return fromPwd;
    }

    public String getTitle() {
        return title;
    }

    public String getMailMsg() {
        return mailMsg;
    }

    public static MailInfo getMailInfoByProperties(){
        return null;
    }

}
