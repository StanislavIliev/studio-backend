package studio.demo.model.view;

import java.math.BigDecimal;
import java.util.List;

public class CartViewModel extends BaseViewModel {
    private List<ProcedureViewModel> procedures;
    private List<ProductViewModel> products;
    private BigDecimal subtotal;


    public CartViewModel() {
    }

    public List<ProcedureViewModel> getProcedures() {
        return procedures;
    }

    public void setProcedures(List<ProcedureViewModel> procedures) {
        this.procedures = procedures;
    }

    public List<ProductViewModel> getProducts() {
        return products;
    }

    public void setProducts(List<ProductViewModel> products) {
        this.products = products;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }
}
