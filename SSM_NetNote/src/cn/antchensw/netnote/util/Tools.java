package cn.antchensw.netnote.util;

import java.net.URLEncoder;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

public class Tools {

    private String to = "";
    private static String from = "antschen@163.com";

    private static String name = "antschen@163.com";
    private static String authorization_code = "chen107908";
    // SMTP服务器(这里用的163 SMTP服务器)
    public static final String MEAIL_163_SMTP_HOST = "smtp.163.com";
    public static final String SMTP_163_PORT = "25";// 端口号,这个是163使用到的;QQ的应该是465或者875

    private transient static Properties prop;
    private static MimeMessage message;
    private transient static Session session;

    public static String toLevel(int level) {
        switch (level) {
        case 1:
            return "重要";
        case 2:
            return "非常重要 ! ";
        case 3:
            return "不重要 ";
        default:
            return "一般";
        }
    }

    public static boolean sendEmail(String to, String title, String emailContent) throws Exception {

        try {
            System.out.println("开始向" + to + "发送邮件...");
            prop = System.getProperties();
            prop.setProperty("mail.transport.protocol", "smtp"); // 协议
            prop.setProperty("mail.smtp.host", "smtp.163.com"); // 主机名
            prop.setProperty("mail.smtp.auth", "true"); // 是否开启权限控制
            prop.setProperty("mail.debug", "true"); // 返回发送的cmd源码
            session = Session.getInstance(prop);
            message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));// 自己的email
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));// 要发送的email，可以设置数组
            message.setSubject(title);

            message.setText(emailContent);

            // 不被当作垃圾邮件的关键代码--Begin
            // 如果不加这些代码，发送的邮件会自动进入对方的垃圾邮件列表
            message.addHeader("X-Priority", "3");
            message.addHeader("X-MSMail-Priority", "Normal");
            message.addHeader("X-Mailer", "Microsoft Outlook Express 6.00.2900.2869"); // 本文以outlook名义发送邮件，不会被当作垃圾邮件
            message.addHeader("X-MimeOLE", "Produced By Microsoft MimeOLE V6.00.2900.2869");
            message.addHeader("ReturnReceipt", "1");
            // 不被当作垃圾邮件的关键代码--end
            message.saveChanges();
            Transport transport = session.getTransport();
            transport.connect("smtp.163.com", name, authorization_code);
            transport.sendMessage(message, message.getAllRecipients());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String getFileName(HttpServletRequest request, String photoname) throws Exception {
        // TODO
        String[] ieBrowserKeyWords = { "MSIE", "Trident", "Edge" };
        String userAgent = request.getHeader("User-Agent");
        for (String keyWord : ieBrowserKeyWords) {
            if (userAgent.contains(keyWord)) {
                return URLEncoder.encode(photoname, "UTF-8");
            }
        }
        return new String(photoname.getBytes("UTF-8"), "ISO-8859-1");
    }

}
