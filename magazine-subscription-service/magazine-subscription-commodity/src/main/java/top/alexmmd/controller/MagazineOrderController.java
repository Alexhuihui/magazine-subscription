package top.alexmmd.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import top.alexmmd.domain.Magazine;
import top.alexmmd.domain.ResponseBean;
import top.alexmmd.domain.request.NewOrderRequest;
import top.alexmmd.service.MagazineOrderService;
import top.alexmmd.service.MagazineService;

/**
 * @author 汪永晖
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class MagazineOrderController {

    private final MagazineOrderService orderService;
    private final top.alexmmd.service.MagazineService magazineService;

    public MagazineOrderController(MagazineOrderService orderService, MagazineService magazineService) {
        this.orderService = orderService;
        this.magazineService = magazineService;
    }

    @GetMapping("/{id}")
    public ResponseBean getOrder(@PathVariable("id") Long id) {
        return orderService.get(id);
    }

    @PostMapping(path = "/add")
    public ResponseBean create(@RequestBody NewOrderRequest newOrder) {
        log.info("Receive new Order {}", newOrder);
        Magazine[] MagazineList = magazineService.getMagazineByName(newOrder.getItems())
                .toArray(new Magazine[] {});
        return orderService.createOrder(newOrder.getCustomer(), MagazineList);
    }
}
