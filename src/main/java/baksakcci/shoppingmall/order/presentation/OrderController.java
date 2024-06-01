package baksakcci.shoppingmall.order.presentation;

import baksakcci.shoppingmall.common.response.Response;
import baksakcci.shoppingmall.order.application.port.OrderService;
import baksakcci.shoppingmall.order.domain.Order;
import baksakcci.shoppingmall.order.domain.OrderCreate;
import baksakcci.shoppingmall.order.domain.OrderData;
import baksakcci.shoppingmall.order.infra.OrderQueryRepository;
import baksakcci.shoppingmall.order.presentation.dto.OrderIdResponse;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderQueryRepository orderQueryRepository;

    @PostMapping("/create")
    public ResponseEntity<Response> place(@RequestBody OrderCreate orderCreate) {
        long orderId = orderService.create(orderCreate);
        OrderIdResponse orderIdResponse = new OrderIdResponse(orderId);
        return ResponseEntity.created(URI.create("/order/" + orderId)).body(Response.success(201, "주문이 정상적으로 처리되었습니다.", orderIdResponse));
    }

    @GetMapping("{id}")
    public ResponseEntity<Response> getOne(@PathVariable("id") Long id) {
        OrderData orderData = orderQueryRepository.findById(id);
        return ResponseEntity.ok().body(Response.success(200, "요청한 데이터 처리", orderData));
    }

    @GetMapping("list")
    public Order getList() {
        return null;
    }
}
