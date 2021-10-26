package studio.demo.model.binding;

import io.swagger.annotations.ApiModelProperty;

public class UserUpdateModel extends BaseBindingModel {
    @ApiModelProperty(notes = "The username of the user.")
   private String username;
    @ApiModelProperty(notes = "The first name of the user.")
    private String firstName;
    @ApiModelProperty(notes = "The last name of the user.")
    private String lastName;
    @ApiModelProperty(notes = "The phone number of the user.")
    private String phoneNumber;

    public UserUpdateModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
