package studio.demo.web;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import studio.demo.exception.ProcedureNullException;
import studio.demo.exception.ProductAlreadyExist;
import studio.demo.exception.ProductNullException;
import studio.demo.model.binding.ProductBindingModel;
import studio.demo.model.entity.Product;
import studio.demo.model.service.ProductServiceModel;
import studio.demo.model.view.ProductViewModel;
import studio.demo.service.ProductService;
import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {

    ConcurrentMap<String , Product> products = new ConcurrentHashMap<>();

    private final ProductService productService;
    private final ModelMapper modelMapper;

    public ProductController(ProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }


    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProductViewModel>> allProducts() {
        List<ProductViewModel> products = this.productService.findAll()
                .stream()
                .map(ccc -> this.modelMapper.map(ccc, ProductViewModel.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Find products by id",
            notes = "Provide id to look up for a specific product",
            response = Product.class
    )
    public  ResponseEntity<ProductViewModel> getProduct (@ApiParam(value = "Id value for the product you need to retrieve"
            ,required = true)@PathVariable String id){

        Product product = this.productService.findProductById(id);
        return new ResponseEntity<ProductViewModel>(this.modelMapper
                .map(product, ProductViewModel.class),HttpStatus.OK);
    }


    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductViewModel> update
            (@RequestBody ProductBindingModel product) throws ProductNullException {

        ProductServiceModel updatedProduct =
                this.productService.updateProduct
                        (this.modelMapper.map(product, ProductBindingModel.class));

        return new ResponseEntity<ProductViewModel>(this.modelMapper.map
                (updatedProduct, ProductViewModel.class),  HttpStatus.OK);

    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductViewModel> addProduct
            (@RequestBody ProductBindingModel product) throws ProductNullException, ProcedureNullException, ProductAlreadyExist {

        ProductServiceModel ppp = this.productService.addProduct(product);

        ProductViewModel proView = this.modelMapper.map(ppp, ProductViewModel.class);

        return new ResponseEntity<>(proView, HttpStatus.OK);
    }



    @PostMapping("/delete")
    public ResponseEntity<Boolean>
    deleteProduct (@Valid @RequestBody String id) throws ProductNullException {

        boolean isDeleted = this.productService.deleteById(id);
        return new ResponseEntity<>(isDeleted, HttpStatus.OK);
    }

}
