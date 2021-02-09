package studio.demo.model.service;

import javax.validation.constraints.NotNull;

public class ProductToCartSeviceModel {
    private String username;
    private String product;

    public ProductToCartSeviceModel() {
    }

    @NotNull
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotNull
    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }
}
