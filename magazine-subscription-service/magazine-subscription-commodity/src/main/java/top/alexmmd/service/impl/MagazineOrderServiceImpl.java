package top.alexmmd.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.alexmmd.domain.Magazine;
import top.alexmmd.domain.MagazineOrder;
import top.alexmmd.domain.OrderState;
import top.alexmmd.domain.ResponseBean;
import top.alexmmd.repository.MagazineOrderRepository;
import top.alexmmd.service.MagazineOrderService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author 汪永晖
 */
@Service
@Transactional
@Slf4j
public class MagazineOrderServiceImpl implements MagazineOrderService {

    private final MagazineOrderRepository orderRepository;

    public MagazineOrderServiceImpl(MagazineOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public ResponseBean get(Long id) {
        Optional<MagazineOrder> order = orderRepository.findById(id);
        if (!order.isPresent()) {
            return new ResponseBean(500, "查询订单失败");
        }
        log.info("<magazine-commodity>: Get Order -> {}", order.get());
        return new ResponseBean(100, "查询订单成功", order.get());
    }

    @Override
    public ResponseBean createOrder(String customer, Magazine... coffee) {

        List<Magazine> magazines = new ArrayList<>(Arrays.asList(coffee));

        BigDecimal prices = magazines.stream()
                .map(Magazine::getCommodityPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        MagazineOrder order = MagazineOrder.builder()
                .customer(customer)
                .items(magazines)
                .state(OrderState.INIT)
                .orderPrice(prices)
                .build();

        MagazineOrder saved = orderRepository.save(order);
        log.info("New Order: {}", saved);
        return new ResponseBean(100, "成功新增订单", saved);
    }

    @Override
    public boolean updateState(MagazineOrder order, OrderState state) {
        if (state.compareTo(order.getState()) <= 0) {
            log.warn("Wrong State order: {}, {}", state, order.getState());
            return false;
        }
        order.setState(state);
        orderRepository.save(order);
        log.info("Updated Order: {}", order);
        return true;
    }
}
