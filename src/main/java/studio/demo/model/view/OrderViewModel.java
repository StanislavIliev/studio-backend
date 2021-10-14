package studio.demo.model.view;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@ApiModel(description = "Details about the order view model.")
public class OrderViewModel extends BaseViewModel{

    @ApiModelProperty(notes = "The products of the order.")
    private List<ProductViewModel> products;
    @ApiModelProperty(notes = "The procedures of the order.")
    private List<ProcedureViewModel> procedures;
    @ApiModelProperty(notes = "The user of the order.")
    private UserViewModel user;


    public OrderViewModel() {
    }


    public List<ProductViewModel> getProducts() {
		return products;
	}

	public void setProducts(List<ProductViewModel> products) {
		this.products = products;
	}


	public List<ProcedureViewModel> getProcedures() {
		return procedures;
	}

	public void setProcedures(List<ProcedureViewModel> procedures) {
		this.procedures = procedures;
	}

	public UserViewModel getUser() {
        return user;
    }

    public void setUser(UserViewModel user) {
        this.user = user;
    }
}

