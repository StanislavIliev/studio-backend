package studio.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import studio.demo.model.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {

}

