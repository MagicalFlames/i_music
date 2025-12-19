package xyz.mywebs.imusic.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import xyz.mywebs.imusic.Dto.ApiResponse;
import xyz.mywebs.imusic.Dto.LoginRequest;
import xyz.mywebs.imusic.Dto.SongRequest;
import xyz.mywebs.imusic.Entity.SongList;
import xyz.mywebs.imusic.Entity.User;
import xyz.mywebs.imusic.Service.SongListService;
import xyz.mywebs.imusic.Service.UserService;

import java.io.UnsupportedEncodingException;
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

    @Value("${codeforces.clientId}")
    private String clientId;
    @Value("${codeforces.clientSecret}")
    private String clientSecret;
    @Value("${codeforces.redirectUri}")
    private String redirectUri;
    @Value("${codeforces.grantType}")
    private String grantType;

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
            responseMessage.put("ok","登陆成功");
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
        String thirdPartHandle = (String) session.getAttribute("thirdPartHandle");
        String thirdPart = (String) session.getAttribute("thirdPart");
        if(thirdPartHandle != null&&thirdPart!=null){
            User user=new User(loginRequest.getUsername(),loginRequest.getPassword(),thirdPart,thirdPartHandle);
            User existedUser=userService.findByPlatformAndThirdPartID(thirdPart,thirdPartHandle);
            if(existedUser!=null){
                userService.deleteUserByPlatformAndThirdPartID(thirdPart,thirdPartHandle);
            }
            userService.createUser(user);
            responseMessage.put("ok","用户创建成功");
            return new ApiResponse(true,responseMessage);

        }
        responseMessage.put("error","未认证");
        return new ApiResponse(false,responseMessage);
    }

    @GetMapping("/certificate/codeforces/")
    public ApiResponse codeforcesCallback(HttpServletRequest request, HttpSession session) {
        Map<String, Object> responseMessage = new HashMap<>();
        // 获取code
        String code = request.getParameter("code");
        // 组装请求体
        String requestBody = "client_id=" + clientId + "&" +
                "client_secret=" + clientSecret + "&" +
                "code=" + code + "&" +
                "redirect_uri=" + redirectUri + "&" +
                "grant_type=" + grantType;

        // 创建HTTP头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // 创建请求实体
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        // 发送POST请求并获取响应
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(
                "https://codeforces.com/oauth/token",
                HttpMethod.POST,
                entity,
                String.class
        );
        try {
            // 获取 id_token
            String idToken = new ObjectMapper().readTree(response.getBody()).path("id_token").asText();
            // 解析 id_token
            String[] tokenParts = idToken.split("\\.");
            String payload = new String(Base64.getUrlDecoder().decode(tokenParts[1]), "UTF-8");
            JsonNode payloadJson = new ObjectMapper().readTree(payload);

            // 打印 handle 字段
            String handle = payloadJson.path("handle").asText();
            session.setAttribute("thirdPartHandle", handle);
            session.setAttribute("thirdPart", "codeforces");
            responseMessage.put("ok", "认证完成");
            responseMessage.put("type","codeforcrs_AUTH_SUCCESS");
            return new ApiResponse(true, responseMessage);
        } catch (JsonProcessingException | UnsupportedEncodingException e) {
            e.printStackTrace();  // 打印异常堆栈
            responseMessage.put("error", "未知异常");
            return new ApiResponse(false, responseMessage);
        }
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



