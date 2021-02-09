package studio.demo.init;


import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import studio.demo.service.ProcedureService;
import studio.demo.service.ProductService;

@Component
public class DataInit implements CommandLineRunner {

    private final ProcedureService procedureService;
    private final ProductService productService;

    public DataInit(ProcedureService procedureService, ProductService productService) {
        this.procedureService = procedureService;
        this.productService = productService;
    }


    @Override
    public void run(String... args) throws Exception {
        this.procedureService.initProcedures();
       this.productService.initProducts();
    }
}
