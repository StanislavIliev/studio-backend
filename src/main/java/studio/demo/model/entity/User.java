package studio.demo.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.List;
import javax.persistence.*;


@Entity
@Table(name = "users")
@ApiModel(description = "Details about the user.")
public class User extends BaseEntity implements UserDetails {

    @ApiModelProperty(notes = "The username of the user. It must be unique.")
    private String username;
    @ApiModelProperty(notes = "The password of the user.")
    private String password;
    @ApiModelProperty(notes = "The email of the user. It must be unique.")
    private String email;
    @ApiModelProperty(notes = "The first name of the user.")
    private String firstName;
    @ApiModelProperty(notes = "The last name of the user.")
    private String lastName;
    @ApiModelProperty(notes = "The phone number of the user.")
    private String phoneNumber;
    @ApiModelProperty(notes = "The authority of the user.")
    private List<Authority> authorities;
    @ApiModelProperty(notes = "The unique string helps to change the password of the user.")
    private String uniqueString;
    @ApiModelProperty(notes = "The cart the user.")
    private Cart cart;

    public User() {
    }


    @Override
    @Transient
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @Transient
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @Transient
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @Transient
    public boolean isEnabled() {
        return true;
    }

    @Column(name = "username", unique = true, nullable = false)
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "email", nullable = false, unique = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "phone_number", unique = true)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Column(name = "first_name",nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name", nullable =  false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    public List<Authority> getAuthorities() {
        return this.authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    @Column(name = "unique_string", nullable =  false)
    public String getUniqueString() {
        return uniqueString;
    }

    public void setUniqueString(String uniqueString) {
        this.uniqueString = uniqueString;
    }

    @OneToOne
    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }    
    
    
}
