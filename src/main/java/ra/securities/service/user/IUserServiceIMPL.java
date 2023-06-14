package ra.securities.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.securities.model.User;
import ra.securities.reponsitory.IUserRepository;

import java.util.List;
import java.util.Optional;
@Service
public class IUserServiceIMPL implements IUserService{
    @Autowired
    private IUserRepository userService;
    @Override
    public Boolean existsByUsername(String username) {
        return userService.existsByUsername(username);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userService.existsByEmail(email);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userService.findByUsername(username);
    }

    @Override
    public User save(User user) {
        return userService.save(user);
    }

    @Override
    public List<User> findAll() {
        return userService.findAll();
    }
}
