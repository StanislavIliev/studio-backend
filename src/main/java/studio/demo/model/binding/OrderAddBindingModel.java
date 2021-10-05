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
    private List<ProcedureBindingModel> procedures;
    private List<ProductBindingModel> products;
    private UserBindingModel user;

    public OrderAddBindingModel() {
    }


    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @FutureOrPresent(message = DATE_CORRECT)
    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }


    public List<ProcedureBindingModel> getProcedures() {
		return procedures;
	}


	public void setProcedures(List<ProcedureBindingModel> procedures) {
		this.procedures = procedures;
	}


	public List<ProductBindingModel> getProducts() {
		return products;
	}


	public void setProducts(List<ProductBindingModel> products) {
		this.products = products;
	}


	public UserBindingModel getUser() {
        return user;
    }

    public void setUser(UserBindingModel user) {
        this.user = user;
    }
}

