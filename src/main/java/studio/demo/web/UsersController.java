package studio.demo.web;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import studio.demo.exception.UserIllegalRegistrationException;
import studio.demo.model.binding.UserBindingModel;
import studio.demo.model.service.UserServiceModel;
import studio.demo.model.view.UserViewModel;
import studio.demo.service.UserService;
import studio.demo.validation.UserLoginValidator;
import studio.demo.validation.UserRegisterValidator;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;


@RestController
@RequestMapping("/users")
public class    UsersController {
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRegisterValidator userRegisterValidator;
    private final UserLoginValidator userLoginValidator;

    public UsersController(UserService userService, ModelMapper modelMapper, BCryptPasswordEncoder bCryptPasswordEncoder, UserRegisterValidator userRegisterValidator, UserLoginValidator userLoginValidator) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRegisterValidator = userRegisterValidator;
        this.userLoginValidator = userLoginValidator;
    }
//
//    @GetMapping("/login")
//    public String login(Model model) {
//
//        if (!model.containsAttribute("userBindingModel")) {
//            model.addAttribute("userBindingModel", new UserBindingModel());
//        }
//
//        return "login";
//    }
//
//    @PostMapping("/login")
//    public String loginConfirm(@Valid @ModelAttribute("userBindingModel")
//                                       UserBindingModel userBindingModel,
//                               BindingResult bindingResult, RedirectAttributes redirectAttributes,
//                               HttpSession httpSession) {
//
//        userLoginValidator.validate(userBindingModel, bindingResult);
//
//        if (bindingResult.hasErrors()) {
//            redirectAttributes.addFlashAttribute
//                    ("userBindingModel", userBindingModel);
//            redirectAttributes.addFlashAttribute
//                    ("org.springframework.validation.BindingResult.userBindingModel"
//                            , bindingResult);
//
//            return "redirect:login";
//        }
//        UserServiceModel user = this.userService.findByUserName(userBindingModel.getUsername());
//
//        if (user == null ||
//                !this.bCryptPasswordEncoder
//                        .matches(userBindingModel.getPassword(), user.getPassword())) {
//            redirectAttributes.addFlashAttribute("userBindingModel", userBindingModel);
//            redirectAttributes.addFlashAttribute("notFound", true);
//            return "redirect:login";
//        }
//
//
//        httpSession.setAttribute("user", user);
//        return "redirect:/";
//    }


    @PostMapping("/register")
    public ResponseEntity<UserViewModel> registerConfirm(@Valid @ModelAttribute("userBindingModel")
                                          UserBindingModel userBindingModel) throws UserIllegalRegistrationException, IOException {

        UserServiceModel u = this.userService.register(this.modelMapper.map(userBindingModel, UserServiceModel.class));
        UserViewModel uvm = this.modelMapper.map(u, UserViewModel.class);
        return new ResponseEntity<UserViewModel>(uvm, HttpStatus.OK);
    }


//    @GetMapping("/logout")
//    public String logout(HttpSession httpSession) {
//        httpSession.invalidate();
//        return "redirect:/";
//    }
}
