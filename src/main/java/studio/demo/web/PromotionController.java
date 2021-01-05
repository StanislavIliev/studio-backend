package studio.demo.web;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import studio.demo.exception.*;
import studio.demo.model.binding.CommentAddBindingModel;
import studio.demo.model.binding.PromotionAddBindingModel;
import studio.demo.model.entity.Comment;
import studio.demo.model.entity.Promotion;
import studio.demo.model.service.CommentServiceModel;
import studio.demo.model.service.PromotionServiceModel;
import studio.demo.model.view.CommentViewModel;
import studio.demo.model.view.PromotionViewModel;
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


    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PromotionViewModel> update
            (@RequestBody PromotionAddBindingModel promotion) throws PromotionWithThisNameDoesNotExist {

        PromotionServiceModel updatedPromotion =
                this.promotionService.update
                        (this.modelMapper.map(promotion, PromotionAddBindingModel.class));

        return new ResponseEntity<PromotionViewModel>(this.modelMapper.map
                (updatedPromotion, PromotionViewModel.class),  HttpStatus.OK);

    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PromotionViewModel> addPromotion
            (@RequestBody PromotionAddBindingModel promotion) throws PromotiontWithThisNameExist, PromotionDoesNotExist {

        PromotionServiceModel ppp = this.promotionService.addPromotion(this.modelMapper.map
                (promotion, PromotionServiceModel.class));

        PromotionViewModel proView = this.modelMapper.map(ppp, PromotionViewModel.class);

        return new ResponseEntity<>(proView, HttpStatus.OK);
    }




    @GetMapping("/details")
    public ModelAndView details(@RequestParam("id") String id, ModelAndView modelAndView) {
        modelAndView.addObject("promotion", this.promotionService.findById(id));
        modelAndView.setViewName("details-promotion");
        return modelAndView;
    }


    @DeleteMapping("/delete")
    public ResponseEntity<Boolean>
    deletePromotion (@Valid @RequestBody PromotionAddBindingModel promotion) throws PromotionWithThisIdDoesNotExist {

        Promotion p = this.modelMapper.map(promotion,Promotion.class);
        boolean isPromotionDeleted = this.promotionService.delete(p.getId());
        return new ResponseEntity<>(isPromotionDeleted, HttpStatus.OK);
    }

}
