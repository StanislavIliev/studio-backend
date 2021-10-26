package studio.demo.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import studio.demo.exception.UserIllegalRegistrationException;
import studio.demo.exception.UserWithThisUsernameDoesNotExist;
import studio.demo.model.entity.Authority;
import studio.demo.model.entity.User;
import studio.demo.model.service.UserServiceModel;
import studio.demo.model.view.UserViewModel;
import studio.demo.repository.AuthorityRepository;
import studio.demo.repository.UserRepository;
import studio.demo.service.AuthorityService;
import studio.demo.service.UserService;

import javax.xml.bind.DataBindingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

        UserServiceModel newUser=null;
        userForDb.setUniqueString("");
        try {
            newUser = this.modelMapper.map(this.userRepository.saveAndFlush(userForDb), UserServiceModel.class);

        } catch (DataBindingException e) {
            throw new UserIllegalRegistrationException("User can not be added too db!");
        }
        return newUser;
    }

    @Override
    public UserServiceModel findByUserName(String username) throws UserWithThisUsernameDoesNotExist{

        return this.userRepository.findByUsername(username)
                .map(user -> this.modelMapper.map(user, UserServiceModel.class))
                .orElseThrow(() -> new UserWithThisUsernameDoesNotExist("Username not found!"));
    }

    @Override
    public User findByEmail(String email) throws UserWithThisUsernameDoesNotExist{

        User foundUser = this.userRepository.findByEmail(email).orElse(null);
        if (foundUser == null) throw new UserWithThisUsernameDoesNotExist("Not found!");

        return foundUser;
    }

    @Override
    public User findByPhoneNumber(String phone) throws UserWithThisUsernameDoesNotExist {

        User foundUser = this.userRepository.findByPhoneNumber(phone).orElse(null);
        if (foundUser == null) throw new UserWithThisUsernameDoesNotExist("Not found!");
        return foundUser;

    }

    @Override
    public User findByUniqueString(String uniqueString) throws UserWithThisUsernameDoesNotExist{

        return this.userRepository.findByUniqueString(uniqueString).orElse(null);

    }

    @Override
    public UserServiceModel update(UserServiceModel inputUser) throws UserWithThisUsernameDoesNotExist {

        User u = this.userRepository.findByUsername(inputUser.getUsername()).orElse(null);
        if( u == null){
            throw new UserWithThisUsernameDoesNotExist ("Not found!");
        }
        this.checkUserExist(u);
        if(!inputUser.getFirstName().trim().isEmpty()){
              u.setFirstName(inputUser.getFirstName());
        }
        if(!inputUser.getLastName().trim().isEmpty()){
             u.setLastName(inputUser.getLastName());
        }
        if(!inputUser.getPhoneNumber().trim().isEmpty()){
              u.setPhoneNumber(inputUser.getPhoneNumber());
        }

        this.userRepository.saveAndFlush(u);
        UserServiceModel updatedUser = this.modelMapper.map
                (u, UserServiceModel.class);
        return updatedUser;
    }

    @Override
    public void resetPassword(UserServiceModel user) throws UserWithThisUsernameDoesNotExist{
        String uniqueString = user.getUniqueString();
        String password = user.getPassword();
        User foundUser = this.userRepository.findByUniqueString(uniqueString).orElse(null);

        if(foundUser == null){
            throw new UserWithThisUsernameDoesNotExist("Not found");
        }
        
        foundUser.setPassword(this.bCryptPasswordEncoder.encode(password));
		foundUser.setUniqueString(" ");

        this.userRepository.saveAndFlush(foundUser);

    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User fUser = this.userRepository.findByUsername(username).orElse(null);

        if (fUser == null) throw new UsernameNotFoundException("Not found!");
        return fUser;
    }

    private void checkUserExist(User u) throws UserWithThisUsernameDoesNotExist {
        if (u == null){
            throw new UserWithThisUsernameDoesNotExist("User with this username does not exist!");
        }
    }

	@Override
	public List<UserViewModel> getAll() {
		   return this.userRepository.findAll().stream()
	                .map(user -> {
	                    UserViewModel userViewModel = this.modelMapper
	                            .map(user, UserViewModel.class);
	                    return userViewModel;
	                }).collect(Collectors.toList());
	    }

	@Override
	public User findById(String id) {
		return this.userRepository.findById(id).orElse(null);
	}

}
