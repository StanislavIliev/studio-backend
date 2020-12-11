package studio.demo.service;

import studio.demo.exception.UserIllegalRegistrationException;
import studio.demo.model.entity.User;
import studio.demo.model.service.UserServiceModel;

import java.io.IOException;

public interface UserService {
    UserServiceModel register(UserServiceModel userServiceModel) throws UserIllegalRegistrationException, IOException;

    UserServiceModel findByUserName(String username);

    User findByEmail(String email);
    User findByPhoneNumber(String phone);

    UserServiceModel update (UserServiceModel user);
}
