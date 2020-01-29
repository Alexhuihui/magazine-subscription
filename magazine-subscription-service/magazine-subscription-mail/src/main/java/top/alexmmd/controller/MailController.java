package top.alexmmd.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.alexmmd.domain.MailInfo;
import top.alexmmd.domain.ResponseBean;
import top.alexmmd.service.MailService;

/**
 * @author 汪永晖
 */
@RestController
@RequestMapping("/mail")
@Slf4j
public class MailController {

    private final MailService mailService;

    public MailController(MailService mailService) {
        this.mailService = mailService;
    }

    /**
     * 发送 html 邮件
     *
     * @param mailInfo 邮件基本内容
     */
    @PostMapping("/sendHtml")
    public ResponseBean sendHtmlMail(@RequestBody MailInfo mailInfo) {

        mailService.sendHtmlMail(mailInfo);

        return new ResponseBean(200, "Mail has been send successful.");
    }

    /**
     * 发送纯文本邮件
     *
     * @param mailInfo 邮件基本内容
     */
    @PostMapping("/sendText")
    public ResponseBean sendTextMail(@RequestBody MailInfo mailInfo) {

        mailService.sendTextMail(mailInfo);

        return new ResponseBean(200, "Mail has been send successful.");
    }
}
