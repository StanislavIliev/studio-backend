package studio.demo.model.binding;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;

import static studio.demo.contants.GlobalConstants.*;

public class CommentAddBindingModel {
    private String topic;
    private String description;
    private UserBindingModel user;

    public CommentAddBindingModel() {
    }

   // @Length(min = 2, message = "Topic name must be more than 2 characters.")
 //   @Pattern(regexp = COMMENT_REGEX , message = COMMENT_NOT_NULL)
    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    //@Length(min = 7, message = COMMENT_DESCRIPTION)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserBindingModel getUser() {
        return user;
    }

    public void setUser(UserBindingModel user) {
        this.user = user;
    }
}
