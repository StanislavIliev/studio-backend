package studio.demo.model.binding;

import studio.demo.model.entity.Procedure;
import studio.demo.model.entity.Product;
import java.util.List;

public class OrderAddBindingModel extends BaseBindingModel{

    private List<Procedure> procedures;
    private List<Product> products;
    private UserBindingModel user;
    
    public OrderAddBindingModel() {
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

