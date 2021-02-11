package studio.demo.web;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import studio.demo.exception.*;
import studio.demo.model.binding.ProcedureToCartBindingModel;
import studio.demo.model.binding.ProductToCartBindingModel;
import studio.demo.model.binding.PromotionAddBindingModel;
import studio.demo.model.binding.UserBindingModel;
import studio.demo.model.service.*;
import studio.demo.model.view.ProcedureViewModel;
import studio.demo.model.view.ProductViewModel;
import studio.demo.model.view.PromotionViewModel;
import studio.demo.service.CartService;
import studio.demo.service.ProcedureService;
import studio.demo.service.ProductService;

import javax.validation.Valid;

@RestController
@RequestMapping("/carts")
public class CartController {

    private final ModelMapper modelMapper;
    private final CartService cartService;
    private final ProductService productService;
    private final ProcedureService procedureService;

    @Autowired
    public CartController(ModelMapper modelMapper, CartService cartService, ProductService productService, ProcedureService procedureService) {
        this.cartService = cartService;
        this.modelMapper = modelMapper;
        this.productService = productService;
        this.procedureService = procedureService;
    }


    @DeleteMapping("/delete")
    public ResponseEntity<Boolean>
    deletePromotion (@Valid @RequestBody UserBindingModel user) throws UserWithThisUsernameDoesNotExist, CartDoesNotExists {


        boolean isCartDeleted = this.cartService.deleteAll(user.getId());
        return new ResponseEntity<>(isCartDeleted, HttpStatus.OK);
    }

    @PostMapping(value = "/add-product", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductViewModel> addProduct
            (@RequestBody ProductToCartBindingModel product) throws ProductDoesNotExist, UserWithThisUsernameDoesNotExist {

         ProductServiceModel ppp = this.cartService.addProductToCart(product);

         ProductViewModel proView = this.modelMapper.map(ppp, ProductViewModel.class);

        return new ResponseEntity<>(proView, HttpStatus.OK);
    }

    @PostMapping(value = "/add-procedure", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProcedureViewModel> addProcedure
            (@RequestBody ProcedureToCartBindingModel procedure) throws UserWithThisUsernameDoesNotExist, ProcedureDoesNotExist {

        ProcedureServiceModel ppp = this.cartService.addProcedureToCart(procedure);

        ProcedureViewModel proceView = this.modelMapper.map(ppp, ProcedureViewModel.class);

        return new ResponseEntity<>(proceView, HttpStatus.OK);
    }

    @DeleteMapping("/delete-product")
    public ResponseEntity<Boolean>
    deleteProduct (@Valid @RequestBody ProductToCartBindingModel product)
            throws ProductDoesNotExist, UserWithThisUsernameDoesNotExist {

        boolean isProductDeleted = this.cartService.deleteProduct(this.modelMapper.map
                (product,ProductToCartSeviceModel.class));
        return new ResponseEntity<>(isProductDeleted, HttpStatus.OK);
    }

    @DeleteMapping("/delete-procedure")
    public ResponseEntity<Boolean>
    deleteProcedure (@Valid @RequestBody ProcedureToCartBindingModel procedure)
            throws UserWithThisUsernameDoesNotExist, ProcedureDoesNotExist {

        boolean isProcedureDeleted = this.cartService.deleteProcedure(this.modelMapper
                .map(procedure,ProcedureToCartServiceModel.class));
        return new ResponseEntity<>(isProcedureDeleted, HttpStatus.OK);
    }
}