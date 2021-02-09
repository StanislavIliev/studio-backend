package studio.demo.service;

import studio.demo.exception.CartDoesNotExists;
import studio.demo.exception.ProcedureDoesNotExist;
import studio.demo.exception.ProductDoesNotExist;
import studio.demo.exception.UserWithThisUsernameDoesNotExist;
import studio.demo.model.binding.ProcedureToCartBindingModel;
import studio.demo.model.binding.ProductToCartBindingModel;
import studio.demo.model.service.ProcedureServiceModel;
import studio.demo.model.service.ProcedureToCartServiceModel;
import studio.demo.model.service.ProductServiceModel;
import studio.demo.model.service.ProductToCartSeviceModel;

public interface CartService {

    ProductServiceModel addProductToCart(ProductToCartBindingModel product) throws ProductDoesNotExist, UserWithThisUsernameDoesNotExist;

    boolean deleteAll(String id) throws UserWithThisUsernameDoesNotExist, CartDoesNotExists;

    boolean deleteProduct(ProductToCartSeviceModel product) throws ProductDoesNotExist, UserWithThisUsernameDoesNotExist;


    ProcedureServiceModel addProcedureToCart(ProcedureToCartBindingModel cp) throws UserWithThisUsernameDoesNotExist, ProcedureDoesNotExist;


    boolean deleteProcedure(ProcedureToCartServiceModel procedure) throws UserWithThisUsernameDoesNotExist, ProcedureDoesNotExist;

}

