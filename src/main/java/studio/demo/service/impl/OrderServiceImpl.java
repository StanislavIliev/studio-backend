package studio.demo.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import studio.demo.exception.*;
import studio.demo.model.binding.OrderAddBindingModel;
import studio.demo.model.binding.ProcedureBindingModel;
import studio.demo.model.binding.ProductBindingModel;
import studio.demo.model.entity.Procedure;
import studio.demo.model.entity.Product;
import studio.demo.model.entity.User;
import studio.demo.model.view.OrderViewModel;
import studio.demo.model.view.ProcedureViewModel;
import studio.demo.model.view.ProductViewModel;
import studio.demo.model.entity.Order;
import studio.demo.model.service.OrderServiceModel;
import studio.demo.model.service.ProcedureServiceModel;
import studio.demo.model.service.ProductServiceModel;
import studio.demo.repository.OrderRepository;
import studio.demo.repository.UserRepository;
import studio.demo.service.ProcedureService;
import studio.demo.service.OrderService;
import studio.demo.service.ProductService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final ProcedureService procedureService;
    private final ProductService productService;
    private final UserRepository userRepository;

    public OrderServiceImpl(OrderRepository orderRepository, ModelMapper modelMapper, ProcedureService procedureService, 
    		ProductService productService, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
        this.procedureService = procedureService;
        this.productService = productService;
        this.userRepository = userRepository;
    }


    @Override
    public OrderServiceModel addOrder(OrderServiceModel orderServiceModel) throws UserNullException, OrderNullException, OrderWithThisNameExist, ProcedureNullException, ProductNullException, OrderEmptyException {

        String username = orderServiceModel.getUser().getUsername();
        User user = this.userRepository.findByUsername(username).orElse(null);
        if(user == null){
            throw new UserNullException("User is empty.");
        }

        this.userRepository.saveAndFlush(user);

        if (orderRepository.findById(orderServiceModel.getId()).isPresent()) {
            throw new OrderWithThisNameExist("Order with this name exists!");
        }

        Order order  = this.modelMapper.map(orderServiceModel, Order.class);
        order.setUser(user);


        List<Procedure> procedures = null;
        for( ProcedureServiceModel ppp: orderServiceModel.getProcedures()) {
        	procedures.add(this.modelMapper.map(ppp, Procedure.class));
        }
        order.setProcedures(procedures);
        
        List<Product> products = null;
        for( ProductServiceModel p: orderServiceModel.getProducts()) {
        	products.add(this.modelMapper.map(p, Product.class));
        }
        order.setProducts(products);

        this.orderRepository.saveAndFlush(order);

        return orderServiceModel;
    }

    @Override
    public List<OrderViewModel> findAllItems() {

        return this.orderRepository.findAll().stream()
                .map(order -> {
                    OrderViewModel orderViewModel = this.modelMapper
                            .map(order, OrderViewModel.class);
                    List<ProductViewModel> products =null;
                    List<ProcedureViewModel> procedures = null;
                    for( Product ppp: order.getProducts()) {
                    	products.add(this.modelMapper.map(ppp, ProductViewModel.class));
                    }
                    for( Procedure ppp: order.getProcedures()) {
                    	procedures.add(this.modelMapper.map(ppp, ProcedureViewModel.class));
                    }
                    orderViewModel.setProducts(products);
                    orderViewModel.setProcedures(procedures);
                    return orderViewModel;
                }).collect(Collectors.toList());


    }

    @Override
    public OrderViewModel findByid(String id) {
        return this.orderRepository.findById(id)
                .map(order -> {
                    OrderViewModel orderViewModel = this.modelMapper
                            .map(order, OrderViewModel.class);
                    for( Procedure ppp: order.getProcedures()) {
                         this.modelMapper.map(ppp, ProcedureViewModel.class);
                    }
                    for( Product ppp: order.getProducts()) {
                        this.modelMapper.map(ppp, ProductViewModel.class);
                   }
                    return orderViewModel;
                }).orElse(null);

    }

    @Override
    public boolean delete(String id) throws OrderWithThisIdNotExist {

        if (this.orderRepository.findById(id).isEmpty()) {
            throw new OrderWithThisIdNotExist("Order with this id does not exist");
        }
        this.orderRepository.deleteById(id);
        return true;
    }



    @Override
    public Optional<Order> findById(String id) {

        return this.orderRepository.findById(id);
    }


    @Override
    public OrderServiceModel update(OrderAddBindingModel order) throws OrderWithThisNameDoesNotExist {

        Order ooo = this.orderRepository.
                findById(order.getId()).orElse(null);
        this.checkOrderExist(ooo);

        if (order.getDate()!=null) {
            ooo.setDate(order.getDate());
        }
        if (order.getProcedures()!=null) {
        	List<Product> products =null;
        	for( ProductBindingModel ppp: order.getProducts()) {
                this.modelMapper.map(ppp, Product.class);
           }
            ooo.setProducts(products);
        }
        if (order.getProducts()!=null) {
        	List<Procedure> procedures =null;
        	for( ProcedureBindingModel ppp: order.getProcedures()) {
                this.modelMapper.map(ppp, Procedure.class);
           }
            ooo.setProcedures(procedures);
         }
    	for( Product ppp: ooo.getProducts()) {
            this.modelMapper.map(ppp, ProductServiceModel.class);
       }
    	for( Procedure ppp: ooo.getProcedures()) {
            this.modelMapper.map(ppp, Procedure.class);
       }

        return this.modelMapper.map(this.orderRepository.saveAndFlush(ooo),
                OrderServiceModel.class);

    }

    private void checkOrderExist(Order ooo) throws OrderWithThisNameDoesNotExist {
        if (ooo == null) {
            throw new OrderWithThisNameDoesNotExist("Order with this name does not exist!");
        }
    }


}
