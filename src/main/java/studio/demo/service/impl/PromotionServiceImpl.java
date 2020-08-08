package studio.demo.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import studio.demo.model.entity.Promotion;
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
    public void delete(String id) {
        this.promotionRepository.deleteById(id);
    }

    @Override
    public void addPromotion(PromotionServiceModel promotionServiceModel) {
        Promotion promotion = this.modelMapper.map(promotionServiceModel, Promotion.class);

        this.promotionRepository.saveAndFlush(promotion);
    }
}
