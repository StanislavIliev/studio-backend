package studio.demo.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import studio.demo.exception.CartDoesNotExists;
import studio.demo.exception.ProcedureDoesNotExist;
import studio.demo.exception.ProductDoesNotExist;
import studio.demo.exception.UserWithThisUsernameDoesNotExist;
import studio.demo.model.binding.ProcedureToCartBindingModel;
import studio.demo.model.binding.ProductToCartBindingModel;
import studio.demo.model.entity.Cart;
import studio.demo.model.entity.Procedure;
import studio.demo.model.entity.Product;
import studio.demo.model.entity.User;
import studio.demo.model.service.ProcedureServiceModel;
import studio.demo.model.service.ProcedureToCartServiceModel;
import studio.demo.model.service.ProductServiceModel;
import studio.demo.model.service.ProductToCartSeviceModel;
import studio.demo.repository.CartRepository;
import studio.demo.repository.ProcedureRepository;
import studio.demo.repository.ProductRepository;
import studio.demo.repository.UserRepository;
import studio.demo.service.CartService;

import java.util.ArrayList;
import java.util.List;

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
        User user = this.userRepository.findByUsername(ppp.getUsername()).orElse(null);
        Product product = this.productRepository.findByName(ppp.getProduct());
        checkUserProductNull(user, product);

        if(user.getCart()  == null){
            Cart cart =  new Cart();
            cart.setProducts(new ArrayList<>());
            cart.setProcedures(new ArrayList<>());
            this.cartRepository.saveAndFlush(cart);
            user.setCart(cart);
        }

        user.getCart().getProducts().add(product);
        this.userRepository.saveAndFlush(user);
        return this.modelMapper.map(product, ProductServiceModel.class);
    }

    @Override
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
    public boolean deleteProduct(ProductToCartSeviceModel product) throws ProductDoesNotExist, UserWithThisUsernameDoesNotExist {

        User user = this.userRepository.findByUsername(product.getUsername()).orElse(null);
        Product ppp = this.productRepository.findById(product.getProduct()).orElse(null);
        checkUserProductNull(user, ppp);
        user.getCart().getProducts().remove(ppp);
        return true;
    }

    @Override
    @Transactional
    public ProcedureServiceModel addProcedureToCart(ProcedureToCartBindingModel pr) throws UserWithThisUsernameDoesNotExist, ProcedureDoesNotExist {

        User user = this.userRepository.findByUsername(pr.getUsername()).orElse(null);
        Procedure procedure = this.procedureRepository.findByName(pr.getProcedure());
        checkUserProcedureNotNull(user, procedure);

        if(user.getCart() == null){
            Cart cart =  new Cart();
            cart.setProducts(new ArrayList<>());
            cart.setProcedures(new ArrayList<>());
            this.cartRepository.saveAndFlush(cart);
            user.setCart(new Cart());
        }

        user.getCart().getProcedures().add(procedure);
        this.userRepository.saveAndFlush(user);

        return this.modelMapper.map(procedure, ProcedureServiceModel.class);
    }


    @Override
    public boolean deleteProcedure(ProcedureToCartServiceModel pr) throws UserWithThisUsernameDoesNotExist, ProcedureDoesNotExist {

        User user = this.userRepository.findByUsername(pr.getUsername()).orElse(null);
        Procedure procedure = this.procedureRepository.findByName(pr.getProcedure());
        checkUserProcedureNotNull(user, procedure);
        user.getCart().getProcedures().remove(procedure);
        return true;
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
