package com.orderProductPayment.service;

import com.orderProductPayment.dtos.OrderDto;
import com.orderProductPayment.model.Product;
import com.orderProductPayment.model.Order;
import com.orderProductPayment.repo.ProductRepo;
import com.orderProductPayment.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class
OrderService {
    @Autowired
    OrderRepo or;
    @Autowired
    ProductRepo pr ;

    public OrderDto createOrder(Order u){
        return utility(or.save(u));
    }

    public Iterable<OrderDto> allOrders(){
        Set<OrderDto> so = new HashSet<>();
        List<Order> lo = or.findAll();
        for (Order r:lo ) {
            so.add(utility(r));
        }
        return so;
    }

    public OrderDto oneOrder(Integer id){
        Order u = or.findById(id).orElse(new Order());
        return utility(u);
    }

    public OrderDto addProduct(Integer orderId, Product p){
        Order order = or.findById(orderId).orElse(new Order());
        p = pr.save(p);
        order.addProduct(p);
        or.save(order);
        return utility(order);
    }

    public OrderDto insertProduct(Integer orderId, Integer productId) {
        Product q = pr.findById(productId).orElse(new Product());
        Order o = or.findById(orderId).orElse(new Order());
        o.addProduct(q);
        or.save(o);
        return utility(o);
    }

    public OrderDto deleteOrder(Integer orderId, Integer productId) {
        Order o = or.findById(orderId).orElse(new Order());
        o.removeProducts(productId);
        or.save(o);
        return utility(o);
    }


    public static OrderDto utility(Order u){
        OrderDto od = new OrderDto();
        od.orderId = u.getOrderId();
        od.name = u.getName() ;
        if(u.getPayment()!=null) {
            od.payment.paymentId = u.getPayment().getPaymentId();
            od.payment.status = u.getPayment().getStatus();
        }

        for (Product p: u.getProducts()) {
            od.products.add( ProductService.utility(p) );
        }

        return od ;
    }

}
