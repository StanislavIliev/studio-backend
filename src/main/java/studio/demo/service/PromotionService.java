package studio.demo.service;

import studio.demo.model.service.PromotionServiceModel;

import studio.demo.model.view.PromotionViewModel;

import java.util.List;

public interface PromotionService {

    PromotionViewModel findById(String id);

    void delete(String id);

    List<PromotionViewModel> findAllItems();

    void addPromotion(PromotionServiceModel promotionServiceModel);
}
