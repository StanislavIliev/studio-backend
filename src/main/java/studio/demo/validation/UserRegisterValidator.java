package studio.demo.validation;

import org.springframework.validation.Errors;
import studio.demo.model.binding.UserBindingModel;
import studio.demo.repository.UserRepository;

@Validator
public class UserRegisterValidator implements org.springframework.validation.Validator {

    private final UserRepository userRepository;

    public UserRegisterValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UserBindingModel.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserBindingModel userBindingModel = (UserBindingModel) o;

        if (this.userRepository.findByUsername(userBindingModel.getUsername()).isPresent()) {
            errors.rejectValue("username",
                    String.format(ValidationConstants.USERNAME_ALREADY_EXISTS, userBindingModel.getUsername()),
                    String.format(ValidationConstants.USERNAME_ALREADY_EXISTS, userBindingModel.getUsername())
            );
        }


        if (this.userRepository.findByPhoneNumber(userBindingModel.getPhoneNumber()).isPresent()) {
            errors.rejectValue("phoneNumber",
                    String.format(ValidationConstants.USERNAME_WITH_PHONE_ALREADY_EXISTS,
                            userBindingModel.getPhoneNumber()),
                    String.format(ValidationConstants.USERNAME_WITH_PHONE_ALREADY_EXISTS,
                            userBindingModel.getPhoneNumber())
            );
        }

        if (userBindingModel.getUsername().length() < 3 ||
                userBindingModel.getUsername().length() > 10) {
            errors.rejectValue("username",
                    ValidationConstants.USERNAME_LENGTH,
                    ValidationConstants.USERNAME_LENGTH
            );
        }

        if (this.userRepository.findByEmail(userBindingModel.getEmail()).isPresent()) {
            errors.rejectValue("email",
                    String.format(ValidationConstants.EMAIL_ALREADY_EXISTS, userBindingModel.getEmail()),
                    String.format(ValidationConstants.EMAIL_ALREADY_EXISTS, userBindingModel.getEmail())
            );
        }
        if (userBindingModel.getEmail().isEmpty()) {
            errors.rejectValue("email",
                    ValidationConstants.EMAIL_NULL,
                    ValidationConstants.EMAIL_NULL
            );
        }
        if (userBindingModel.getPassword().isEmpty()) {
            errors.rejectValue("password",
                    ValidationConstants.EMPTY_PASSWORD,
                    ValidationConstants.EMPTY_PASSWORD
            );
        }


    }
}
