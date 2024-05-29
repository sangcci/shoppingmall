package baksakcci.shoppingmall.order.presentation;

import baksakcci.shoppingmall.order.application.port.OrderService;
import baksakcci.shoppingmall.order.domain.Order;
import baksakcci.shoppingmall.order.domain.OrderCreate;
import baksakcci.shoppingmall.order.domain.OrderData;
import baksakcci.shoppingmall.order.infra.OrderQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderQueryRepository orderQueryRepository;

    @PostMapping("/create")
    public ResponseEntity place(@RequestBody OrderCreate orderCreate) {
        orderService.create(orderCreate);
        return ResponseEntity.ok().build();
    }

    @GetMapping("list")
    public Order getList() {
        return null;
    }

    @GetMapping("{id}")
    public ResponseEntity<OrderData> getOne(@PathVariable("id") Long id) {
        OrderData orderData = orderQueryRepository.findById(id);
        return ResponseEntity.ok().body(orderData);
    }

}
