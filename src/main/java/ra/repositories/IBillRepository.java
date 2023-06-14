package ra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.entities.entity.Bill;
@Repository
public interface IBillRepository extends JpaRepository<Bill,Integer> {

}
