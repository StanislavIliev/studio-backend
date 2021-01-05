package studio.demo.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import studio.demo.exception.*;
import studio.demo.model.binding.OrderAddBindingModel;
import studio.demo.model.entity.User;
import studio.demo.model.view.OrderViewModel;
import studio.demo.model.entity.Manicure;
import studio.demo.model.entity.Order;
import studio.demo.model.service.OrderServiceModel;
import studio.demo.repository.OrderRepository;
import studio.demo.repository.UserRepository;
import studio.demo.service.ManicureService;
import studio.demo.service.OrderService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final ManicureService manicureService;
    private final UserRepository userRepository;

    public OrderServiceImpl(OrderRepository orderRepository, ModelMapper modelMapper, ManicureService manicureService, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
        this.manicureService = manicureService;
        this.userRepository = userRepository;
    }


    @Override
    public OrderServiceModel addOrder(OrderServiceModel orderServiceModel) throws UserNullException, OrderNullException, OrderWithThisNameExist, ManicureNullException {

        String username = orderServiceModel.getUser().getUsername();
        User user = this.userRepository.findByUsername(username).orElse(null);
        if(user == null){
            throw new UserNullException("User is empty.");
        }
        user.setUsername(orderServiceModel.getUser().getUsername());
        user.setEmail(orderServiceModel.getUser().getEmail());
        user.setPhoneNumber(orderServiceModel.getUser().getPhoneNumber());

        this.userRepository.saveAndFlush(user);

        if (orderServiceModel.getName().trim().isEmpty()) {
            throw new OrderNullException("Order name is empty.");
        }
        if (orderRepository.findById(orderServiceModel.getName()).isPresent()) {
            throw new OrderWithThisNameExist("Order with this name exists!");
        }

        Order order  = this.modelMapper.map(orderServiceModel, Order.class);
        order.setUser(user);

        Manicure manicure = this.manicureService
                .find(orderServiceModel.getManicure().getManicureType());
        if(manicure==null){
            throw  new ManicureNullException("Manicure type is empty.");
        }
        order.setManicure(manicure);

        this.orderRepository.saveAndFlush(order);



        this.orderRepository.saveAndFlush(order);

        return orderServiceModel;
    }

    @Override
    public List<OrderViewModel> findAllItems() {

        return this.orderRepository.findAll().stream()
                .map(order -> {
                    OrderViewModel orderViewModel = this.modelMapper
                            .map(order, OrderViewModel.class);
                    orderViewModel.setImgUrl(String.format("/img/%s-%s.jpg"
                            , order.getName(), order.getManicure().getManicureType().name()));
                    return orderViewModel;
                }).collect(Collectors.toList());

    }

    @Override
    public OrderViewModel findById(String id) {
        return this.orderRepository.findById(id)
                .map(order -> {
                    OrderViewModel itemViewModel = this.modelMapper
                            .map(order, OrderViewModel.class);
                    itemViewModel.setImgUrl(String.format("/img/%s-%s.jpg"
                            , order.getName(), order.getManicure().getManicureType().name()));
                    return itemViewModel;
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
    public Optional<Order> findByName(String name) {
        return this.orderRepository.findByName(name);
    }


    @Override
    public OrderServiceModel update(OrderAddBindingModel order) throws OrderWithThisNameDoesNotExist {

        Order ooo = this.orderRepository.
                findByName(order.getName()).orElse(null);
        this.checkOrderExist(ooo);

        if (!order.getDescription().trim().isEmpty()) {
            ooo.setDescription(order.getDescription());
        }
        if (order.getPrice()!=null) {
            ooo.setPrice(order.getPrice());
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
