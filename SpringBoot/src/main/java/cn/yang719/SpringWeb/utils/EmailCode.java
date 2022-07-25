package cn.yang719.SpringWeb.utils;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

public class EmailCode {

    private EmailCode() {

    }

    public static void send(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.163.com");//指定用来发送Email的邮件服务器主机名
        mailSender.setPort(25);//默认端口，标准的SMTP端口
        mailSender.setUsername("test@163.com");//用户名
        mailSender.setPassword("test");//密码

        SimpleMailMessage message = new SimpleMailMessage();//消息构造器
        message.setFrom("test@163.com");//发件人
        message.setTo("shoujian@tom.com");//收件人
        message.setSubject("Spring Email Test");//主题
        message.setText("hello world!!");//正文
        mailSender.send(message);
        System.out.println("邮件发送完毕");

    }
}
