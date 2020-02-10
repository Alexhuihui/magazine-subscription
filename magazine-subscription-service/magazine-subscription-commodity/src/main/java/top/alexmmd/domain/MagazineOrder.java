package top.alexmmd.domain;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author 汪永晖
 */
@Entity
@Table(name = "t_order")
@Builder
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
@DynamicInsert
public class MagazineOrder extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "customer")
    private String customer;

    @Column(name = "order_description")
    private String orderDescription;

    @Column(name = "order_price")
    private BigDecimal orderPrice;

    @ManyToMany
    @JoinTable(name = "t_order_magazine")
    @OrderBy("commodityId")
    private List<Magazine> items;

    @Enumerated
    @Column(nullable = false)
    private OrderState state;
}
