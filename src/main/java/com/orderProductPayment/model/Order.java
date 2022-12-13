package com.orderProductPayment.model;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orderTable")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId ;
    private String name ;

    @ManyToMany
    @JoinTable(
            name="order_product",
            joinColumns = @JoinColumn(name="order_id" ),
            inverseJoinColumns = @JoinColumn(name="product_id")
    )
    private Set<Product> products = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_Id")
    Payment payment;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }


    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Order(String name, Set<Product> products, Payment payment) {
        this.name = name;
        this.products = products;
        this.payment = payment;
    }

    public Order(Integer orderId, String name) {
        this.orderId = orderId;
        this.name = name;
    }

    public Order() {
    }

    public void addProduct(Product b) {
        this.products.add(b) ;
    }
    public void removeProducts(Integer productId) {
        if(!this.products.isEmpty()){
            for (Product p : this.products) {
                if(p.getId() == productId){
                    this.products.remove(p);
                }
            }
        }
    }
}