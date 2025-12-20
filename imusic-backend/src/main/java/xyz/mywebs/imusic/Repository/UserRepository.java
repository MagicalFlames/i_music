package xyz.mywebs.imusic.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.mywebs.imusic.Entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    boolean existsByUsername(String username);
}
