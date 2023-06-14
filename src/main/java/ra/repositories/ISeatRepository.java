package ra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.entities.entity.Seat;

import java.util.List;

@Repository
public interface ISeatRepository extends JpaRepository<Seat,Integer> {
    List<Seat> getSeatByRoom_Id(Integer roomId);
}
