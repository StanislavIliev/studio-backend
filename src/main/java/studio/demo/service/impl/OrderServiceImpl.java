package studio.demo.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
import studio.demo.model.view.UserViewModel;
import studio.demo.model.entity.Order;
import studio.demo.model.service.OrderServiceModel;
import studio.demo.model.service.ProcedureServiceModel;
import studio.demo.model.service.ProductServiceModel;
import studio.demo.repository.OrderRepository;
import studio.demo.repository.UserRepository;
import studio.demo.service.ProcedureService;
import studio.demo.service.OrderService;
import studio.demo.service.ProductService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
    @Transactional
    public OrderServiceModel addOrder(OrderServiceModel orderServiceModel) throws UserNullException, OrderNullException, OrderWithThisNameExist, ProcedureNullException, ProductNullException, OrderEmptyException {
       
        User user = this.modelMapper.map(orderServiceModel.getUser() ,User.class );
        if(user == null){
            throw new UserNullException("User is empty.");
        }
        
        Order order = new Order();
        List<Product> products = new ArrayList<>();        
        List<Procedure> procedures = new ArrayList<>();

        if(orderServiceModel.getProcedures()!=null) {
        	for( ProcedureServiceModel ppp: orderServiceModel.getProcedures()) {
        	Procedure proc =this.modelMapper.map(ppp , Procedure.class);
        	procedures.add(proc);

        }}
        if(orderServiceModel.getProducts()!=null) {
            for( ProductServiceModel p: orderServiceModel.getProducts()) {
        	Product prod = this.modelMapper.map(p, Product.class);
            products.add(prod); 
            }}
    	order.setProducts(products);
        order.setProcedures(procedures);
        order.setUser(user);
        
        if (user.getCart()!= null){
        user.getCart().getProducts().clear();
        user.getCart().getProcedures().clear();
        }
        this.orderRepository.saveAndFlush(order);
        return orderServiceModel;
    }

    @Override
    @Transactional
    public List<OrderViewModel> findMyOrders(String id) throws OrderEmptyException {

    	User user =this.userRepository.findById(id).orElse(null);
   	  	if(user == null ){
          throw new OrderEmptyException("Order can not be null.");
   	  	}
   	  	List<OrderViewModel> orderViews =new ArrayList<>();
    
    	if(!this.findAllItems().isEmpty()) {
    	for( OrderViewModel o: this.findAllItems()) {
    		if(o.getUser().getId()== id) {
    			orderViews.add(o);
    		}
    	}
    	}
    	
        return orderViews;
    }
    
    @Override
    @Transactional
    public List<OrderViewModel> findAllItems() {

        return this.orderRepository.findAll().stream()
                .map(order -> {
                	OrderViewModel orderViewModel = this.modelMapper.map(order, OrderViewModel.class);
                	if(!order.getProcedures().isEmpty()) {
                		List<ProcedureViewModel> pro= new ArrayList<>();
                		for(Procedure procedure : order.getProcedures()) {
                			ProcedureViewModel ppp=this.modelMapper.map(procedure, ProcedureViewModel.class);
                			pro.add(ppp);
                		}
                		orderViewModel.setProcedures(pro);
                	}
                	
                	if(!order.getProducts().isEmpty()) {
                		List<ProductViewModel> proc= new ArrayList<>();
                		for(Product product : order.getProducts()) {
                			ProductViewModel ppp=this.modelMapper.map(product, ProductViewModel.class);
                			proc.add(ppp);
                		}
                		orderViewModel.setProducts(proc);
                	}
                	
                	return orderViewModel;
                }).collect(Collectors.toList());
    }

    @Override
    @Transactional
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
    @Transactional
    public boolean delete(String id) throws OrderWithThisIdNotExist {

        if (this.orderRepository.findById(id).isEmpty()) {
            throw new OrderWithThisIdNotExist("Order with this id does not exist");
        }
        this.orderRepository.deleteById(id);
        return true;
    }



    @Override  
    @Transactional
    public OrderViewModel findById(String id) {
    	
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




    private void checkOrderExist(Order ooo) throws OrderWithThisNameDoesNotExist {
        if (ooo == null) {
            throw new OrderWithThisNameDoesNotExist("Order with this name does not exist!");
        }
    }


}
