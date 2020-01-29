package top.alexmmd.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import top.alexmmd.domain.MailInfo;

/**
 * 构建 html 邮件
 *
 * @author 汪永晖
 */
@Service
public class MailContentBuilder {

    private TemplateEngine templateEngine;

    @Autowired
    public MailContentBuilder(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    /**
     * 构建杂志更新的 html 邮件
     * @param mailInfo
     * @return
     */
    public String buildMagazine(MailInfo mailInfo) {
        Context context = new Context();
        context.setVariable("userName", mailInfo.getUserName());
        context.setVariable("message", mailInfo.getContent());
        return templateEngine.process("MailTemplate", context);
    }

}