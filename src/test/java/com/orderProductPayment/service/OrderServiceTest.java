package com.orderProductPayment.service;

import com.orderProductPayment.model.Order;
import com.orderProductPayment.repo.OrderRepo;
import com.orderProductPayment.repo.ProductRepo;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class OrderServiceTest {
    @Mock
    private OrderRepo or;
    @Mock
    private ProductRepo pr;
    @InjectMocks
    private OrderService os;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void allUsers(){
        Order o1 = new Order(1 , "Dhruvi");
        Order o2 = new Order(2 , "Jigyasa");
        List<Order> testorder = Arrays.asList(o1,o2);

        when(or.findAll()).thenReturn(testorder);

        assertEquals(2,os.allOrders().spliterator().estimateSize());
        }


}
