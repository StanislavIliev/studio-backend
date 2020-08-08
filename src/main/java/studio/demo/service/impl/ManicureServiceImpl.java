package studio.demo.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import studio.demo.model.entity.Manicure;
import studio.demo.model.entity.ManicureType;
import studio.demo.repository.ManicureRepository;
import studio.demo.service.ManicureService;

import java.util.Arrays;

@Service
public class ManicureServiceImpl implements ManicureService {

    private final ManicureRepository manicureRepository;
    private final ModelMapper modelMapper;

    public ManicureServiceImpl(ManicureRepository manicureRepository, ModelMapper modelMapper) {
        this.manicureRepository = manicureRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public void initCAtegories() {
        if (this.manicureRepository.count() == 0) {
            Arrays.stream(ManicureType.values())
                    .forEach(categoryName -> {
                        this.manicureRepository
                                .save(new Manicure(categoryName
                                        , String.format("Description for %s", categoryName.name())));
                    });
        }

    }

    @Override
    public Manicure find(ManicureType manicureType) {

        return this.manicureRepository
                .findByManicureType(manicureType)
                .orElse(null);
    }
}

