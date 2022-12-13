package com.orderProductPayment.service;

import com.orderProductPayment.model.Order;
import com.orderProductPayment.model.Product;
import com.orderProductPayment.repo.OrderRepo;
import com.orderProductPayment.repo.ProductRepo;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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
    public void testCreateOrder() {
        Order o = new Order(1 , "Dhruvi");

        when(or.save(o)).thenReturn(o);

        assertEquals(1,os.createOrder(o).orderId);
    }


    @Test
    public void testAllOrders(){
        Order o1 = new Order(1 , "Dhruvi");
        Order o2 = new Order(2 , "Jigyasa");
        List<Order> testorder = Arrays.asList(o1,o2);

        when(or.findAll()).thenReturn(testorder);

        assertEquals(2,os.allOrders().spliterator().estimateSize());
    }

    @Test
    public void testOneOrder(){
        Order o1 = new Order(1 , "Dhruvi");
        when(or.findById(1)).thenReturn(Optional.of(o1));

        assertEquals("Dhruvi" , os.oneOrder(1).name);
    }


    @Test
    public void testInsertProduct(){
        Order o = new Order(1 , "Dhruvi");

        Order temp = new Order(1 , "Dhruvi");
        Product p = new Product(1 , "copy" , 30);
        temp.addProduct(p);

        when(pr.findById(1)).thenReturn(Optional.of(p));
        when(or.findById(1)).thenReturn(Optional.of(o));
        when(or.save(o)).thenReturn(temp);

        assertEquals("copy" ,os.insertProduct(1, 1).products.iterator().next().name);
    }

    @Test
    public void testDeleteOrder(){
        Order o = new Order(1 , "Dhruvi");
        Product p = new Product(1 , "Book" , 50);
        o.addProduct(p);

        Order temp = new Order(1 , "Dhruvi");

        when(or.findById(1)).thenReturn(Optional.of(o));
        when(or.save(o)).thenReturn(temp);

        assertEquals(false ,os.deleteFromOrder(1,1).products.iterator().hasNext());

    }

}

