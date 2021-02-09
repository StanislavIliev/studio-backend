package studio.demo.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "orders")
@ApiModel(description = "Details about the order.")
public class Order extends BaseEntity {

    @ApiModelProperty(notes = "The description of the oder.")
    private String description;
    @ApiModelProperty(notes = "The procedure of the oder.")
    private Procedure procedure;
    @ApiModelProperty(notes = "The product of the oder.")
    private Product product;
    @ApiModelProperty(notes = "The date of the oder.")
    private LocalDateTime date;
    @ApiModelProperty(notes = "The person of the oder.")
    private User user;


    public Order() {
    }


    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToOne
    public Procedure getProcedure() {
        return procedure;
    }

    public void setProcedure(Procedure procedure) {
        this.procedure = procedure;
    }

    @ManyToOne
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Column(name = "date",nullable = false)
    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @ManyToOne
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}


