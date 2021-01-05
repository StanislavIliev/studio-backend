package studio.demo.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import studio.demo.exception.*;
import studio.demo.model.binding.CommentAddBindingModel;
import studio.demo.model.binding.PromotionAddBindingModel;
import studio.demo.model.entity.Comment;
import studio.demo.model.entity.Promotion;
import studio.demo.model.entity.User;
import studio.demo.model.service.CommentServiceModel;
import studio.demo.model.service.PromotionServiceModel;
import studio.demo.model.view.PromotionViewModel;
import studio.demo.repository.PromotionRepository;
import studio.demo.service.PromotionService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PromotionServiceImpl implements PromotionService {

    private final PromotionRepository promotionRepository;
    private final ModelMapper modelMapper;

    public PromotionServiceImpl(PromotionRepository promotionRepository, ModelMapper modelMapper) {
        this.promotionRepository = promotionRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PromotionViewModel findById(String id) {
        return this.promotionRepository.findById(id)
                .map(promotion -> {
                    PromotionViewModel promotionViewModel = this.modelMapper
                            .map(promotion, PromotionViewModel.class);
                    promotionViewModel.setImageUrl(String.format("/img/%s-%s.jpg"
                            , promotion.getName(), promotion.getPrice()));
                    return promotionViewModel;
                }).orElse(null);
    }

    @Override
    public List<PromotionViewModel> findAllItems() {

        return this.promotionRepository.findAll().stream()
                .map(promotion -> {
                    PromotionViewModel promotionViewModel = this.modelMapper
                            .map(promotion, PromotionViewModel.class);
                    promotionViewModel.setImageUrl(String.format("/img/%s-%s.jpg"
                            , promotion.getName(), promotion.getPrice()));
                    return promotionViewModel;
                }).collect(Collectors.toList());

    }



    @Override
    public boolean delete(String id) throws PromotionWithThisIdDoesNotExist {
        if (this.promotionRepository.findById(id).isEmpty()) {
            throw new PromotionWithThisIdDoesNotExist("Promotion with this id does not exist.");
        }
        this.promotionRepository.deleteById(id);
        return true;
    }

    @Override
    public PromotionServiceModel addPromotion(PromotionServiceModel promotionServiceModel) throws PromotionDoesNotExist, PromotiontWithThisNameExist {
        Promotion ppp=promotionRepository.findByName(promotionServiceModel.getName()).orElse(null);

        if (promotionServiceModel.getName().trim().isEmpty()) {
            throw new PromotionDoesNotExist("Promotion is null.");
        }
        if (ppp != null) {
            throw new PromotiontWithThisNameExist("Promotion with this topic exists!");
        }
        Promotion promotion = this.modelMapper.map(promotionServiceModel, Promotion.class);

        this.promotionRepository.saveAndFlush(promotion);


        return promotionServiceModel;
    }


    @Override
    public PromotionServiceModel update(PromotionAddBindingModel promotion) throws PromotionWithThisNameDoesNotExist {

        Promotion ppp = this.promotionRepository.
                findByName(promotion.getName()).orElse(null);
        this.checkPromotionExist(ppp);

        if (!promotion.getDescription().trim().isEmpty()) {
            ppp.setDescription(promotion.getDescription());
        }
        if (promotion.getPrice() != null) {
            ppp.setPrice(promotion.getPrice());
        }
        return this.modelMapper.map(this.promotionRepository.saveAndFlush(ppp),
                PromotionServiceModel.class);

    }

    private void checkPromotionExist(Promotion promotion) throws PromotionWithThisNameDoesNotExist {
        if (promotion == null) {
            throw new PromotionWithThisNameDoesNotExist("Promotion with this topic does not exist!");
        }
    }


}
