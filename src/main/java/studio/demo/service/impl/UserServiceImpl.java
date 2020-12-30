package studio.demo.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import studio.demo.exception.UserIllegalRegistrationException;
import studio.demo.model.entity.Authority;
import studio.demo.model.entity.User;
import studio.demo.model.service.UserServiceModel;
import studio.demo.repository.AuthorityRepository;
import studio.demo.repository.UserRepository;
import studio.demo.service.AuthorityService;
import studio.demo.service.UserService;

import javax.xml.bind.DataBindingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthorityService authorityService;
    private final AuthorityRepository authorityRepository;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper,
                           BCryptPasswordEncoder bCryptPasswordEncoder,
                           AuthorityService authorityService, AuthorityRepository authorityRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authorityService = authorityService;
        this.authorityRepository = authorityRepository;
    }

    @Override
    public UserServiceModel register(UserServiceModel user) throws UserIllegalRegistrationException, IOException {
        User userForDb = this.modelMapper.map(user, User.class);
        userForDb.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
        User userInDb = this.userRepository.findByUsername(user.getUsername()).orElse(null);
        if (userInDb != null) {
            throw new UserIllegalRegistrationException(String.format("This name %s already exists!" +
                    "", user.getUsername()));
        }
        List<Authority> authorities = new ArrayList<>();
        if (this.userRepository.count() == 0) {
            List<Authority> aaaaa = this.authorityRepository.findAll();
            userForDb.setAuthorities(aaaaa);
        } else {
            Authority authority = null;
            if (this.authorityService.findByAuthority("USER") != null) {
                authority = this.authorityRepository.findByAuthority("USER");
            }
            authorities.add(authority);
            userForDb.setAuthorities(authorities);
        }

        UserServiceModel newUser;
        try {
            newUser = this.modelMapper.map(this.userRepository.saveAndFlush(userForDb), UserServiceModel.class);
        } catch (DataBindingException e) {
            throw new UserIllegalRegistrationException("User can not be added too db!");
        }
        return newUser;
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

    @Override
    public User findByPhoneNumber(String phone) {

        User foundUser = this.userRepository.findByPhoneNumber(phone).orElse(null);
        if (foundUser == null) throw new UsernameNotFoundException("Not found!");
        return foundUser;

    }

    @Override
    public UserServiceModel update(UserServiceModel user) {

        UserServiceModel usm = this.modelMapper.map(this.userRepository
                .findByUsername(user.getUsername()),UserServiceModel.class);

        usm.setPassword(user.getPassword());
        usm.setPhoneNumber(user.getPhoneNumber());
        return usm;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User fUser = this.userRepository.findByUsername(username).orElse(null);

        if (fUser == null) throw new UsernameNotFoundException("Not found!");
        return fUser;
    }
}