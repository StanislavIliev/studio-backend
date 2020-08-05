package studio.demo.web;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import studio.demo.service.CommentService;
import studio.demo.service.OrderService;
import studio.demo.service.PromotionService;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
    private final OrderService orderService;
 private final PromotionService promotionService;
 private final CommentService commentService;

    public HomeController(OrderService orderService, PromotionService promotionService, CommentService commentService) {
        this.orderService = orderService;
        this.promotionService = promotionService;
        this.commentService = commentService;
    }

    @GetMapping("/")
    public ModelAndView index(HttpSession httpSession,ModelAndView modelAndView){

        if(httpSession.getAttribute("user") == null) {
            modelAndView.setViewName("index");
        }else {
            modelAndView.addObject("orders",this.orderService.findAllItems());
            modelAndView.addObject("promotions",this.promotionService.findAllItems());
            modelAndView.addObject("comment",this.commentService.findAllItems());
            modelAndView.setViewName("home");
        }
        return modelAndView;
    }


}

