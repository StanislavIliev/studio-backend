package studio.demo.model.binding;

import studio.demo.model.entity.BaseEntity;

public class UserPasswordBindingModel extends BaseBindingModel {

    private String password;
    private String usk;

    public UserPasswordBindingModel() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsk() {
        return usk;
    }

    public void setUsk(String usk) {
        this.usk = usk;
    }
}
