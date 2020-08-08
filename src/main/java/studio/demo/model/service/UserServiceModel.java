package studio.demo.model.service;

import java.math.BigDecimal;
import java.util.Set;

public class UserServiceModel extends BaseServiceModel {

    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    //private Set<RoleServiceModel> authorities;

    public UserServiceModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


//    public Set<RoleServiceModel> getAuthorities() {
//        return authorities;
//    }
//
//    public void setAuthorities(Set<RoleServiceModel> authorities) {
//        this.authorities = authorities;
//    }
//


}

