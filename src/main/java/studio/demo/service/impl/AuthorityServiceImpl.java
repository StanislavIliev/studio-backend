package studio.demo.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import studio.demo.model.entity.Authority;
import studio.demo.model.service.AuthorityServiceModel;
import studio.demo.repository.AuthorityRepository;
import studio.demo.service.AuthorityService;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorityServiceImpl implements AuthorityService {
    private final AuthorityRepository roleRepository;
    private final ModelMapper modelMapper;

    public AuthorityServiceImpl(AuthorityRepository authorityRepository, ModelMapper modelMapper) {
        this.roleRepository = authorityRepository;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    @Override
    public void seedSeedAuthoritiesInDb() {
        if (this.roleRepository.count() == 0) {
            this.roleRepository.saveAndFlush(new Authority("ADMIN"));
            this.roleRepository.saveAndFlush(new Authority("USER"));
        }
    }

    @Override
    public List<AuthorityServiceModel> getAllAuthorities() {
        return this.roleRepository.findAll()
                .stream().map(r ->
                        this.modelMapper.map(r, AuthorityServiceModel.class)).collect(Collectors.toList());
    }

    @Override
    public AuthorityServiceModel findByAuthority(String authority) {
        return this.modelMapper.map(this.roleRepository.findByAuthority(authority), AuthorityServiceModel.class);
    }
}
