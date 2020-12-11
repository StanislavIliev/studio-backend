package studio.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import studio.demo.model.entity.Authority;


@Repository
public interface AuthorityRepository extends JpaRepository<Authority, String> {

    Authority findByAuthority(String authority);
}
