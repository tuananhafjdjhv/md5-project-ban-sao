package ra.securities.service.user;

import ra.securities.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    Optional<User> findByUsername(String username);
    User save(User user);
    List<User> findAll();
}
