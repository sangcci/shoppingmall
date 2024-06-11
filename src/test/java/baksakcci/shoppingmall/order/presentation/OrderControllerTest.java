package baksakcci.shoppingmall.order.presentation;

import static baksakcci.shoppingmall.order.fixture.OrderFixtureProvider.주문_상세_정보_생성;
import static baksakcci.shoppingmall.order.fixture.OrderFixtureProvider.주문서_생성;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import baksakcci.shoppingmall.order.application.port.OrderService;
import baksakcci.shoppingmall.order.domain.OrderCreate;
import baksakcci.shoppingmall.order.domain.OrderData;
import baksakcci.shoppingmall.order.infra.OrderQueryRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
@ActiveProfiles("test")
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();
    @MockBean
    private OrderService orderService;
    @MockBean
    private OrderQueryRepository orderQueryRepository;

    @Test
    void 주문_하기() throws Exception {
        // given
        OrderCreate orderCreate = 주문서_생성();
        when(orderService.create(orderCreate)).thenReturn(1L);

        // when & then
        mockMvc.perform(
                        post("/order/create")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(orderCreate)))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

    @Test
    void 특정_주문_내역_조회() throws Exception {
        // given
        Long orderId = 1L;
        when(orderQueryRepository.findById(anyLong())).thenReturn(주문_상세_정보_생성());

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
                ).andDo(print());
    }

    @Test
    void 주문_내역_페이지_별_조회() throws Exception {
        // given
        OrderData orderData = 주문_상세_정보_생성();
        List<OrderData> orderDatas = new ArrayList<>();
        orderDatas.add(orderData);
        when(orderQueryRepository.findAllByPagination(anyInt(), anyInt())).thenReturn(orderDatas);

        // when & then
        mockMvc.perform(
                        get("/order/list" + "?page=0&size=10"))
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.data[0].orderItemDatas[0].productId").value(1),
                        jsonPath("$.data[0].orderItemDatas[1].productId").value(2)
                ).andDo(print());
    }

    @Test
    void 주문_취소() throws Exception {
        Long orderId = 1L;
        doNothing().when(orderService).cancel(anyLong());

        mockMvc.perform(
                        put("/order/" + orderId + "/cancel"))
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.code").value(200)
                ).andDo(print());
    }

    @Test
    void 주문_삭제() throws Exception {
        Long orderId = 1L;
        doNothing().when(orderService).delete(anyLong());

        mockMvc.perform(
                        delete("/order/" + orderId + "/delete"))
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.code").value(200)
                ).andDo(print());
    }
}
