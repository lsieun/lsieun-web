package lsieun.utils;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import static lsieun.utils.LogUtils.err;

public class EmailUtils {
    private final static Logger LOGGER = Logger.getLogger(EmailUtils.class.getCanonicalName());

    private static final String smtp_server = PropertyUtils.getProperty("smtp.server");
    private static final String email_from = PropertyUtils.getProperty("email.from");
    private static final String email_to = PropertyUtils.getProperty("email.to");
    private static final String email_username = PropertyUtils.getProperty("email.username");
    private static final String email_password = PropertyUtils.getProperty("email.password");
    private static final String email_debug = PropertyUtils.getProperty("email.debug");

    private static final int startHour = PropertyUtils.getInt("time.send.email.start.hour");
    private static final int startMinute = PropertyUtils.getInt("time.send.email.start.minute");
    private static final int endHour = PropertyUtils.getInt("time.send.email.end.hour");
    private static final int endMinute = PropertyUtils.getInt("time.send.email.end.minute");

    static {
        LOGGER.info(() -> String.format("Email Time Range: %02d:%02d~%02d:%02d", startHour, startMinute, endHour, endMinute));
    }

    public static boolean send(String title, String text) {
        try {
            //0. 邮件参数
            Properties prop = new Properties();
            prop.put("mail.transport.protocol", "smtp");    // 指定协议
            prop.put("mail.smtp.host", smtp_server);        // 邮件服务器放到了smtp.163.com上
            prop.put("mail.smtp.port", 25);                 // 端口
            prop.put("mail.smtp.auth", "true");             // 用户密码认证
            prop.put("mail.debug", email_debug);            // 调试模式

            //1. 创建一个邮件的会话
            Session session = Session.getDefaultInstance(prop);
            //2. 创建邮件体对象 (整封邮件对象)
            MimeMessage message = new MimeMessage(session);
            //3. 设置邮件体参数:
            //3.1 标题
            message.setSubject(title);
            //3.2 邮件发送时间
            message.setSentDate(new Date());
            //3.3 发件人
            message.setSender(new InternetAddress(email_from));
            //3.4 接收人
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(email_to));
            //3.5内容
            message.setText(text);  // 简单纯文本邮件
            message.saveChanges();   // 保存邮件

            //4. 发送
            Transport trans = session.getTransport();
            trans.connect(email_username, email_password);
            // 发送邮件
            trans.sendMessage(message, message.getAllRecipients());
            trans.close();
            return true;
        } catch (Exception ex) {
            err.log(Level.SEVERE, "unexpected error: " + ex.getMessage(), ex);
        }
        return false;
    }

    public static void daemon() {

        Runnable r = () -> {
            while (true) {
                try {
                    if (DateUtils.betweenTime(startHour, startMinute, endHour, endMinute)) {
//                        String result = JobUtils.getResult();
//                        EmailUtils.send("签到提醒", result);
                    }
                    Thread.sleep(Const.DEFAULT_SLEEP_TIME);

                } catch (Exception e) {
                    // do nothing
                }
            }
        };
        Thread t = new Thread(r);
        t.setDaemon(true);
        t.start();
    }
}
