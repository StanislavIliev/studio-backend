package studio.demo.service;

import studio.demo.model.view.OrderViewModel;
import studio.demo.model.service.OrderServiceModel;

import java.util.List;

public interface OrderService {

    void addOrder(OrderServiceModel orderServiceModel);

    List<OrderViewModel> findAllItems();

    OrderViewModel findById(String id);

    void delete(String id);
}
