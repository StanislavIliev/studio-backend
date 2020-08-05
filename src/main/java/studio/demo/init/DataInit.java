package studio.demo.init;


import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import studio.demo.service.ManicureService;

@Component
public class DataInit implements CommandLineRunner {

    private final ManicureService manicureService;

    public DataInit(ManicureService manicureService) {
        this.manicureService = manicureService;
    }


    @Override
    public void run(String... args) throws Exception {
        this.manicureService.initCAtegories();
    }
}
