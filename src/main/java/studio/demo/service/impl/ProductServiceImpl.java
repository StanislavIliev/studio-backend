package studio.demo.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import studio.demo.enums.DefaultProducts;
import studio.demo.exception.ProcedureAlreadyExist;
import studio.demo.exception.ProcedureNullException;
import studio.demo.exception.ProductAlreadyExist;
import studio.demo.exception.ProductNullException;
import studio.demo.model.binding.ProductBindingModel;
import studio.demo.model.entity.Procedure;
import studio.demo.model.entity.Product;
import studio.demo.model.service.ProcedureServiceModel;
import studio.demo.model.service.ProductServiceModel;
import studio.demo.model.view.ProcedureViewModel;
import studio.demo.model.view.ProductViewModel;
import studio.demo.repository.ProductRepository;
import studio.demo.service.ProductService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

//
//    @Override
//    public void initProducts() {
//        if (this.productRepository.count() == 0) {
//            for (DefaultProducts p : DefaultProducts.values()) {
//                Product newProduct = new Product();
//                this.productRepository.saveAndFlush(newProduct);
//            }
//        }
//    }

    @Override
    public Product findByName(String name) {
        return this.productRepository.findByName(name);
    }

    @Override
    public List<ProductViewModel> findAll() {

        return this.productRepository.findAll().stream()
                .map(product -> {
                    ProductViewModel productViewModel = this.modelMapper
                            .map(product, ProductViewModel.class);
                    productViewModel.setImgUrl(String.format("/img/%s-%s-%s .jpg"
                            , product.getName(),
                            product.getDescription(),
                            product.getPrice()));
                    return productViewModel;
                }).collect(Collectors.toList());
    }

    @Override
    public ProductServiceModel addProduct(ProductBindingModel product) throws ProductNullException, ProductAlreadyExist, ProcedureNullException {

        if (productRepository.findById(product.getName()).isPresent()) {
            throw new ProductAlreadyExist("Product with this name exists!");
        }

        Product p = new Product();

        p.setDescription(product.getDescription());
        p.setPrice(product.getPrice());
        p.setName(product.getName());
        this.productRepository.saveAndFlush(p);

        return this.modelMapper.map(p, ProductServiceModel.class);
    }

    @Override
    public boolean deleteById(String id) throws ProductNullException {

        if (this.productRepository.findById(id).isEmpty()) {
            throw new ProductNullException("Product does not exist.");
        }
        this.productRepository.deleteById(id);
        return true;
    }

    @Override
    public Product findProductById(String id) {
        return this.productRepository.findById(id).orElse(null);
    }

    @Override
    public ProductServiceModel updateProduct(ProductBindingModel product) throws ProductNullException {

        Product updatedProduct = this.productRepository.
                findById(product.getId()).orElse(null);

        this.checkProductExist(updatedProduct);

        if (!product.getDescription().trim().isEmpty()) {
            updatedProduct.setDescription(product.getDescription());
        }

        if (product.getPrice()!=null) {
            updatedProduct.setPrice(product.getPrice());
        }

        if (product.getName()!=null) {
            updatedProduct.setName(product.getName());
        }
        return this.modelMapper.map(this.productRepository.saveAndFlush(updatedProduct),
                ProductServiceModel.class);

    }


    private void checkProductExist(Product product) throws ProductNullException {
        if (product == null) {
            throw new ProductNullException("Procedure with this name does not exist!");
        }
    }

}