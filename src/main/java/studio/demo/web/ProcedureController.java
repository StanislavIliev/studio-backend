package studio.demo.web;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import studio.demo.exception.ProcedureAlreadyExist;
import studio.demo.exception.ProcedureNullException;
import studio.demo.model.binding.ProcedureBindingModel;
import studio.demo.model.entity.Procedure;
import studio.demo.model.service.ProcedureServiceModel;
import studio.demo.model.view.ProcedureViewModel;
import studio.demo.service.ProcedureService;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/procedures")
public class ProcedureController {


    ConcurrentMap<String , Procedure> procedures = new ConcurrentHashMap<>();
    private final ProcedureService procedureService;
    private final ModelMapper modelMapper;

    public ProcedureController(ProcedureService procedureService, ModelMapper modelMapper) {
        this.procedureService = procedureService;
        this.modelMapper = modelMapper;
    }


    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProcedureViewModel>> allProcedures() {
        List<ProcedureViewModel> procedures = this.procedureService.findAll()
                .stream()
                .map(ccc -> this.modelMapper.map(ccc, ProcedureViewModel.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(procedures, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Find promotions by id",
            notes = "Provide id to look up for a specific procedure",
            response = Procedure.class
    )
    public  ResponseEntity<ProcedureViewModel> getProcedure (@ApiParam(value = "Id value for the procedure you need to retrieve"
            ,required = true)@PathVariable String id){

        Procedure procedure = this.procedureService.findProcedureById(id);
        return new ResponseEntity<ProcedureViewModel>(this.modelMapper
                .map(procedure, ProcedureViewModel.class),HttpStatus.OK);
    }


    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProcedureViewModel> update
            (@RequestBody ProcedureBindingModel procedure) throws  ProcedureNullException {

        ProcedureServiceModel updatedProcedure =
                this.procedureService.updateProcedure
                        (this.modelMapper.map(procedure, ProcedureBindingModel.class));

        return new ResponseEntity<ProcedureViewModel>(this.modelMapper.map
                (updatedProcedure, ProcedureViewModel.class),  HttpStatus.OK);

    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProcedureViewModel> addProcedure
            (@RequestBody ProcedureBindingModel procedure) throws ProcedureNullException, ProcedureAlreadyExist {

        ProcedureServiceModel ppp = this.procedureService.addProcedure(procedure);

        ProcedureViewModel proView = this.modelMapper.map(ppp, ProcedureViewModel.class);

        return new ResponseEntity<>(proView, HttpStatus.OK);
    }


    @PostMapping("/delete")
    public ResponseEntity<Boolean> deleteProcedure (@Valid @RequestBody String id) throws ProcedureNullException {

        boolean isDeleted = this.procedureService.deleteProcedureById(id);
        return new ResponseEntity<>(isDeleted, HttpStatus.OK);
    }


}
