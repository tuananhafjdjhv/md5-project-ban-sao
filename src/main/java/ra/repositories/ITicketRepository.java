package ra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ra.entities.entity.Schedule;
import ra.entities.entity.Seat;
import ra.entities.entity.Ticket;

import java.util.List;
import java.util.Optional;

@Repository
public interface ITicketRepository extends JpaRepository<Ticket,Integer> {
    List<Ticket> findTicketsBySchedule_Id(Integer scheduleId);
    List<Ticket> findTicketBySeatAndSchedule(Seat seat, Schedule schedule);
    @Query("SELECT t FROM Ticket t WHERE t.bill.id IN (SELECT b.id FROM Bill b WHERE b.user.id=:userId) ORDER BY t.id DESC")
    List<Ticket> findTicketsByUserId(@Param("userId") Long userId);
}
