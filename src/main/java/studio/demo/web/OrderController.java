package studio.demo.web;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import studio.demo.model.binding.OrderAddBindingModel;
import studio.demo.model.service.OrderServiceModel;
import studio.demo.service.OrderService;

import javax.validation.Valid;

@Controller
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final ModelMapper modelMapper;

    public OrderController(OrderService orderService, ModelMapper modelMapper) {
        this.orderService = orderService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/add")
    public String add(Model model) {
        if(!model.containsAttribute("orderAddBindingModel")){
            model.addAttribute("orderAddBindingModel",new OrderAddBindingModel());
        }
        return "add-order";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid @ModelAttribute("orderAddBindingModel")
                                     OrderAddBindingModel orderAddBindingModel,
                             BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("orderAddBindingModel",orderAddBindingModel);
            redirectAttributes.addFlashAttribute
                    ("org.springframework.validation.BindingResult.orderAddBindingModel"
                            ,bindingResult);
            return "redirect:add";
        }
        this.orderService.addOrder(this.modelMapper.map(orderAddBindingModel,
                OrderServiceModel.class));
        return "redirect:/";
    }
    @GetMapping("/details")
    public ModelAndView details(@RequestParam("id")String id,ModelAndView modelAndView){
        modelAndView.addObject("order",this.orderService.findById(id));
        modelAndView.setViewName("details-order");
        return modelAndView;
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id")String id){
        this.orderService.delete(id);
        return "redirect:/";
    }
}

