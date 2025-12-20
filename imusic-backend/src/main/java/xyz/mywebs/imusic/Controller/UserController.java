package xyz.mywebs.imusic.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.mywebs.imusic.Dto.ApiResponse;
import xyz.mywebs.imusic.Dto.LoginRequest;
import xyz.mywebs.imusic.Dto.SongRequest;
import xyz.mywebs.imusic.Entity.SongList;
import xyz.mywebs.imusic.Entity.User;
import xyz.mywebs.imusic.Service.SongListService;
import xyz.mywebs.imusic.Service.UserService;

import java.util.*;

@RestController
@RequestMapping("api/user")
public class UserController {

    private final UserService userService;
    private final SongListService songListService;

    @Autowired
    public UserController(UserService userService, SongListService songListService) {
        this.userService= userService;
        this.songListService = songListService;
    }

    @PostMapping("/login/password")
    public ApiResponse loginByPassword(@RequestBody LoginRequest loginRequest, HttpSession session) {
        Map<String, Object> responseMessage = new HashMap<>();
        User user = userService.findByUsername(loginRequest.getUsername());

        if (user == null) {
            responseMessage.put("error", "用户不存在");
            return new ApiResponse(false, responseMessage);
        }
        if(Objects.equals(user.getPassword(), loginRequest.getPassword())){
            // 登录成功
            session.setAttribute("username",loginRequest.getUsername());
            session.setAttribute("password",loginRequest.getPassword());
            responseMessage.put("ok","登录成功");
            return new ApiResponse(true,responseMessage);
        }
        else{
            responseMessage.put("error","用户名或密码错误");
            return new ApiResponse(false,responseMessage);
        }
    }

    @PostMapping("/register")
    public ApiResponse register(@RequestBody LoginRequest loginRequest, HttpSession session) {
        Map<String, Object> responseMessage = new HashMap<>();

        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        // 验证用户名和密码不为空
        if (username == null || username.trim().isEmpty()) {
            responseMessage.put("error", "用户名不能为空");
            return new ApiResponse(false, responseMessage);
        }
        if (password == null || password.trim().isEmpty()) {
            responseMessage.put("error", "密码不能为空");
            return new ApiResponse(false, responseMessage);
        }

        // 检查用户名是否已存在
        User existedUser = userService.findByUsername(username);
        if (existedUser != null) {
            responseMessage.put("error", "用户名已存在");
            return new ApiResponse(false, responseMessage);
        }

        // 创建新用户
        User user = new User(username, password);
        userService.createUser(user);

        responseMessage.put("ok", "注册成功");
        return new ApiResponse(true, responseMessage);
    }

    @PostMapping("/songLists/show")
    public ApiResponse getSongLists(@RequestBody SongRequest songRequest, HttpSession session){
        Map<String, Object> responseMessage = new HashMap<>();
        String currentUser=(String) session.getAttribute("username");
        if(currentUser==null){
            responseMessage.put("error", "未登录");
            return new ApiResponse(false, responseMessage);
        }
        else{
            List<SongList> songLists = songListService.getSongListsByUsername(currentUser);
            responseMessage.put("songLists", songLists);
            return new ApiResponse(true, responseMessage);
        }
    }


    @PostMapping("/songLists/add")
    public ApiResponse addSongList(@RequestBody SongRequest songRequest, HttpSession session){
        Map<String, Object> responseMessage = new HashMap<>();
        String currentUser=(String) session.getAttribute("username");
        String currentPassword =(String) session.getAttribute("password");
        System.out.println(currentUser);
        System.out.println(currentPassword);
        if(currentUser==null){
            responseMessage.put("error", "未登录");
            return new ApiResponse(false, responseMessage);
        }
        else{
            User user=userService.findByUsername(currentUser);
            if(!Objects.equals(user.getPassword(), currentPassword)){
                responseMessage.put("error","登录信息错误");
                return new ApiResponse(false, responseMessage);
            }

            String listName = songRequest.getListName();
            songListService.createSongList(currentUser,listName);
            responseMessage.put("ok", "歌单创建成功");
            return new ApiResponse(true, responseMessage);
        }
    }
}
