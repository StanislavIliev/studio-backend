package studio.demo.web;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import studio.demo.exception.*;
import studio.demo.model.binding.ItemRemoveFromCartBindingModel;
import studio.demo.model.binding.ProcedureToCartBindingModel;
import studio.demo.model.binding.ProductToCartBindingModel;
import studio.demo.model.binding.UserBindingModel;
import studio.demo.model.entity.Cart;
import studio.demo.model.service.*;
import studio.demo.model.view.CartViewModel;
import studio.demo.model.view.ProcedureViewModel;
import studio.demo.model.view.ProductViewModel;
import studio.demo.service.CartService;
import studio.demo.service.ProcedureService;
import studio.demo.service.ProductService;

import javax.validation.Valid;
import java.math.BigDecimal;

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


    @PostMapping("/deleteAll")
    public ResponseEntity<Boolean> deleteAll (@Valid @RequestBody UserBindingModel user)
            throws UserWithThisUsernameDoesNotExist, CartDoesNotExists {

        boolean isCartDeleted = this.cartService.deleteAll(user.getId());
        return new ResponseEntity<>(isCartDeleted, HttpStatus.OK);

    }

    @PostMapping(value = "/add-product", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductViewModel> addProduct
            (@RequestBody ProductToCartBindingModel product) throws ProductDoesNotExist, UserWithThisUsernameDoesNotExist {
    	System.out.println(product.getProductId());
    	System.out.println(product.getUserId());
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


    @GetMapping("/{id}")
    @ApiOperation(value = "Find cart by id",
            notes = "Provide id to look up for a specific cart", response = Cart.class)
    public  ResponseEntity<CartViewModel> getCart
            (@ApiParam(value = "Id value for the cart you need to retrieve"
            ,required = true)@PathVariable String id) throws CartNullException {

        CartViewModel cartView = this.cartService.findByUserId(id);
        return new ResponseEntity<CartViewModel>(cartView , HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity<Boolean> deleteItem (@Valid @RequestBody ItemRemoveFromCartBindingModel item) throws ProductDoesNotExist, 
    ProcedureDoesNotExist,  UserWithThisUsernameDoesNotExist {
        boolean isItemDeleted = this.cartService.deleteService(item);
        return new ResponseEntity<>(isItemDeleted, HttpStatus.OK);
    }

}
