package top.alexmmd.domain;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author 汪永晖
 */
@Entity
@Table(name = "t_magazine")
@Builder
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
@DynamicInsert
public class Magazine extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commodity_id")
    private Long commodityId;

    @Column(name = "commodity_name")
    private String commodityName;

    @Column(name = "commodity_price")
    private BigDecimal commodityPrice;

    @Column(name = "commodity_description")
    private String commodityDescription;
}
