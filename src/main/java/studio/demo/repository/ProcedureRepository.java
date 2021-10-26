package studio.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import studio.demo.model.entity.Procedure;

@Repository
public interface ProcedureRepository extends JpaRepository<Procedure, String> {

    Procedure findByName(String name);

}
