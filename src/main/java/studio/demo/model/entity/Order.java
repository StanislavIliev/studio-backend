package studio.demo.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

 
@Entity
@Table(name = "orders")
@ApiModel(description = "Details about the order.")
public class Order extends BaseEntity {

    @ApiModelProperty(notes = "The procedures of the oder.")
    private List<Procedure> procedures;
    @ApiModelProperty(notes = "The products of the oder.")
    private List<Product> products;
    @ApiModelProperty(notes = "The date of the oder.")
    private LocalDateTime date;
    @ApiModelProperty(notes = "The person of the oder.")
    private User user;


    public Order() {
    }

    @ManyToMany
    public List<Procedure> getProcedures() {
		return procedures;
	}


	public void setProcedures(List<Procedure> procedures) {
		this.procedures = procedures;
	}

	@ManyToMany
	public List<Product> getProducts() {
		return products;
	}


	public void setProducts(List<Product> products) {
		this.products = products;
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


