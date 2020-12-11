package studio.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import studio.demo.service.impl.JwtProviderImpl;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
//        JwtProviderImpl jwt = new JwtProviderImpl();
//        jwt.createJwt(null);
    }

}
