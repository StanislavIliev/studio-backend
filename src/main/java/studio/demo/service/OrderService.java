package studio.demo.service;

import studio.demo.exception.*;
import studio.demo.model.binding.OrderAddBindingModel;
import studio.demo.model.entity.Order;
import studio.demo.model.view.OrderViewModel;
import studio.demo.model.service.OrderServiceModel;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    OrderServiceModel update(OrderAddBindingModel order) throws OrderWithThisNameDoesNotExist;

    OrderServiceModel addOrder(OrderServiceModel orderServiceModel) throws UserNullException, OrderNullException, OrderWithThisNameExist, ManicureNullException;

    List<OrderViewModel> findAllItems();

    OrderViewModel findById(String id);

    boolean delete(String id) throws OrderWithThisIdNotExist;

    Optional<Order> findByName(String name);
}
