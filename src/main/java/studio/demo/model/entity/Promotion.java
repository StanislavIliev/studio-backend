package studio.demo.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "promotions")
@ApiModel(description = "Details about the promotion.")
public class Promotion extends BaseEntity {
    @ApiModelProperty(notes = "The name of the promotion.")
    private String name;
    @ApiModelProperty(notes = "The description of the promotion.")
    private String description;
    @ApiModelProperty(notes = "The price of the promotion.")
    private BigDecimal price;

    public Promotion() {
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
}
