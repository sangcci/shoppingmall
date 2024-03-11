package baksakcci.shoppingmall.order.presentation;

import baksakcci.shoppingmall.order.domain.Order;
import baksakcci.shoppingmall.order.application.port.PlaceOrderService;
import baksakcci.shoppingmall.order.presentation.request.OrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order/")
@RequiredArgsConstructor
public class OrderController {

    private final PlaceOrderService placeOrderService;

    @PostMapping("/create")
    public Order place(@RequestBody OrderRequest orderRequest) {
        return placeOrderService.placeOrder(OrderRequest.toDto(1L, orderRequest));
    }
}
