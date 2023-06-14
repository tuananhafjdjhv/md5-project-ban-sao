package ra.securities.service.role;

import ra.securities.model.Role;
import ra.securities.model.RoleName;

import java.util.Optional;

public interface IRoleService {
    Optional<Role> findByName(RoleName name);
}
