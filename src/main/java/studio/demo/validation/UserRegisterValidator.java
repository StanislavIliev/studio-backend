package studio.demo.validation;

import org.springframework.validation.Errors;
import studio.demo.model.binding.UserRegisterBindingModel;
import studio.demo.repository.UserRepository;

@Validator
public class UserRegisterValidator implements org.springframework.validation.Validator {

    private final UserRepository userRepository;

    public UserRegisterValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UserRegisterBindingModel.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserRegisterBindingModel userRegisterBindingModel = (UserRegisterBindingModel) o;

        if (this.userRepository.findByUsername(userRegisterBindingModel.getUsername()).isPresent()) {
            errors.rejectValue("username",
                    String.format(ValidationConstants.USERNAME_ALREADY_EXISTS, userRegisterBindingModel.getUsername()),
                    String.format(ValidationConstants.USERNAME_ALREADY_EXISTS, userRegisterBindingModel.getUsername())
            );
        }

        if (userRegisterBindingModel.getUsername().length() < 3 ||
                userRegisterBindingModel.getUsername().length() > 10) {
            errors.rejectValue("username",
                    ValidationConstants.USERNAME_LENGTH,
                    ValidationConstants.USERNAME_LENGTH
            );
        }
        if (!userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {
            errors.rejectValue("password",
                    ValidationConstants.PASSWORD_DO_NOT_MATCH,
                    ValidationConstants.PASSWORD_DO_NOT_MATCH
            );
        }
        if (this.userRepository.findByEmail(userRegisterBindingModel.getEmail()).isPresent()) {
            errors.rejectValue("email",
                    String.format(ValidationConstants.EMAIL_ALREADY_EXISTS, userRegisterBindingModel.getEmail()),
                    String.format(ValidationConstants.EMAIL_ALREADY_EXISTS, userRegisterBindingModel.getEmail())
            );
        }
        if (userRegisterBindingModel.getEmail().isEmpty()) {
            errors.rejectValue("email",
                    String.format(ValidationConstants.EMAIL_NULL, userRegisterBindingModel.getEmail()),
                    String.format(ValidationConstants.EMAIL_NULL, userRegisterBindingModel.getEmail())
            );
        }


    }
}
