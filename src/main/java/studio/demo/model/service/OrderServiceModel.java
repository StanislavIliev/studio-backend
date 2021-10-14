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
import java.util.List;

import static studio.demo.contants.GlobalConstants.*;


public class OrderServiceModel extends BaseServiceModel {

    private List<ProcedureServiceModel> procedures;
    private List<ProductServiceModel> products;
    private UserServiceModel user;
    
    public OrderServiceModel() {
    }


    public List<ProcedureServiceModel> getProcedures() {
		return procedures;
	}


	public void setProcedures(List<ProcedureServiceModel> procedures) {
		this.procedures = procedures;
	}


	public List<ProductServiceModel> getProducts() {
		return products;
	}


	public void setProducts(List<ProductServiceModel> products) {
		this.products = products;
	}


	public UserServiceModel getUser() {
		return user;
	}


	public void setUser(UserServiceModel user) {
		this.user = user;
	}

}
