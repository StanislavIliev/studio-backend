package studio.demo.model.binding;


public class UserPasswordBindingModel extends BaseBindingModel {

    private String password;
    private String uniqueString;

    public UserPasswordBindingModel() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUniqueString() {
        return uniqueString;
    }

    public void setUniqueString(String uniqueString) {
        this.uniqueString = uniqueString;
    }
}
