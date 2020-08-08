package studio.demo.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import studio.demo.model.view.OrderViewModel;
import studio.demo.model.entity.Manicure;
import studio.demo.model.entity.Order;
import studio.demo.model.service.OrderServiceModel;
import studio.demo.repository.OrderRepository;
import studio.demo.service.ManicureService;
import studio.demo.service.OrderService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final ManicureService manicureService;

    public OrderServiceImpl(OrderRepository orderRepository, ModelMapper modelMapper, ManicureService manicureService) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
        this.manicureService = manicureService;
    }


    @Override
    public void addOrder(OrderServiceModel orderServiceModel) {
        Order order = this.modelMapper.map(orderServiceModel, Order.class);
        Manicure manicure = this.manicureService
                .find(orderServiceModel.getManicure().getManicureType());
        order.setManicure(manicure);

        this.orderRepository.saveAndFlush(order);
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
    public void delete(String id) {
        this.orderRepository.deleteById(id);
    }
}
