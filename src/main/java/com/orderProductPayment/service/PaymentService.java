package com.orderProductPayment.service;

import com.orderProductPayment.dtos.OrderDto;
import com.orderProductPayment.model.Order;
import com.orderProductPayment.model.Payment;
import com.orderProductPayment.repo.OrderRepo;
import com.orderProductPayment.repo.PaymentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PaymentService{

    @Autowired
    PaymentRepo pr;

    @Autowired
    OrderRepo or;

    public OrderDto createPayment(Integer id , Integer paymentId)
    {
        Order o = or.findById(id).orElse(new Order());
        Payment pay = new Payment(paymentId , "Payment Successful");
        pr.save(pay);
        o.setPayment(pr.findById(paymentId).orElse(new Payment()));
        or.save(o);
        return OrderService.utility(o);
    }

    public Iterable<OrderDto> allPayment(){
        Set<OrderDto> so = new HashSet<>();
        List<Payment> lop = pr.findAll();

        for(Payment py:lop ){
            Order op = or.findById( py.getO().getOrderId() ).orElse(new Order());
            so.add(OrderService.utility(op));
        }
        return so;
    }
}
