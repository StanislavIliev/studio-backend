package studio.demo.model.service;

import studio.demo.model.entity.ManicureType;

public class ManicureServiceModel extends BaseServiceModel {
    private ManicureType manicureType;
    private String description;

    public ManicureServiceModel() {
    }

    public ManicureType getManicureType() {
        return manicureType;
    }

    public void setManicureType(ManicureType manicureType) {
        this.manicureType = manicureType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
