package studio.demo.web;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import studio.demo.model.binding.PromotionAddBindingModel;
import studio.demo.model.entity.Promotion;
import studio.demo.model.service.PromotionServiceModel;
import studio.demo.service.PromotionService;

import javax.validation.Valid;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Controller
@RequestMapping("/promotions")
public class PromotionController {
    ConcurrentMap<String , Promotion> promotions = new ConcurrentHashMap<>();
    private final PromotionService promotionService;
    private final ModelMapper modelMapper;

    public PromotionController(PromotionService promotionService, ModelMapper modelMapper) {
        this.promotionService = promotionService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/{id}")
    @ApiOperation(value = "Find promotions by id",
    notes = "Provide id to look up for a specific promotion",
            response = Promotion.class
    )
    public  Promotion getPromotion (@ApiParam(value = "Id value for the promotion you need to retrieve"
            ,required = true)@PathVariable String id){
        return promotions.get(id);
    }


    @GetMapping("/add")
    public String add(Model model) {
        if (!model.containsAttribute("promotionAddBindingModel")) {
            model.addAttribute("promotionAddBindingModel", new PromotionAddBindingModel());
        }
        return "add-promotion";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid @ModelAttribute("promotionAddBindingModel")
                                     PromotionAddBindingModel promotionAddBindingModel,
                             BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("promotionAddBindingModel", promotionAddBindingModel);
            redirectAttributes.addFlashAttribute
                    ("org.springframework.validation.BindingResult.promotionAddBindingModel"
                            , bindingResult);
            return "redirect:add";
        }
        this.promotionService.addPromotion(this.modelMapper.map(promotionAddBindingModel,
                PromotionServiceModel.class));
        return "redirect:/";
    }

    @GetMapping("/details")
    public ModelAndView details(@RequestParam("id") String id, ModelAndView modelAndView) {
        modelAndView.addObject("promotion", this.promotionService.findById(id));
        modelAndView.setViewName("details-promotion");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") String id) {
        this.promotionService.delete(id);
        return "redirect:/";
    }

}
