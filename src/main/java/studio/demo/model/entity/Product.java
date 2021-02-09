package studio.demo.model.entity;

import io.swagger.annotations.ApiModel;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
@ApiModel(description = "Details about the order.")
public class Product extends Service {

    public Product() {
    }

    public Product(String name, String description, BigDecimal price) {
        super(name, description, price);
    }

    @Override
    public String toString() {
        return String.format("Name: %s, Description: %s, Price: %s%n",
                this.getName(), this.getDescription(), this.getPrice());
    }
}
