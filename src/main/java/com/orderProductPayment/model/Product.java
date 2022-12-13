package com.orderProductPayment.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@JsonIgnoreType
@Entity
@Table(name = "productTable")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId ;
    private String name ;

    private Integer price;

    @ManyToMany(mappedBy = "products")
    private Set<Order> orders = new HashSet<>();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> order) {
        this.orders = order;
    }

    public Integer getId() {
        return productId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Product(String name, int price, Set<Order> orders) {
        this.name = name;
        this.price = price;
        this.orders = orders;
    }
    public Product() {
    }

}
