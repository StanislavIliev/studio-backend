package studio.demo.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import studio.demo.model.entity.User;
import studio.demo.model.service.UserServiceModel;
import studio.demo.repository.UserRepository;

import studio.demo.service.UserService;

import java.util.LinkedHashSet;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    // private final RoleService roleService;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper,
                           BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;

        //this.roleService = roleService;
    }

    @Override
    public boolean register(UserServiceModel userServiceModel) {

        //this.roleService.seedRolesindb();

//        if(this.userRepository.count()==0){
//
//            userServiceModel.setAuthorities(this.roleService.findAllRoles());
//
//        }else {
//            userServiceModel.setAuthorities(new LinkedHashSet<>());
//            userServiceModel.getAuthorities().add(this.roleService.findByAuthority("USER"));
//        }


        User user = this.modelMapper.map(userServiceModel, User.class);
        user.setPassword(this.bCryptPasswordEncoder.encode(userServiceModel.getPassword()));

        try {
            this.userRepository.saveAndFlush(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }


    }

    @Override
    public UserServiceModel findByUserName(String username) {

        return this.userRepository.findByUsername(username)
                .map(user -> this.modelMapper.map(user, UserServiceModel.class))
                .orElseThrow(() -> new UsernameNotFoundException("Username not found!"));
    }

    @Override
    public User findByEmail(String email) {
        return this.userRepository.findByEmail(email).orElse(null);
    }

}