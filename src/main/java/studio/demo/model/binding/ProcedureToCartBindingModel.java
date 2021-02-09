package studio.demo.model.binding;

import javax.validation.constraints.NotNull;

public class ProcedureToCartBindingModel {
    private String username;
    private String procedure;

    public ProcedureToCartBindingModel() {
    }

    @NotNull
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotNull
    public String getProcedure() {
        return procedure;
    }

    public void setProcedure(String procedure) {
        this.procedure = procedure;
    }
}
