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
import java.util.List;

import static studio.demo.contants.GlobalConstants.*;


public class OrderAddBindingModel extends BaseBindingModel{

    private LocalDateTime date;
    private List<Procedure> procedures;
    private List<Product> products;
    private UserBindingModel user;

    public OrderAddBindingModel() {
    }


    @FutureOrPresent(message = "Date can not be in the past.")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }



	public List<Procedure> getProcedures() {
		return procedures;
	}


	public void setProcedures(List<Procedure> procedures) {
		this.procedures = procedures;
	}


	public List<Product> getProducts() {
		return products;
	}


	public void setProducts(List<Product> products) {
		this.products = products;
	}


	public UserBindingModel getUser() {
        return user;
    }

    public void setUser(UserBindingModel user) {
        this.user = user;
    }
}

