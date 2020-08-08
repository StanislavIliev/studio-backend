package studio.demo.model.binding;

import org.hibernate.validator.constraints.Length;

public class CommentAddBindingModel {
    private String topic;
    private String description;

    public CommentAddBindingModel() {
    }

    @Length(min = 2, message = "Topic name must be more than 2 characters.")
    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    @Length(min = 7, message = "Order name name must be more than 7 characters.")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
