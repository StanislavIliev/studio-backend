package studio.demo.validation;

import org.springframework.validation.Errors;
import studio.demo.model.binding.UserLoginBindingModel;
import studio.demo.model.binding.UserRegisterBindingModel;
import studio.demo.repository.UserRepository;

@Validator
public class UserLoginValidator implements org.springframework.validation.Validator {
    private final UserRepository userRepository;

    public UserLoginValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UserRegisterBindingModel.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserLoginBindingModel userLoginBindingModel = (UserLoginBindingModel) o;

        if (userLoginBindingModel.getUsername().length() > 2 && this.userRepository.findByUsername(userLoginBindingModel.getUsername()).isEmpty()) {
            errors.rejectValue("username",
                    ValidationConstants.USERNAME_LENGTH,
                    ValidationConstants.USERNAME_LENGTH);
        }

        if (userLoginBindingModel.getUsername().length() < 3 ||
                userLoginBindingModel.getUsername().length() > 10) {
            errors.rejectValue("username",
                    ValidationConstants.USERNAME_LENGTH,
                    ValidationConstants.USERNAME_LENGTH
            );
        }

        if (userLoginBindingModel.getPassword().length() < 3) {
            errors.rejectValue("username",
                    ValidationConstants.USERNAME_LENGTH,
                    ValidationConstants.USERNAME_LENGTH
            );
        }


    }
}
