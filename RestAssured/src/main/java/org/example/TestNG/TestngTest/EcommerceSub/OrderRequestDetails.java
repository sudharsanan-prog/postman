package org.example.TestNG.TestngTest.EcommerceSub;

public class OrderRequestDetails {
    private String country;
    private String productOrderedId; // ✅ renamed to match API

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProductOrderedId() {
        return productOrderedId;
    }

    public void setProductOrderedId(String productOrderedId) {
        this.productOrderedId = productOrderedId;
    }
}
