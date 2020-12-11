package studio.demo.service;


import studio.demo.model.service.AuthorityServiceModel;

import java.util.List;
import java.util.Set;

public interface AuthorityService {

    void seedSeedAuthoritiesInDb();

    List<AuthorityServiceModel> getAllAuthorities();

    AuthorityServiceModel findByAuthority(String authority);

}
