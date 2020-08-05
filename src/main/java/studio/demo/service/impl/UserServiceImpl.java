package studio.demo.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import studio.demo.model.entity.User;
import studio.demo.model.service.UserServiceModel;
import studio.demo.repository.UserRepository;
import studio.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserServiceModel register(UserServiceModel userServiceModel) {
        User user =this.modelMapper.map(userServiceModel, User.class);
        return this.modelMapper.map(this.userRepository.saveAndFlush(user),UserServiceModel.class);
    }

    @Override
    public UserServiceModel findByUserName(String username) {

        return this.userRepository.findByUsername(username)
                .map(user -> this.modelMapper.map(user,UserServiceModel.class))
                .orElse(null);
    }
}