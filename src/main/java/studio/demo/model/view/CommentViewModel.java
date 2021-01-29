package studio.demo.model.view;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Details about the comment view model.")
public class CommentViewModel extends BaseViewModel {

    @ApiModelProperty(notes = "The topic of the comment.")
    private String topic;
    @ApiModelProperty(notes = "The description of the comment.")
    private String description;
    @ApiModelProperty(notes = "The user of the comment.")
    private UserViewModel user;
    @ApiModelProperty(notes = "The image of the comment.")
    private String imgUrl;

    public CommentViewModel() {
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
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


    public UserViewModel getUser() {
        return user;
    }

    public void setUser(UserViewModel user) {
        this.user = user;
    }
}
