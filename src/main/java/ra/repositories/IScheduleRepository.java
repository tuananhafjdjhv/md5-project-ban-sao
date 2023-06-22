package ra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ra.entities.entity.Schedule;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface IScheduleRepository extends JpaRepository<Schedule,Integer> {
    @Query("SELECT DISTINCT s.startTime FROM Schedule s WHERE s.movie.id=:movieId AND s.branch.id" +
            "= :branchId AND s.startDate=:startDate")
    List<LocalTime> getStartTimeByMovie_IdAndBranch_IdAndStartDate(@Param("movieId") Integer movieId
            , @Param("branchId") Integer branchId
            , @Param("startDate") LocalDate startDate);

//    @Query(value = "select * from Schedule where startDate= :startDate and startTime= :startTime and branch_id = :branhId and movie_id=:movieId and room_id= :roomId",nativeQuery = true)
//    List<Schedule> getSchedulesByMovie_IdAndBranch_IdAndStartDateAndStartTimeAndRoom_Id(@Param("movieId") Integer movieId,@Param("branhId") Integer branchId
//            ,@Param("startDate") LocalDate startDate,@Param("startTime") LocalTime startTime,@Param("roomId") Integer roomId);

    List<Schedule> findAllByMovieIdAndBranch_IdAndRoom_IdAndStartDate(int movieId,
                                                                      int branchId,
                                                                      int roomId,
                                                                      LocalDate startDate );

}
