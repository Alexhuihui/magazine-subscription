package top.alexmmd.domain.request;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author 汪永晖
 */
@Data
@ToString
public class NewMagazineRequest {

    @NotEmpty
    private String name;

    @NotNull
    private BigDecimal price;
}
