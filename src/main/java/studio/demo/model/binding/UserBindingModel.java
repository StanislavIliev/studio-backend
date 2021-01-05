package studio.demo.model.binding;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

import static studio.demo.contants.GlobalConstants.*;

@ApiModel(description = "Details about the user binding model.")
public class UserBindingModel extends  BaseBindingModel{
    @ApiModelProperty(notes = "The username of the user. It must be unique.")
    private String username;
    @ApiModelProperty(notes = "The email of the user. It must be unique.")
    private String email;
    @ApiModelProperty(notes = "The password of the user.")
    private String password;
    @ApiModelProperty(notes = "The phone number of the user.")
    private String phoneNumber;

    public UserBindingModel() {
    }

    @Length(min = 2 ,message = "Username must be more than two characters.")
    @Pattern(regexp = USERNAME_REGEX , message = USERNAME_NOT_CORRECT)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Length(min = 3 ,message = "Password must be more than three characters.")
    @Pattern(regexp = PASSWORD_REGEX , message = PASSWORD_NOT_CORRECT)
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
    @Pattern(regexp = PHONE_REGEX , message = PHONE_NOT_CORRECT)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

