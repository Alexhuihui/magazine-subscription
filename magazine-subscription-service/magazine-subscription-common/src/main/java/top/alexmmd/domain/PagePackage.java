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
public class PagePackage {

    private Integer page;
    private Integer size;
}
