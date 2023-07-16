package com.example.cameraincome.model.product;

import javax.persistence.*;
import java.util.Date;

@Entity
public class CancelRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Orders orders;
    private Date requestDate;
    @Lob
    private String reason;
    private String status;
    private boolean isConfirmByOwner;

    public CancelRequest() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isConfirmByOwner() {
        return isConfirmByOwner;
    }

    public void setConfirmByOwner(boolean confirmByOwner) {
        isConfirmByOwner = confirmByOwner;
    }
}