package top.alexmmd.domain.request;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author 汪永晖
 */
@Data
@ToString
public class NewOrderRequest {

    private String customer;
    
    private List<String> items;
}
