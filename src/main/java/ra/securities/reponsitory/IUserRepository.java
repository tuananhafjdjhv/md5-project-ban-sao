package ra.securities.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ra.securities.model.User;

import java.util.Optional;
@Repository
@Transactional
public interface IUserRepository extends JpaRepository<User,Long> {
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    Optional<User> findByUsername(String username);
@Modifying
    @Query("UPDATE User e SET e.status = 0 WHERE e.id = :blockId")
    void blockUser(Long blockId);
@Modifying
    @Query("UPDATE User e SET e.status = 1 WHERE e.id = :unblockId")
    void unBlockUser(Long unblockId);
}
