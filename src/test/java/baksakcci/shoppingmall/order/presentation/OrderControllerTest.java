package baksakcci.shoppingmall.order.presentation;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import baksakcci.shoppingmall.order.domain.OrderCreate;
import baksakcci.shoppingmall.order.domain.OrderCreate.OrderItemCreate;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql(value = "/sql/order-create-controller-test-data.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @Order(1)
    void 사용자는_주문을_할_수_있다() throws Exception {
        // given
        OrderCreate orderCreate = orderCreateFixture();

        // when, then
        mockMvc.perform(
                        post("/order/create")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(orderCreate)))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    @Order(2)
    void 특정_주문_내역_조회() throws Exception {
        // given
        String orderId = "1";

        // when, then
        mockMvc.perform(
                        get("/order/" + orderId))
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.code").value(200),
                        jsonPath("$.data.id").value(1),
                        jsonPath("$.data.orderState").value("PAYMENT_WAITING"),
                        jsonPath("$.data.orderItemDatas[0].name").value("꿀사과")
                );
    }

    OrderCreate orderCreateFixture() {
        OrderItemCreate item1 = OrderItemCreate.builder()
                .productId(1L)
                .qty(2)
                .build();
        OrderItemCreate item2 = OrderItemCreate.builder()
                .productId(2L)
                .qty(3)
                .build();
        ArrayList<OrderItemCreate> items = new ArrayList<>();
        items.add(item1);
        items.add(item2);

        return OrderCreate.builder()
                .orderItemCreates(items)
                .address("경기도")
                .detailAddress("땡땡빌딩 101호")
                .receiverName("홍길동")
                .receiverPhoneNumber("010-1234-5678")
                .build();
    }
}
