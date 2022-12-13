package com.orderProductPayment.controllers;

import com.orderProductPayment.dtos.ProductDto;
import com.orderProductPayment.model.Product;
import com.orderProductPayment.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "app/product")
public class ProductController {
    @Autowired
    ProductService ps;

    @PostMapping(value = "/create")
    ProductDto createProduct(@RequestBody Product p){
        return ps.createProduct(p);
    }

    @GetMapping(value = "/all")
    Iterable<ProductDto> allProducts(){
        return ps.allProducts() ;
    }

    @GetMapping(value = "/{id}")
    ProductDto oneProduct(@PathVariable Integer id){
        return ps.oneProduct(id);
    }

    @PutMapping(value = "/{id}")
    ProductDto updateProduct(@PathVariable Integer id , @RequestBody Product p){
        return ps.updateProduct(id,p);
    }


    @DeleteMapping(value = "/delete/{id}")
    String deleteProduct(@PathVariable Integer id , @RequestBody Product p){
        return ps.deleteProduct(id);
    }
}
