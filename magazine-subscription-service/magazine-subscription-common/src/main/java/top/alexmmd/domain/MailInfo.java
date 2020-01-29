package top.alexmmd.domain;

import lombok.*;

/**
 * @author 汪永晖
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class MailInfo {

    // 收邮件地址
    private String toAddr;

    // 名字
    private String userName;

    // 标题
    private String title;

    // 内容
    private String content;

    // 附件地址
    String filePath;

    // 资源路径
    String rscPath;

    // 资源id (可能有多个图片)
    String rscId;
}
