package studio.demo.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import studio.demo.enums.DefaultProcedures;
import studio.demo.exception.*;
import studio.demo.model.binding.ProcedureBindingModel;
import studio.demo.model.entity.Order;
import studio.demo.model.entity.Procedure;
import studio.demo.model.entity.Product;
import studio.demo.model.entity.User;
import studio.demo.model.service.ProcedureServiceModel;
import studio.demo.model.view.ProcedureViewModel;
import studio.demo.repository.ProcedureRepository;
import studio.demo.service.ProcedureService;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProcedureServiceImpl implements ProcedureService {

    private final ProcedureRepository procedureRepository;
    private final ModelMapper modelMapper;

    public ProcedureServiceImpl(ProcedureRepository procedureRepository, ModelMapper modelMapper) {
        this.procedureRepository = procedureRepository;
        this.modelMapper = modelMapper;
    }

//
//    @Override
//    public void initProcedures() {
//        if (this.procedureRepository.count() == 0) {
//            for (DefaultProcedures p : DefaultProcedures.values()) {
//                Procedure newProcedure = new Procedure();
//                this.procedureRepository.saveAndFlush(newProcedure);
//            }
//        }
//    }

    @Override
    public Procedure findByName(String name) {

        return this.procedureRepository.findByName(name);
    }

    @Override
    public List<ProcedureViewModel> findAll() {

        return this.procedureRepository.findAll().stream()
                .map(procedure -> {
                    ProcedureViewModel procedureViewModel = this.modelMapper
                            .map(procedure, ProcedureViewModel.class);
                    procedureViewModel.setImgUrl(String.format("/img/%s-%s-%s .jpg"
                            , procedure.getName(),
                            procedure.getDescription(),
                            procedure.getPrice()));
                    return procedureViewModel;
                }).collect(Collectors.toList());
    }

    @Override
    public ProcedureServiceModel addProcedure(ProcedureBindingModel procedure) throws ProcedureNullException, ProcedureAlreadyExist {

        if (procedureRepository.findById(procedure.getName()).isPresent()) {
            throw new ProcedureAlreadyExist("Procedure with this name exists!");
        }

        Procedure p = new Procedure();

        p.setDate(procedure.getDate());
        p.setDescription(procedure.getDescription());
        p.setPrice(procedure.getPrice());
        p.setName(procedure.getName());
        this.procedureRepository.saveAndFlush(p);

        return this.modelMapper.map(p,ProcedureServiceModel.class);
    }

    @Override
    public boolean deleteProcedureById(String id) throws ProcedureNullException {
        if (this.procedureRepository.findById(id).isEmpty()) {
            throw new ProcedureNullException("Procedure does not exist.");
        }
        this.procedureRepository.deleteById(id);
        return true;
    }

    @Override
    public Procedure findProcedureById(String id) {
        return this.procedureRepository.findById(id).orElse(null);
    }

    @Override
    public ProcedureServiceModel updateProcedure(ProcedureBindingModel procedure) throws ProcedureNullException {

        Procedure updatedProcedure = this.procedureRepository.
                findById(procedure.getId()).orElse(null);

        this.checkProcedureExist(updatedProcedure);

        if (!procedure.getDescription().trim().isEmpty()) {
            updatedProcedure.setDescription(procedure.getDescription());
        }
        if (procedure.getDate()!=null) {
            updatedProcedure.setDate(procedure.getDate());
        }
        if (procedure.getPrice()!=null) {
            updatedProcedure.setPrice(procedure.getPrice());
        }
        if (procedure.getName()!=null) {
            updatedProcedure.setName(procedure.getName());
        }
        return this.modelMapper.map(this.procedureRepository.saveAndFlush(updatedProcedure),
                ProcedureServiceModel.class);
    }

    private void checkProcedureExist(Procedure procedure) throws ProcedureNullException {
        if (procedure == null) {
            throw new ProcedureNullException("Procedure with this name does not exist!");
        }
    }
}

