package top.alexmmd.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import top.alexmmd.domain.MailInfo;
import top.alexmmd.service.MailService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @author 汪永晖
 */
@Service
@Slf4j
public class MailServiceImpl implements MailService {

    private final JavaMailSender mailSender;

    // 注入常量
    @Value("${mail.fromMail.addr}")
    private String from;

    public MailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * 发送纯文本邮件
     *
     * toAddr  发送给谁
     * title   标题
     * content 内容
     */
    @Override
    public void sendTextMail(MailInfo mailInfo) {

        // 纯文本邮件对象
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(mailInfo.getToAddr());
        message.setSubject(mailInfo.getTitle());
        message.setText(mailInfo.getContent());

        try {
            mailSender.send(message);
            log.info("<magazine-subscription-mail>: Text 邮件已经发送给 -> {}，标题是 -> {}", mailInfo.getToAddr(), mailInfo.getTitle());
        } catch (Exception e) {
            log.error("<magazine-subscription-mail>: 发送 Text 邮件给 -> {} 时发生异常！", mailInfo.getToAddr(), e);
        }
    }

    /**
     * 发送 html 邮件
     *
     * toAddr
     * userName
     * title
     * content 内容（HTML）
     */
    @Override
    public void sendHtmlMail(MailInfo mailInfo) {

        // html 邮件对象
        MimeMessage message = mailSender.createMimeMessage();

        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(mailInfo.getToAddr());
            helper.setSubject(mailInfo.getTitle());
            helper.setText(mailInfo.getContent(), true);

            mailSender.send(message);
            log.info("<magazine-subscription-mail>: Html 邮件已经发送给 -> {}，标题是 -> {}", mailInfo.getToAddr(), mailInfo.getTitle());
        } catch (MessagingException e) {
            log.error("<magazine-subscription-mail>: 发送 Html 邮件给 -> {} 时发生异常！", mailInfo.getToAddr(), e);
        }
    }

    /**
     * 发送带附件的邮件
     *
     * toAddr
     * title
     * content
     * filePath 附件地址
     */
    @Override
    public void sendAttachmentsMail(MailInfo mailInfo) {

        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(mailInfo.getToAddr());
            helper.setSubject(mailInfo.getTitle());
            helper.setText(mailInfo.getContent(), true);

            String filePath = mailInfo.getFilePath();
            FileSystemResource file = new FileSystemResource(new File(filePath));
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
            helper.addAttachment(fileName, file);

            mailSender.send(message);
            log.info("<magazine-subscription-mail>: 带附件的邮件已经发送给 -> {}，标题是 -> {}", mailInfo.getToAddr(), mailInfo.getTitle());
        } catch (MessagingException e) {
            log.error("<magazine-subscription-mail>: 发送带附件的邮件给 -> {} 时发生异常！", mailInfo.getToAddr(), e);
        }
    }

    /**
     * 发送文本中有静态资源（图片）的邮件
     *
     * toAddr
     * title
     * content
     * rscPath 资源路径
     * rscId   资源id (可能有多个图片)
     */
    @Override
    public void sendInlineResourceMail(MailInfo mailInfo) {

        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(mailInfo.getToAddr());
            helper.setSubject(mailInfo.getTitle());
            helper.setText(mailInfo.getContent(), true);

            FileSystemResource res = new FileSystemResource(new File(mailInfo.getRscPath()));
            helper.addInline(mailInfo.getRscId(), res);

            mailSender.send(message);
            log.info("<magazine-subscription-mail>: 嵌入静态资源的邮件已经发送给 -> {}，标题是 -> {}", mailInfo.getToAddr(), mailInfo.getTitle());
        } catch (MessagingException e) {
            log.error("<magazine-subscription-mail>: 发送嵌入静态资源的邮件给 -> {} 时发生异常！", mailInfo.getToAddr(), e);
        }
    }
}
