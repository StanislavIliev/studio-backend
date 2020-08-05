package studio.demo.web;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import studio.demo.model.binding.CommentAddBindingModel;
import studio.demo.model.service.CommentServiceModel;
import studio.demo.service.CommentService;

import javax.validation.Valid;

@Controller
@RequestMapping("/comments")
public class CommentController {
    private final ModelMapper modelMapper;
    private final CommentService commentService;

    public CommentController(ModelMapper modelMapper, CommentService commentService) {
        this.modelMapper = modelMapper;
        this.commentService = commentService;
    }

    @GetMapping("/add")
    public String add(Model model) {
        if(!model.containsAttribute("commentAddBindingModel")){
            model.addAttribute("commentAddBindingModel",new CommentAddBindingModel());
        }
        return "add-comment";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid @ModelAttribute("commentAddBindingModel")
                                         CommentAddBindingModel commentAddBindingModel,
                             BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("commentAddBindingModel",commentAddBindingModel);
            redirectAttributes.addFlashAttribute
                    ("org.springframework.validation.BindingResult.commentAddBindingModel"
                            ,bindingResult);
            return "redirect:add";
        }
        this.commentService.addComment(this.modelMapper.map(commentAddBindingModel,
                CommentServiceModel.class));
        return "redirect:/";
    }
    @GetMapping("/details")
    public ModelAndView details(@RequestParam("id")String id, ModelAndView modelAndView){
        modelAndView.addObject("comment",this.commentService.findById(id));
        modelAndView.setViewName("details-comment");
        return modelAndView;
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id")String id){
        this.commentService.delete(id);
        return "redirect:/";
    }

}
