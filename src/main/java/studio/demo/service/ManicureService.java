package studio.demo.service;

import studio.demo.model.entity.Manicure;
import studio.demo.model.entity.ManicureType;

public interface ManicureService {

    void initCAtegories();

    Manicure find(ManicureType manicureType);
}
