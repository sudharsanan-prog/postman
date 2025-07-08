package org.example.TestNG.TestngTest.EcommerceSub;

import java.util.List;


public class OrderRequest {
    private List<OrderRequestDetails> orders;

    public List<OrderRequestDetails> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderRequestDetails> orders) {
        this.orders = orders;
    }
}
