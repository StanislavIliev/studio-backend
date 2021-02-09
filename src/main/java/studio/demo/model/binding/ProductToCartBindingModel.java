package studio.demo.model.binding;

import javax.validation.constraints.NotNull;

public class ProductToCartBindingModel {
    private String username;
    private String product;

    public ProductToCartBindingModel() {
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
