package studio.demo.model.binding;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;


public class UserBindingModel {

    private String username;
    private String email;
    private String password;
    private String phoneNumber;

    public UserBindingModel() {
    }
    //todo regex
    @Length(min = 2 ,message = "Username must be more than two characters.")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    //todo
    @Length(min = 3 ,message = "Password must be more than three characters.")

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Email(message = "Enter valid email.")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Length(min = 7, message = "Phone number must be more than 7 digits.")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

