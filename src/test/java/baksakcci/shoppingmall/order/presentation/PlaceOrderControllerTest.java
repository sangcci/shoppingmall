package baksakcci.shoppingmall.order.presentation;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import baksakcci.shoppingmall.order.presentation.request.OrderRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@Sql(value = "/sql/order-create-controller-test-data.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class PlaceOrderControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void 사용자는_주문을_할_수_있다() throws Exception {
        // given
        OrderItemRequest item1 = OrderItemRequest.builder()
                .productId(1L)
                .qty(2)
                .build();
        OrderItemRequest item2 = OrderItemRequest.builder()
                .productId(2L)
                .qty(3)
                .build();
        ArrayList<OrderItemRequest> items = new ArrayList<>();
        items.add(item1);
        items.add(item2);

        OrderRequest orderRequest = OrderRequest.builder()
                .orderItemRequests(items)
                .address("경기도")
                .detailAddress("땡땡빌딩 101호")
                .receiverName("홍길동")
                .receiverPhoneNumber("010-1234-5678")
                .build();

        // when, then
        mockMvc.perform(
                        post("/api/order/create")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(orderRequest)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.orderItems[0].qty").value(2))
                //.andExpect(jsonPath("$.orderer.id").isNumber())
                .andExpect(jsonPath("$.totalPrice").value(10000 * 2 + 5000 * 3))
                .andExpect(jsonPath("$.deliveryInfo.address").value("경기도"))
                .andExpect(jsonPath("$.orderState").value("PAYMENT_WAITING"));
    }
}
