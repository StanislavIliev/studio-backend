package studio.demo.model.service;

import studio.demo.model.entity.Manicure;
import studio.demo.model.entity.ManicureType;

import java.math.BigDecimal;

public class OrderServiceModel extends BaseServiceModel {


    private String name;
    private String description;
    private BigDecimal price;
    private ManicureServiceModel manicure;

    public OrderServiceModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ManicureServiceModel getManicure() {
        return manicure;
    }

    public void setManicure(ManicureServiceModel manicure) {
        this.manicure = manicure;
    }
}
