package studio.demo.service;

import studio.demo.model.entity.User;
import studio.demo.model.service.UserServiceModel;

public interface UserService {
    boolean register(UserServiceModel userServiceModel);

    UserServiceModel findByUserName(String username);

    User findByEmail(String email);


}
