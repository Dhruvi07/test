package com.orderProductPayment.controllers;

import com.orderProductPayment.dtos.OrderDto;
import com.orderProductPayment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/app/payment")
public class PaymentController {

    @Autowired
    PaymentService pas;

    @GetMapping (value = "create/{o_id}/{p_id}")
    OrderDto createPayment(@PathVariable Integer o_id , @PathVariable Integer p_id){
        return pas.createPayment(o_id , p_id);
    }

    @GetMapping (value = "/getAll")
    Iterable<OrderDto> allPayment(){
        return pas.allPayment();
    }


}
