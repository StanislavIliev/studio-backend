package studio.demo.model.view;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

public class ProductViewModel extends BaseViewModel {

    private String name;
    private BigDecimal price;
    private String description;
    @ApiModelProperty(notes = "The image of the order.")
    private String imgUrl;

    public ProductViewModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
