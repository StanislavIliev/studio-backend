package studio.demo.model.service;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import studio.demo.model.binding.UserBindingModel;
import studio.demo.model.entity.Procedure;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static studio.demo.contants.GlobalConstants.*;


public class OrderServiceModel extends BaseServiceModel {

    private String description;
    private LocalDateTime date;
    private ProcedureServiceModel procedure;
    private ProductServiceModel product;
    private UserServiceModel user;

    public OrderServiceModel() {
    }


    @Length(min = 3, message = "Description name must be more than 3 characters.")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @FutureOrPresent(message = DATE_CORRECT)
    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public ProcedureServiceModel getProcedure() {
        return procedure;
    }

    public void setProcedure(ProcedureServiceModel procedure) {
        this.procedure = procedure;
    }

    public ProductServiceModel getProduct() {
        return product;
    }

    public void setProduct(ProductServiceModel product) {
        this.product = product;
    }

    public UserServiceModel getUser() {
        return user;
    }

    public void setUser(UserServiceModel user) {
        this.user = user;
    }
}
