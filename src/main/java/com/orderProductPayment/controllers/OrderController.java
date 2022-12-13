package com.orderProductPayment.controllers;
import com.orderProductPayment.dtos.OrderDto;
import com.orderProductPayment.model.Order;
import com.orderProductPayment.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "app/order")
public class OrderController {
    @Autowired
    OrderService os;

    @PostMapping(value = "/create")
    OrderDto createOrder(@RequestBody Order o){
        return os.createOrder(o);
    }

    @GetMapping(value = "/all")
    Iterable<OrderDto> allOrders(){
        return os.allOrders();
    }

    @GetMapping(value= "/{id}")
    OrderDto oneOrder(@PathVariable Integer id){
        return os.oneOrder(id);
    }


    @GetMapping(value= "/{orderId}/product/{productId}")
    OrderDto insertProduct(@PathVariable Integer orderId , @PathVariable Integer productId ){
        return os.insertProduct(orderId,productId);
    }

    @DeleteMapping(value = "{orderId}/product/{productId}")
    OrderDto deleteProductFromOrder(@PathVariable Integer orderId , @PathVariable Integer productId ){
        return os.deleteFromOrder(orderId,productId);
    }

}
