package studio.demo.service;

import studio.demo.exception.*;
import studio.demo.model.binding.CommentAddBindingModel;
import studio.demo.model.binding.PromotionAddBindingModel;
import studio.demo.model.service.CommentServiceModel;
import studio.demo.model.service.PromotionServiceModel;

import studio.demo.model.view.PromotionViewModel;

import java.util.List;

public interface PromotionService {

    PromotionServiceModel update (PromotionAddBindingModel promotion) throws PromotionWithThisNameDoesNotExist;


    PromotionViewModel findById(String id);

    boolean delete(String id) throws PromotionWithThisIdDoesNotExist;

    List<PromotionViewModel> findAllItems();

    PromotionServiceModel addPromotion(PromotionServiceModel promotionServiceModel) throws PromotionDoesNotExist, PromotiontWithThisNameExist;
}
