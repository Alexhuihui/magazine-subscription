package top.alexmmd.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * @author 汪永晖
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Base {

    @CreatedDate
    private Date createTime;

    @LastModifiedDate
    private Date updateTime;
}
