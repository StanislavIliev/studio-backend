package studio.demo.model.entity;

import io.swagger.annotations.ApiModel;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "carts")
@ApiModel(description = "Details about the cart.")
public class Cart extends BaseEntity{

    private List<Procedure> procedures;
    private List<Product> products;

    public Cart() {
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
}
