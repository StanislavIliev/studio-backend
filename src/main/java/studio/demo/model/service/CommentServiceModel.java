package studio.demo.model.service;

import org.hibernate.validator.constraints.Length;
import studio.demo.model.entity.User;

import javax.validation.constraints.Pattern;

import static studio.demo.contants.GlobalConstants.*;

public class CommentServiceModel extends BaseServiceModel {
    private String topic;
    private String description;
    private UserServiceModel user;

    public CommentServiceModel() {
    }


    @Length(min = 2, message = "Topic name must be more than 2 characters.")
    @Pattern(regexp = COMMENT_REGEX , message = COMMENT_NOT_NULL)
    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    @Length(min = 7, message = COMMENT_DESCRIPTION)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserServiceModel getUser() {
        return user;
    }

    public void setUser(UserServiceModel user) {
        this.user = user;
    }
}
