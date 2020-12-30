package studio.demo.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(name = "comments")
@ApiModel(description = "Details about the comment.")
public class Comment extends BaseEntity {

    @ApiModelProperty(notes = "The topic of the comment.")
    private String topic;
    @ApiModelProperty(notes = "The description of the comment.")
    private String description;
    @ApiModelProperty(notes = "The user who wrote the comment.")
    private User user;


    public Comment() {
    }

    @Column(name = "topic")
    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    @Column(name = "description", columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @OneToOne
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
