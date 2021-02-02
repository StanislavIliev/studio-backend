package studio.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import studio.demo.exception.UserIllegalRegistrationException;
import studio.demo.exception.UserWithThisUsernameDoesNotExist;
import studio.demo.model.entity.User;
import studio.demo.model.service.UserServiceModel;

import java.io.IOException;

public interface UserService extends UserDetailsService {

    UserServiceModel register(UserServiceModel userServiceModel) throws UserIllegalRegistrationException, IOException;

    UserServiceModel findByUserName(String username) throws UserWithThisUsernameDoesNotExist;

    User findByEmail(String email) throws UserWithThisUsernameDoesNotExist;

    User findByPhoneNumber(String phone) throws UserWithThisUsernameDoesNotExist;

    UserServiceModel update (UserServiceModel inputUser) throws UserWithThisUsernameDoesNotExist;

    void resetPassword(UserServiceModel user) throws UserWithThisUsernameDoesNotExist;

    User findByUniqueString(String uniqueString) throws UserWithThisUsernameDoesNotExist;

    void sendSimpleMessage(String to, String subject, String text);

}
