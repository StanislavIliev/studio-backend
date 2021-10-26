package studio.demo.model.binding;

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
