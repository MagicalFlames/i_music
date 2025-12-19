package xyz.mywebs.imusic.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.mywebs.imusic.Entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByPlatformAndThirdPartID(String platform,String thirdPartID);
}