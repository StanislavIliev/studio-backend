package studio.demo.model.service;

import java.util.List;

public class CartServiceModel extends BaseServiceModel{

    private List<ProcedureServiceModel> procedures;
    private List<ProductServiceModel> products;

    public CartServiceModel() {
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


}
