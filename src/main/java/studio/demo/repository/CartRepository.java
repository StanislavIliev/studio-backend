package studio.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import studio.demo.model.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart,String>{

}
