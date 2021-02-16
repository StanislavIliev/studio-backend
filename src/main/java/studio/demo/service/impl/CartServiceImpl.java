package studio.demo.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import studio.demo.exception.*;
import studio.demo.model.binding.ItemRemoveFromCartBindingModel;
import studio.demo.model.binding.ProcedureToCartBindingModel;
import studio.demo.model.binding.ProductToCartBindingModel;
import studio.demo.model.binding.UserBindingModel;
import studio.demo.model.entity.Cart;
import studio.demo.model.entity.Procedure;
import studio.demo.model.entity.Product;
import studio.demo.model.entity.User;
import studio.demo.model.service.ProcedureServiceModel;
import studio.demo.model.service.ProcedureToCartServiceModel;
import studio.demo.model.service.ProductServiceModel;
import studio.demo.model.service.ProductToCartSeviceModel;
import studio.demo.model.view.CartViewModel;
import studio.demo.repository.CartRepository;
import studio.demo.repository.ProcedureRepository;
import studio.demo.repository.ProductRepository;
import studio.demo.repository.UserRepository;
import studio.demo.service.CartService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    private final ModelMapper modelMapper;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final ProcedureRepository procedureRepository;
    private final UserRepository userRepository;

    public CartServiceImpl(ModelMapper modelMapper, CartRepository cartRepository, ProductRepository productRepository, ProcedureRepository procedureRepository, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.procedureRepository = procedureRepository;
        this.userRepository = userRepository;
    }


    @Override
    @Transactional
    public ProductServiceModel addProductToCart(ProductToCartBindingModel ppp) throws ProductDoesNotExist, UserWithThisUsernameDoesNotExist {
        System.out.println();
        User user = this.userRepository.findById(ppp.getUserId()).orElse(null);
        Product product = this.productRepository.findById(ppp.getProductId()).orElse(null);
        checkUserProductNull(user, product);

        if(user.getCart()  == null){
            Cart savedCart = this.cartRepository.saveAndFlush(new Cart());
            savedCart.setProducts(new ArrayList<>());
            savedCart.setProcedures(new ArrayList<>());
            user.setCart(savedCart);
        }

        user.getCart().getProducts().add(product);
        this.userRepository.saveAndFlush(user);
        return this.modelMapper.map(product, ProductServiceModel.class);
    }

    @Override
    @Transactional
    public boolean deleteAll(String id) throws UserWithThisUsernameDoesNotExist, CartDoesNotExists {

        User user = this.userRepository.findById(id).orElse(null);

        if (user == null){
            throw new UserWithThisUsernameDoesNotExist("User with this name is not exist!");
        }
        if (user.getCart() == null){
            throw new CartDoesNotExists("The user has not a cart!");
        }
        user.getCart().getProducts().clear();
        user.getCart().getProcedures().clear();
        return true;
    }


    @Override
    @Transactional
    public ProcedureServiceModel addProcedureToCart(ProcedureToCartBindingModel pr) throws UserWithThisUsernameDoesNotExist, ProcedureDoesNotExist {
        User user = this.userRepository.findById(pr.getUserId()).orElse(null);
        Procedure procedure = this.procedureRepository.findById(pr.getProcedureId()).orElse(null);
        checkUserProcedureNotNull(user, procedure);

        if(user.getCart() == null){
            Cart savedCart = this.cartRepository.saveAndFlush(new Cart());
            savedCart.setProducts(new ArrayList<>());
            savedCart.setProcedures(new ArrayList<>());
            user.setCart(savedCart);
        }

        user.getCart().getProcedures().add(procedure);
        this.userRepository.saveAndFlush(user);

        return this.modelMapper.map(procedure, ProcedureServiceModel.class);
    }

    @Override
    @Transactional
    public CartViewModel findByUserId(String id) throws CartNullException {
        Cart cart;
        User user;

        user = this.userRepository.findById(id).orElse(null);
        if(user == null ){
            throw new CartNullException("Cart can not be null.");
        }
        cart = user.getCart();

        return this.modelMapper.map(cart,CartViewModel.class);
    }

    @Override
    @Transactional
    public boolean deleteService(ItemRemoveFromCartBindingModel item) throws UserWithThisUsernameDoesNotExist, ProcedureDoesNotExist, ProductDoesNotExist {
        User user = this.userRepository.findById(item.getUserId()).orElse(null);
        Product ppp = this.productRepository.findById(item.getItemId()).orElse(null);
        if(ppp != null) {
            checkUserProductNull(user, ppp);
            user.getCart().getProducts().remove(ppp);
            return true;
        }else {
            Procedure procedure = this.procedureRepository.findById(item.getItemId())
                    .orElse(null);
            if(procedure != null){
            checkUserProcedureNotNull(user, procedure);
            user.getCart().getProcedures().remove(procedure);
            return true;
            }
            else {
                return false;
            }
        }
    }

    @Override
    @Transactional
    public CartViewModel subtotal(UserBindingModel user111) throws CartNullException {
        Cart cart;
        User user;

        user = this.userRepository.findById(user111.getId()).orElse(null);
        if(user == null ){
            throw new CartNullException("Cart can not be null.");
        }
        cart = user.getCart();
        BigDecimal sum =  BigDecimal.ZERO;
        for (int i = 0; i <  cart.getProducts().size() ; i++) {
            sum =sum.add(cart.getProducts().get(i).getPrice());
        }
        for (int i = 0; i <  cart.getProcedures().size() ; i++) {
            sum = sum.add(cart.getProcedures().get(i).getPrice());
        }
        CartViewModel cartView = this.modelMapper.map(cart , CartViewModel.class);
        cartView.setSubtotal(sum);
        return cartView;
    }


    private void checkUserProductNull(User user, Product product) throws ProductDoesNotExist, UserWithThisUsernameDoesNotExist {
        if (product == null){
            throw new ProductDoesNotExist("Product does not exist!");
        }
        if (user == null){
            throw new UserWithThisUsernameDoesNotExist("User does not exist!");
        }
    }

    private void checkUserProcedureNotNull(User user, Procedure procedure) throws ProcedureDoesNotExist, UserWithThisUsernameDoesNotExist {
        if (procedure == null){
            throw new ProcedureDoesNotExist("Procedure does not exist!");
        }
        if (user == null){
            throw new UserWithThisUsernameDoesNotExist("User does not exist!");
        }
    }
}
