package com.orderProductPayment.controllers;
import com.orderProductPayment.dtos.OrderDto;
import com.orderProductPayment.model.Product;
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
    OrderDto createOrder(@RequestBody Order u){
        return os.createOrder(u);
    }

    @GetMapping(value = "/all")
    Iterable<OrderDto> allOrders(){
        return os.allOrders();
    }

    @GetMapping(value= "/{id}")
    OrderDto oneOrder(@PathVariable Integer id){
        return os.oneOrder(id);
    }

    @PutMapping(value = "{orderId}/product")
    OrderDto addProduct(@PathVariable Integer orderId , @RequestBody Product b ){
        return os.addProduct(orderId,b);
    }

    @GetMapping(value= "/{orderId}/product/{productId}")
    OrderDto insertProduct(@PathVariable Integer orderId , @PathVariable Integer productId ){
        return os.insertProduct(orderId,productId);
    }

    @DeleteMapping(value = "{orderId}/product/{productId}")
    OrderDto deleteProduct(@PathVariable Integer orderId , @PathVariable Integer productId ){
        return os.deleteOrder(orderId,productId);
    }

}
