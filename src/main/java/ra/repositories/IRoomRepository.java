//package ra.repositories;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//import ra.entities.entity.Room;
//
//import java.time.LocalDate;
//import java.time.LocalTime;
//import java.util.List;
//
//@Repository
//public interface IRoomRepository extends JpaRepository<Room, Integer> {
////    @Query("SELECT r FROM Room r WHERE r.id in (SELECT s.room.id FROM Schedule s WHERE s.movie.id=:movieId AND " +
////            "s.branch.id = :branchId AND s.startDate=:startDate AND s.startTime=:startTime)")
//
//    @Query(value = "select r from Room r where r.id = (select s.id from Schedule s where s.movie.id = :movieId and s.branch.id = :branchId and s.startDate = :startDate )")
//    List<Room> getRoomByBranchAndMovieAndSchedule(@Param("movieId") Integer movieId,
//                                            @Param("branchId") Integer branchId,
//                                            @Param("startDate") LocalDate startDate);
//
//}
