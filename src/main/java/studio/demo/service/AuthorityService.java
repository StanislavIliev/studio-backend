package studio.demo.service;

import studio.demo.model.service.AuthorityServiceModel;
import java.util.List;

public interface AuthorityService {

    void seedSeedAuthoritiesInDb();

    List<AuthorityServiceModel> getAllAuthorities();

    AuthorityServiceModel findByAuthority(String authority);

}
