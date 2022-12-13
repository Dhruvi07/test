package com.orderProductPayment.dtos;

import java.util.HashSet;
import java.util.Set;

public class OrderDto {
    public Integer orderId ;
    public String name ;
    public PaymentDto payment = new PaymentDto();
    public Set<ProductDto> products = new HashSet<>();
}


