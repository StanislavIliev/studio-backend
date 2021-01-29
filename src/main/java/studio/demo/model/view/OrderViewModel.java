package studio.demo.model.view;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

@ApiModel(description = "Details about the order view model.")
public class OrderViewModel extends BaseViewModel{

    @ApiModelProperty(notes = "The name of the order.")
    private String name;
    @ApiModelProperty(notes = "The description of the order.")
    private String description;
    @ApiModelProperty(notes = "The price of the order.")
    private BigDecimal price;
    @ApiModelProperty(notes = "The image of the order.")
    private String imgUrl;
    @ApiModelProperty(notes = "The user of the order.")
    private UserViewModel user;


    public OrderViewModel() {
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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public UserViewModel getUser() {
        return user;
    }

    public void setUser(UserViewModel user) {
        this.user = user;
    }
}

