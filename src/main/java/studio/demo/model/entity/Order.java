package studio.demo.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "orders")
@ApiModel(description = "Details about the order.")
public class Order extends BaseEntity {

    @ApiModelProperty(notes = "The name of the oder.")
    private String name;
    @ApiModelProperty(notes = "The description of the oder.")
    private String description;
    @ApiModelProperty(notes = "The price of the oder.")
    private BigDecimal price;
    @ApiModelProperty(notes = "The manicure of the oder.")
    private Manicure manicure;
    @ApiModelProperty(notes = "The person of the oder.")
    private User user;

    public Order() {
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "price")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @ManyToOne
    public Manicure getManicure() {
        return manicure;
    }

    public void setManicure(Manicure manicure) {
        this.manicure = manicure;
    }

    @OneToOne
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}


