package studio.demo.model.binding;

import javax.validation.constraints.NotNull;

public class ProcedureToCartBindingModel {
    private String userId;
    private String procedureId;

    public ProcedureToCartBindingModel() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProcedureId() {
        return procedureId;
    }

    public void setProcedureId(String procedureId) {
        this.procedureId = procedureId;
    }
}
