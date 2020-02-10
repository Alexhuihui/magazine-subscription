package top.alexmmd.service;

import top.alexmmd.domain.Magazine;
import top.alexmmd.domain.MagazineOrder;
import top.alexmmd.domain.OrderState;
import top.alexmmd.domain.ResponseBean;

/**
 * @author 汪永晖
 */
public interface MagazineOrderService {

    ResponseBean get(Long id);

    ResponseBean createOrder(String customer, Magazine... magazineList);

    boolean updateState(MagazineOrder order, OrderState state);
}
