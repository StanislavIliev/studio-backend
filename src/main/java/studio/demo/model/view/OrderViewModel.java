package studio.demo.model.view;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@ApiModel(description = "Details about the order view model.")
public class OrderViewModel extends BaseViewModel{

    @ApiModelProperty(notes = "The description of the order.")
    private String description;
    @ApiModelProperty(notes = "The image of the order.")
    private String imgUrl;
    @ApiModelProperty(notes = "The date of the oder.")
    private LocalDateTime date;
    @ApiModelProperty(notes = "The user of the order.")
    private UserViewModel user;


    public OrderViewModel() {
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
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

