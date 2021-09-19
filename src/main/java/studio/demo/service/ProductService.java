package studio.demo.service;

import studio.demo.exception.ProcedureNullException;
import studio.demo.exception.ProductAlreadyExist;
import studio.demo.exception.ProductNullException;
import studio.demo.model.binding.ProductBindingModel;
import studio.demo.model.entity.Product;
import studio.demo.model.service.ProductServiceModel;
import studio.demo.model.view.ProductViewModel;
import java.util.List;

public interface ProductService {

   // void initProducts();


    Product findByName(String name);

    List<ProductViewModel> findAll();

    ProductServiceModel addProduct(ProductBindingModel product) throws ProductNullException,
            ProductAlreadyExist, ProcedureNullException;

    boolean deleteById(String id) throws ProductNullException;

    Product findProductById(String id);

    ProductServiceModel updateProduct(ProductBindingModel product) throws ProductNullException;


}
