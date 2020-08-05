package studio.demo.service;

import studio.demo.model.service.UserServiceModel;

public interface UserService {
    UserServiceModel register(UserServiceModel userServiceModel);

    UserServiceModel findByUserName(String username);
}
