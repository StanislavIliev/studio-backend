package studio.demo.model.view;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

@ApiModel(description = "Details about the promotion view model.")
public class PromotionViewModel extends BaseViewModel{

    @ApiModelProperty(notes = "The name of the promotion.")
    private String name;
    @ApiModelProperty(notes = "The description of the promotion.")
    private String description;
    @ApiModelProperty(notes = "The price of the promotion.")
    private BigDecimal price;
    @ApiModelProperty(notes = "The image of the promotion.")
    private String imageUrl;

    public PromotionViewModel() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
