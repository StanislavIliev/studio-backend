package studio.demo.web;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import studio.demo.model.binding.PromotionAddBindingModel;
import studio.demo.model.service.PromotionServiceModel;
import studio.demo.service.PromotionService;

import javax.validation.Valid;

@Controller
@RequestMapping("/promotions")
public class PromotionController {

    private final PromotionService promotionService;
    private final ModelMapper modelMapper;

    public PromotionController(PromotionService promotionService, ModelMapper modelMapper) {
        this.promotionService = promotionService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/add")
    public String add(Model model) {
        if(!model.containsAttribute("promotionAddBindingModel")){
            model.addAttribute("promotionAddBindingModel",new PromotionAddBindingModel());
        }
        return "add-promotion";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid @ModelAttribute("promotionAddBindingModel")
                                         PromotionAddBindingModel promotionAddBindingModel,
                             BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("promotionAddBindingModel",promotionAddBindingModel);
            redirectAttributes.addFlashAttribute
                    ("org.springframework.validation.BindingResult.promotionAddBindingModel"
                            ,bindingResult);
            return "redirect:add";
        }
        this.promotionService.addPromotion(this.modelMapper.map(promotionAddBindingModel,
                PromotionServiceModel.class));
        return "redirect:/";
    }
    @GetMapping("/details")
    public ModelAndView details(@RequestParam("id")String id, ModelAndView modelAndView){
        modelAndView.addObject("promotion",this.promotionService.findById(id));
        modelAndView.setViewName("details-promotion");
        return modelAndView;
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id")String id){
        this.promotionService.delete(id);
        return "redirect:/";
    }

}
