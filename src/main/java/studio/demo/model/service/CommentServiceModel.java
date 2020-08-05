package studio.demo.model.service;

public class CommentServiceModel extends BaseServiceModel {
    private String topic;
    private String description;

    public CommentServiceModel() {
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
}
