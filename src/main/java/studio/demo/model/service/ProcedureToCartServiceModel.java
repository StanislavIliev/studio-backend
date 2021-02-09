package studio.demo.model.service;

import javax.validation.constraints.NotNull;

public class ProcedureToCartServiceModel {

    private String username;
    private String procedure;

    public ProcedureToCartServiceModel() {
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
