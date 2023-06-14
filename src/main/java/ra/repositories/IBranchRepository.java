package ra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ra.entities.entity.Branch;

import java.util.List;

@Repository
public interface IBranchRepository extends JpaRepository<Branch,Integer> {
    @Query("SELECT b FROM Branch b where b.id in " +
            "(SELECT s.branch.id FROM Schedule s JOIN s.movie m WHERE s.movie.id = :movieId )")
    List<Branch> getBranchThatShowTheMovie(@Param("movieId") Integer movieId);
}
