package com.orderProductPayment.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

@Entity
@Table(name = "payment_table")
public class Payment {
    @Id
    private Integer paymentId ;

    private String status ;

    @OneToOne(mappedBy = "payment",cascade = CascadeType.ALL)
    @JsonIgnore
    private Order o = new Order();

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public Order getO() {
        return o;
    }

    public void setO(Order o) {
        this.o = o;
    }

    public Payment(Integer paymentId, String status) {
        this.paymentId = paymentId;
        this.status = status;
    }

    public Payment() {
    }

}
