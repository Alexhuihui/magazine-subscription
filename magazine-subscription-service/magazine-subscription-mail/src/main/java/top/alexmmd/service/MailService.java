package top.alexmmd.service;

import top.alexmmd.domain.MailInfo;

/**
 * @author 汪永晖
 */
public interface MailService {

    /**
     * 发送纯文本邮件
     *
     * toAddr  发送给谁
     * title   标题
     * content 内容
     */
    void sendTextMail(MailInfo mailInfo);

    /**
     * 发送 html 邮件
     *
     * toAddr
     * userName
     * title
     * content 内容（HTML）
     */
    void sendHtmlMail(MailInfo mailInfo);

    /**
     * 发送带附件的邮件
     *
     * toAddr
     * title
     * content
     * filePath 附件地址
     */
    void sendAttachmentsMail(MailInfo mailInfo);

    /**
     * 发送文本中有静态资源（图片）的邮件
     *
     * toAddr
     * title
     * content
     * rscPath 资源路径
     * rscId   资源id (可能有多个图片)
     */
    void sendInlineResourceMail(MailInfo mailInfo);

}
