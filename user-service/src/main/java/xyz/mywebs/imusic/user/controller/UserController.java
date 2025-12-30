package xyz.mywebs.imusic.user.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;
import xyz.mywebs.imusic.user.dto.ApiResponse;
import xyz.mywebs.imusic.user.dto.LoginRequest;
import xyz.mywebs.imusic.user.entity.User;
import xyz.mywebs.imusic.user.service.UserService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login/password")
    public ApiResponse loginByPassword(@RequestBody LoginRequest loginRequest, HttpSession session) {
        Map<String, Object> responseMessage = new HashMap<>();

        User user = userService.findByUsername(loginRequest.getUsername());
        if (user == null) {
            responseMessage.put("error", "用户不存在");
            return new ApiResponse(false, responseMessage);
        }

        if (!user.getPassword().equals(loginRequest.getPassword())) {
            responseMessage.put("error", "密码错误");
            return new ApiResponse(false, responseMessage);
        }

        session.setAttribute("username", loginRequest.getUsername());
        session.setAttribute("password", loginRequest.getPassword());
        responseMessage.put("ok", "登录成功");
        return new ApiResponse(true, responseMessage);
    }

    @PostMapping("/register")
    public ApiResponse register(@RequestBody LoginRequest loginRequest, HttpSession session) {
        Map<String, Object> responseMessage = new HashMap<>();

        if (loginRequest.getUsername() == null || loginRequest.getUsername().isEmpty()) {
            responseMessage.put("error", "用户名不能为空");
            return new ApiResponse(false, responseMessage);
        }

        if (loginRequest.getPassword() == null || loginRequest.getPassword().isEmpty()) {
            responseMessage.put("error", "密码不能为空");
            return new ApiResponse(false, responseMessage);
        }

        if (userService.existsByUsername(loginRequest.getUsername())) {
            responseMessage.put("error", "用户名已存在");
            return new ApiResponse(false, responseMessage);
        }

        User user = new User(loginRequest.getUsername(), loginRequest.getPassword());
        userService.createUser(user);

        session.setAttribute("username", loginRequest.getUsername());
        session.setAttribute("password", loginRequest.getPassword());
        responseMessage.put("ok", "注册成功");
        return new ApiResponse(true, responseMessage);
    }

    @GetMapping("/validate")
    public ApiResponse validateUser(@RequestParam String username, @RequestParam String password) {
        Map<String, Object> responseMessage = new HashMap<>();

        User user = userService.findByUsername(username);
        if (user == null) {
            responseMessage.put("valid", false);
            responseMessage.put("error", "用户不存在");
            return new ApiResponse(false, responseMessage);
        }

        if (!user.getPassword().equals(password)) {
            responseMessage.put("valid", false);
            responseMessage.put("error", "密码错误");
            return new ApiResponse(false, responseMessage);
        }

        responseMessage.put("valid", true);
        return new ApiResponse(true, responseMessage);
    }
}
