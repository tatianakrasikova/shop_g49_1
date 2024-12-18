package ait.cochort49.shop_g49_1.repository;



import ait.cochort49.shop_g49_1.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Sergey Bugaenko
 * {@code @date} 13.12.2024
 */

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findRoleByTitle(String title);
}
