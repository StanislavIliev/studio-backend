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
    @ApiModelProperty(notes = "The first name of the user.")
    private String firstName;
    @ApiModelProperty(notes = "The last name of the user.")
    private String lastName;
    @ApiModelProperty(notes = "The phone number of the user.")
    private String phoneNumber;
    @ApiModelProperty(notes = "The token helps to change the password of the user.")
    private String token;

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

    @Length(min = 2 ,message = "First name must be more than two characters.")
    @Pattern(regexp = FIRST_NAME_REGEX , message = FIRST_NAME_NOT_CORRECT)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Length(min = 2 ,message = "Last name must be more than two characters.")
    @Pattern(regexp = LAST_NAME_REGEX , message = LAST_NAME_NOT_CORRECT)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Length(min = 7, message = "Phone number must be more than 7 digits.")
    @Pattern(regexp = PHONE_REGEX , message = PHONE_NOT_CORRECT)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

