/**
 * @file:  MailUtil.java
 * @author:  zhang_yulin
 * @date:  2020/08/26
 * @copyright:  2020-2023 www.bosssoft.com.cn Inc. All rights reserved.
 */
package com.sd365.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @class:  MailUtil
 * @classdesc:  邮件工具类
 * @author:  zhang_yulin
 * @date:  2020/8/28
 * @version:  1.0.0
 * @see
 * @since
 */
public class MailUtil {

    // Spring的邮件工具类，实现了MailSender和JavaMailSender接口
    private JavaMailSenderImpl mailSender;

    /**
     * 初始化邮件发送数据
     * @param host 服务器
     * @param username 发送人
     * @param passwd 发送人密码(授权码）
     */
    public void setInitData(String host, String username, String passwd){
        //创建邮件发送服务器
        mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        // mailSender.setPort(465);
        mailSender.setUsername(username);
        mailSender.setPassword(passwd);
        //加认证机制
        Properties javaMailProperties = new Properties();
        javaMailProperties.put("mail.smtp.auth", true);
        javaMailProperties.put("mail.smtp.starttls.enable", true);
        javaMailProperties.put("mail.smtp.timeout", 5000);
        mailSender.setJavaMailProperties(javaMailProperties);

        //("初始化邮件发送信息完成");

    }
    /**
     * 发送普通文本
     * @param email 对方邮箱地址
     * @param subject 主题
     * @param text 邮件内容
     */
    public void simpleMailSend(String email, String subject, String text) {
        //创建邮件内容
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom(mailSender.getUsername());
        message.setTo(email);
        message.setSubject(subject);
        message.setText(text);
        //发送邮件
        mailSender.send(message);
        System.out.println("发送成功");
    }

    /**
     * 发送附件,支持多附件
     * //使用JavaMail的MimeMessage，支付更加复杂的邮件格式和内容
     //MimeMessages为复杂邮件模板，支持文本、附件、html、图片等。
     * @param email 对方邮箱
     * @param subject 主题
     * @param text 内容
     * @param paths 附件路径，和文件名
     * @throws MessagingException
     */
    public void attachedSend(String email, String subject, String text, Map<String,String> paths) throws MessagingException {

        MimeMessage message = mailSender.createMimeMessage();
        //创建MimeMessageHelper对象，处理MimeMessage的辅助类
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        //使用辅助类MimeMessage设定参数
        helper.setFrom(mailSender.getUsername());
        helper.setTo(email);
        helper.setSubject(subject);
        helper.setText(text);

        if (paths != null){
            paths.forEach((k, v)->{
                //加载文件资源，作为附件
                FileSystemResource file = new FileSystemResource(v);
                try {
                    //添加附件
                    helper.addAttachment(k, file);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        //发送邮件
        mailSender.send(message);
    }

    /**
     * 发送html文件，支持多图片
     * @param email 对方邮箱
     * @param subject 主题
     * @param text 内容
     * @param paths 富文本中添加用到的路径，一般是图片，或者css,js文件
     * @throws MessagingException
     */
    public void richContentSend(String email, String subject, String text, Map<String, String> paths) throws MessagingException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom(mailSender.getUsername());
        helper.setTo(email);
        helper.setSubject(subject);
        //第二个参数true，表示text的内容为html，然后注意<img/>标签，src='cid:file'，'cid'是contentId的缩写，'file'是一个标记，
        //需要在后面的代码中调用MimeMessageHelper的addInline方法替代成文件
        helper.setText(text,true);
        //文件地址相对应src目录
        // ClassPathResource file = new ClassPathResource("logo.png");

        if (paths!=null){
            paths.forEach((k, v)->{
                //文件地址对应系统目录
                FileSystemResource file = new FileSystemResource(v);
                try {
                    helper.addInline(k, file);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        mailSender.send(message);
    }

    /**
     * 群发多人，且多附件
     * @param mailto 多人邮件地址
     * @param subject 主题
     * @param text 内容
     * @param filePath 文件路径
     * @throws Exception
     */
    public void sendBatchMailWithFile(String[] emails, String subject, String text, String[] filePath) throws Exception {

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        messageHelper.setFrom(new InternetAddress(MimeUtility.encodeText(mailSender.getUsername())));
        messageHelper.setSubject(subject);
        if (filePath != null) {
            BodyPart mdp = new MimeBodyPart();// 新建一个存放信件内容的BodyPart对象
            mdp.setContent(text, "text/html;charset=UTF-8");// 给BodyPart对象设置内容和格式/编码方式
            Multipart mm = new MimeMultipart();// 新建一个MimeMultipart对象用来存放BodyPart对象
            mm.addBodyPart(mdp);// 将BodyPart加入到MimeMultipart对象中(可以加入多个BodyPart)
            // 把mm作为消息对象的内容
            MimeBodyPart filePart;
            FileDataSource filedatasource;
            // 逐个加入附件
            for (int j = 0; j < filePath.length; j++) {
                filePart = new MimeBodyPart();
                filedatasource = new FileDataSource(filePath[j]);
                filePart.setDataHandler(new DataHandler(filedatasource));
                try {
                    filePart.setFileName(MimeUtility.encodeText(filedatasource.getName()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                mm.addBodyPart(filePart);
            }
            mimeMessage.setContent(mm);
        } else {
            messageHelper.setText(text, true);
        }

        List<InternetAddress> list = new ArrayList<InternetAddress>();// 不能使用string类型的类型，这样只能发送一个收件人
        for (int i = 0; i < emails.length; i++) {
            list.add(new InternetAddress(emails[i]));
        }
        InternetAddress[] address = list.toArray(new InternetAddress[list.size()]);

        mimeMessage.setRecipients(Message.RecipientType.TO, address);
        mimeMessage = messageHelper.getMimeMessage();

        mailSender.send(mimeMessage);
    }
}