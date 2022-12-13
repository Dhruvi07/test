package com.orderProductPayment.service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.orderProductPayment.controllers.OrderController;
import com.orderProductPayment.dtos.OrderDto;
import com.orderProductPayment.model.Order;
import com.orderProductPayment.model.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {
    private MockMvc mockMvc;
    ObjectMapper objectMapper = new ObjectMapper() ;
    ObjectWriter objectWriter = objectMapper.writer() ;

    @Mock
    OrderService os;
    @InjectMocks
    OrderController oc;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(oc).build();
    }

    @Test
    public void createOrderTest() throws Exception{
        Order o = new Order(1 , "Dhruvi");
        OrderDto od = OrderService.utility(o);

        when(os.createOrder(Mockito.any(Order.class))).thenReturn(od);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/app/order/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content((objectWriter.writeValueAsString(o)))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",notNullValue()))
                .andExpect(jsonPath("$.name" , is("Dhruvi")));

    }

    @Test
    public void getAllUser() throws Exception{
        Order o1 = new Order(1 , "Dhruvi");
        Order o2 = new Order(2 , "Ritu");


        List<OrderDto> lo = Arrays.asList( OrderService.utility(o1), OrderService.utility(o2));
        when(os.allOrders()).thenReturn(lo);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/app/order/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(2)))
                .andExpect(jsonPath("$[0].name" , is("Dhruvi" ))
                );
    }

    @Test
    public void oneOrderTest() throws Exception{
        Order o = new Order(1 , "Dhruvi");
        OrderDto od = OrderService.utility(o);
        when(os.oneOrder(1)).thenReturn(od);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/app/order/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.orderId" , is(1)))
                .andExpect(jsonPath("$.name" , is("Dhruvi" ))
                );
    }

    @Test
    public void insertProductTest() throws Exception{
        Order o = new Order(1, "Dhruvi");
        Product p = new Product(01 , "pen" , 10);
        o.addProduct(p);
        OrderDto od = OrderService.utility(o);

        when(os.insertProduct(1,01)).thenReturn(od);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/app/order/1/product/01")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.products[0].name" , is("pen")))
                .andExpect(jsonPath("$.name" , is("Dhruvi" ))
                );

    }


    @Test
    public void deleteProductFromOrderTest() throws Exception{
        Order o = new Order(1, "Dhruvi");
        OrderDto od = OrderService.utility(o);

        when(os.deleteFromOrder(1 , 01)).thenReturn(od);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/app/order/1/product/01")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.products" , hasSize(0)))
                .andExpect(jsonPath("$.name" , is("Dhruvi" ))
                );

    }
}