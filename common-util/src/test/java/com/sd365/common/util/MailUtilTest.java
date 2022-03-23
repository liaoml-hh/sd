//package com.sd365.common.util;
//
//import org.junit.Test;
//
//import static org.junit.Assert.*;
//
//public class MailUtilTest {
//
//    @Test
//    public void simpleMailSend() {
//        MailUtil test = new MailUtil();
//        //测试发送普通文本
//        test.setInitData("smtp.qq.com","2846955310@qq.com","gwylhfopsbsodghb");
//        //test.setInitData("smtp.163.com","1234@163.com","1234");
//        test.simpleMailSend("463411737@qq.com","测试","测试能不能发邮件！！！");
//        assertTrue(true);
//    }
//
//    @Test
//    public void attachedSend() {
//        /*
//        //测试发送附件
//        MailUtil test = new MailUtil();
//        test.setInitData("smtp.qq.com","2846955310@qq.com","gwylhfopsbsodghb");
//
//        Map<String,String> map = new HashMap<String, String>();
//        map.put("database.zip", "C:\\Users\\Lenovo\\Desktop\\database.zip");
//        try {
//            test.attachedSend("463411737@qq.com", "Hello Attachment", "This is a mail with attachment", map);
//        } catch (MessagingException me) {
//            System.out.println("发送邮件出错");
//        }
//
//        */
//        assertTrue(true);
//    }
//
//    @Test
//    public void richContentSend() {
//        /*
//        //测试发送富文本（html文件）
//        MailUtil test = new MailUtil();
//        test.setInitData("smtp.qq.com","2846955310@qq.com","gwylhfopsbsodghb");
//        String text = "<body><p style='color:red;'>Hello Html Email</p><img src='cid:file'/></body>";
//        Map<String,String> map = new HashMap<String, String>();
//        map.put("file", "C:\\Users\\Lenovo\\Desktop\\dianzi\\Snipaste_2020-08-29_21-00-13.jpg");
//        try {
//            test.attachedSend("tclhydc123@gmail.com", "Hello Attachment", text, map);
//        } catch (MessagingException me) {
//            System.out.println("发送邮件出错");
//        }
//
//        */
//        assertTrue(true);
//    }
//
//    @Test
//    public void sendBatchMailWithFile() {
//        /*
//        //测试群发多人多附件
//        MailUtil test = new MailUtil();
//        test.setInitData("smtp.qq.com","2846955310@qq.com","gwylhfopsbsodghb");
//        String [] address = {"463411737@qq.com","tclhydc123@gmail.com"};
//        String [] filePath = {"C:\\Users\\Lenovo\\Desktop\\ES6\\123.jpg", "C:\\Users\\Lenovo\\Desktop\\ES6\\123.jpg"};
//        try{
//            test.sendBatchMailWithFile(address, "群发多文件", "实时", filePath);
//        }
//        catch (Exception e){
//            System.out.println("发送邮件出错");
//        }
//        */
//        assertTrue(true);
//    }
//}