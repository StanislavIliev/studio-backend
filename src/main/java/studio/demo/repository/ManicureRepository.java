package studio.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import studio.demo.model.entity.Manicure;
import studio.demo.model.entity.ManicureType;

import java.util.Optional;

@Repository
public interface ManicureRepository extends JpaRepository<Manicure, String> {
    Optional<Manicure> findByManicureType(ManicureType manicureType);
}
