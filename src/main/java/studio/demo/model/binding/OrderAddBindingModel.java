package studio.demo.model.binding;

import io.swagger.annotations.ApiModelProperty;
import org.aspectj.lang.annotation.After;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import studio.demo.model.entity.Procedure;
import studio.demo.model.entity.Product;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import static studio.demo.contants.GlobalConstants.*;


public class OrderAddBindingModel extends BaseBindingModel{

    private String description;
    private LocalDateTime date;
    private Procedure procedure;
    private Product product;
    private UserBindingModel user;

    public OrderAddBindingModel() {
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

    @NotNull
    public Procedure getProcedure() {
        return procedure;
    }


    public void setProcedure(Procedure procedure) {
        this.procedure = procedure;
    }

    @NotNull
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public UserBindingModel getUser() {
        return user;
    }

    public void setUser(UserBindingModel user) {
        this.user = user;
    }
}

