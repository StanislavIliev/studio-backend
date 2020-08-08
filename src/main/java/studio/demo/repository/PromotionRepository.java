package studio.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import studio.demo.model.entity.Promotion;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, String> {
}
