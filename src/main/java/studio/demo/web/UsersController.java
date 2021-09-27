package studio.demo.web;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import studio.demo.exception.UserIllegalRegistrationException;
import studio.demo.exception.UserWithThisUsernameDoesNotExist;
import studio.demo.jwt.JWTTokenProvider;
import studio.demo.model.binding.UserBindingModel;
import studio.demo.model.binding.UserPasswordBindingModel;
import studio.demo.model.binding.UserUpdateModel;
import studio.demo.model.entity.User;
import studio.demo.model.service.UserServiceModel;
import studio.demo.model.view.UserViewModel;
import studio.demo.service.UserService;
import studio.demo.service.impl.EmailServiceImpl;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

import org.springframework.security.authentication.AuthenticationManager;

import static studio.demo.contants.SecurityConstant.JWT_TOKEN_HEADER;

@RestController
@RequestMapping("/users")
public class    UsersController {
    ConcurrentMap<String , User> users = new ConcurrentHashMap<>();

    private final EmailServiceImpl emailService;
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final AuthenticationManager authenticationManager;
    private final JWTTokenProvider jwtTokenProvider;

    public UsersController(EmailServiceImpl emailService, UserService userService,
    		ModelMapper modelMapper, AuthenticationManager authenticationManager, JWTTokenProvider jwtTokenProvider) {
        this.emailService = emailService;
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }


    @GetMapping("/{id}")
    @ApiOperation(value = "Find users by id",
            notes = "Provide id to look up for a specific user",
            response = User.class
    )
    public ResponseEntity<User> getUser (@ApiParam(value = "Id value for the user you need to retrieve"
            ,required = true)@PathVariable String id){
    	User user = this.userService.findById(id);
        return new ResponseEntity<User>(user, HttpStatus.OK);  
        }


    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserViewModel> login(@RequestBody UserBindingModel user) throws UserWithThisUsernameDoesNotExist {

        this.authenticate(user.getUsername(), user.getPassword());
        UserServiceModel rUser = this.userService.findByUserName(user.getUsername());
        HttpHeaders jwtHeader = getJwtHeader(rUser);
        return new ResponseEntity<>(this.modelMapper.map(rUser, UserViewModel.class), jwtHeader, HttpStatus.OK);
    }

    
    @PostMapping(value = "/getUser", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<UserViewModel> getUserbyUsername(@RequestBody String username) throws UserWithThisUsernameDoesNotExist {

        UserServiceModel rUser = this.userService.findByUserName(username);
        HttpHeaders jwtHeader = getJwtHeader(rUser);
        return new ResponseEntity<>(this.modelMapper.map(rUser, UserViewModel.class), jwtHeader, HttpStatus.OK);
    }
    
    
    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserViewModel> update(@RequestBody
                                                        UserUpdateModel user) throws UserWithThisUsernameDoesNotExist {

        UserServiceModel updatedUser = this.userService.update(this.modelMapper.map(user,
                UserServiceModel.class));
        HttpHeaders jwtHeader = getJwtHeader(updatedUser);

        return new ResponseEntity<>(this.modelMapper.map(updatedUser, UserViewModel.class)
                , jwtHeader, HttpStatus.OK);
    }

    
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserViewModel>> allUsers() {
        List<UserViewModel> users = this.userService.getAll()
                .stream()
                .map(ccc -> this.modelMapper.map(ccc, UserViewModel.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    
    @PostMapping("/register")
    public ResponseEntity<UserViewModel> registerConfirm(@Valid @RequestBody
                                          UserBindingModel userBindingModel) throws UserIllegalRegistrationException, IOException {

        UserServiceModel u = this.userService.register(this.modelMapper.map(userBindingModel, UserServiceModel.class));
        UserViewModel uvm = this.modelMapper.map(u, UserViewModel.class);
        return new ResponseEntity<UserViewModel>(uvm, HttpStatus.OK);
    }

    @PostMapping("/req-reset-password")
    public void sendEmail(@Valid @RequestBody UserServiceModel userEm){

        this.emailService.sendSimpleMessage(userEm.getEmail(),"Request Reset Password", "Welcome");

    }

    @PostMapping("/reset-password")
    public void resetPassword(@Valid @RequestBody UserPasswordBindingModel user) throws UserWithThisUsernameDoesNotExist {
 
    	UserServiceModel userF =this.modelMapper.map(user, UserServiceModel.class);
            this.userService.resetPassword(userF);

    }
    //todo ModelAtribute or RequestParam


    private void authenticate(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }
    
    private HttpHeaders getJwtHeader(UserServiceModel user) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(JWT_TOKEN_HEADER, jwtTokenProvider.generateJwtToken(user));
        return headers;
    }
}
