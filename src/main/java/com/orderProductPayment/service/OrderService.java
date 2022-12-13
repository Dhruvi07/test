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
        if(!lo.isEmpty()) {
            for (Order ord : lo) {
                so.add(utility(ord));
            }
        }
        return so;
    }

    public OrderDto oneOrder(Integer id){
        Order ord = or.findById(id).orElse(new Order());
        return utility(ord);
    }

    public OrderDto insertProduct(Integer orderId, Integer productId) {
        Product q = pr.findById(productId).orElse(new Product());
        Order o = or.findById(orderId).orElse(new Order());
        o.addProduct(q);
        or.save(o);
        return utility(o);
    }

    public OrderDto deleteFromOrder(Integer orderId, Integer productId) {
        Order o = or.findById(orderId).orElse(new Order());
        o.removeProducts(productId);
        or.save(o);
        return utility(o);
    }


    public static OrderDto utility(Order o){
        OrderDto od = new OrderDto();
        od.orderId = o.getOrderId();
        od.name = o.getName() ;
        if(o.getPayment()!=null) {
            od.payment.paymentId = o.getPayment().getPaymentId();
            od.payment.status = o.getPayment().getStatus();
        }
        for (Product p: o.getProducts()) {
            od.products.add( ProductService.utility(p) );
        }
        return od ;
    }

}
