//package studio.demo.service.impl;
//
//import org.modelmapper.ModelMapper;
//import org.springframework.stereotype.Service;
//import studio.demo.model.entity.Role;
//import studio.demo.model.service.RoleServiceModel;
//import studio.demo.repository.RoleRepository;
//import studio.demo.service.RoleService;
//
//import java.util.Set;
//import java.util.stream.Collectors;
//
//@Service
//public class RoleServiceImpl implements RoleService {
//    private final RoleRepository roleRepository;
//    private final ModelMapper modelMapper;
//
//    public RoleServiceImpl(RoleRepository roleRepository, ModelMapper modelMapper) {
//        this.roleRepository = roleRepository;
//        this.modelMapper = modelMapper;
//    }
//
//    @Override
//    public void seedRolesindb() {
//        if (this.roleRepository.count() == 0) {
//            this.roleRepository.saveAndFlush(new Role("ADMIN"));
//            this.roleRepository.saveAndFlush(new Role("USER"));
//        }
//    }
//
//    @Override
//    public Set<RoleServiceModel> findAllRoles() {
//        return this.roleRepository.findAll()
//                .stream().map(r ->
//                        this.modelMapper.map(r, RoleServiceModel.class)).collect(Collectors.toSet());
//    }
//
//    @Override
//    public RoleServiceModel findByAuthority(String authority) {
//        return this.modelMapper.map(this.roleRepository.findByAuthority(authority), RoleServiceModel.class);
//    }
//}
