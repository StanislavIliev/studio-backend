package studio.demo.service;

import studio.demo.exception.*;
import studio.demo.model.binding.ItemRemoveFromCartBindingModel;
import studio.demo.model.binding.ProcedureToCartBindingModel;
import studio.demo.model.binding.ProductToCartBindingModel;
import studio.demo.model.service.ProcedureServiceModel;
import studio.demo.model.service.ProcedureToCartServiceModel;
import studio.demo.model.service.ProductServiceModel;
import studio.demo.model.service.ProductToCartSeviceModel;
import studio.demo.model.view.CartViewModel;



public interface CartService {

    ProductServiceModel addProductToCart(ProductToCartBindingModel product) throws ProductDoesNotExist, UserWithThisUsernameDoesNotExist;

    boolean deleteAll(String id) throws UserWithThisUsernameDoesNotExist, CartDoesNotExists;

    ProcedureServiceModel addProcedureToCart(ProcedureToCartBindingModel cp) throws UserWithThisUsernameDoesNotExist, ProcedureDoesNotExist;

    CartViewModel findByUserId (String id) throws CartNullException;

    boolean deleteService(ItemRemoveFromCartBindingModel item)
            throws UserWithThisUsernameDoesNotExist, ProcedureDoesNotExist,ProductDoesNotExist;

}

