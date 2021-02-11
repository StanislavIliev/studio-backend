package studio.demo.model.binding;

import javax.validation.constraints.NotNull;

public class ProductToCartBindingModel {
    private String userId;
    private String productId;

    public ProductToCartBindingModel() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
