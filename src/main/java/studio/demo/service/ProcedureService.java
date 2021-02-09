package studio.demo.service;

import studio.demo.exception.ProcedureAlreadyExist;
import studio.demo.exception.ProcedureNullException;
import studio.demo.model.binding.ProcedureBindingModel;
import studio.demo.model.entity.Procedure;
import studio.demo.model.service.ProcedureServiceModel;
import studio.demo.model.view.ProcedureViewModel;

import java.util.List;

public interface ProcedureService {

    void initProcedures();

    Procedure findByName(String name);

    List<ProcedureViewModel> findAll();

    ProcedureServiceModel addProcedure(ProcedureBindingModel procedure) throws ProcedureNullException, ProcedureAlreadyExist;

    boolean deleteProcedureById(String id) throws ProcedureNullException;

    Procedure findProcedureById(String id);

    ProcedureServiceModel updateProcedure(ProcedureBindingModel procedure) throws ProcedureNullException;


}
