package studio.demo.service;

import studio.demo.exception.*;
import studio.demo.model.binding.OrderAddBindingModel;
import studio.demo.model.entity.Order;
import studio.demo.model.view.OrderViewModel;
import studio.demo.model.service.OrderServiceModel;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    OrderServiceModel addOrder(OrderServiceModel orderServiceModel) throws UserNullException, OrderNullException, OrderWithThisNameExist, ProcedureNullException, ProductNullException, OrderEmptyException;

    List<OrderViewModel> findAllItems();

    List<OrderViewModel> findMyOrders(String id) throws OrderEmptyException;
    
    OrderViewModel findByid(String id);

    boolean delete(String id) throws OrderWithThisIdNotExist;
    
    OrderViewModel findById(String id);
}
