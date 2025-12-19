package xyz.mywebs.imusic.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.mywebs.imusic.Entity.User;
import xyz.mywebs.imusic.Repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    public void createUser(User user) {
        userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    public User findByPlatformAndThirdPartID(String platform,String thirdpartID){
        return userRepository.findByPlatformAndThirdPartID(platform,thirdpartID);
    }
    public void deleteUserByPlatformAndThirdPartID(String platform,String thirdpartID) {
        User user=findByPlatformAndThirdPartID(platform, thirdpartID);
        if(user!=null){
            userRepository.delete(user);
        }
    }
}
