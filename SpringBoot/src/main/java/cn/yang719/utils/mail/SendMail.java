package cn.yang719.utils.mail;

import cn.yang719.utils.Log;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class SendMail {
    public static Boolean sendMail(MailInfo info){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(info.getHost());//指定用来发送Email的邮件服务器主机名
        mailSender.setPort(MailInfo.port);//默认端口，标准的SMTP端口
        mailSender.setUsername(info.getFrom());//用户名
        mailSender.setPassword(info.getFromPwd());//密码

        SimpleMailMessage message = new SimpleMailMessage();//消息构造器
        message.setFrom(info.getFrom());//发件人
        message.setTo(info.getTo());//收件人
        message.setSubject(info.getTitle());//主题
        message.setText(info.getMailMsg());//正文
        mailSender.send(message);
        Log.s("SendEmail","邮件发送成功");
        return true;
    }

}
