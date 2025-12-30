package xyz.mywebs.imusic.playlist.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;
import xyz.mywebs.imusic.playlist.client.UserServiceClient;
import xyz.mywebs.imusic.playlist.dto.ApiResponse;
import xyz.mywebs.imusic.playlist.dto.SongRequest;
import xyz.mywebs.imusic.playlist.dto.SongResponse;
import xyz.mywebs.imusic.playlist.entity.SongList;
import xyz.mywebs.imusic.playlist.service.SongListItemsService;
import xyz.mywebs.imusic.playlist.service.SongListService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api")
public class PlaylistController {
    private final SongListService songListService;
    private final SongListItemsService songListItemsService;
    private final UserServiceClient userServiceClient;

    public PlaylistController(SongListService songListService, SongListItemsService songListItemsService, UserServiceClient userServiceClient) {
        this.songListService = songListService;
        this.songListItemsService = songListItemsService;
        this.userServiceClient = userServiceClient;
    }

    private String getUsername(HttpServletRequest request) {
        return request.getHeader("X-Username");
    }

    private String getPassword(HttpServletRequest request) {
        return request.getHeader("X-Password");
    }

    @PostMapping("/user/songLists/show")
    public ApiResponse getSongLists(@RequestBody SongRequest songRequest, HttpServletRequest request) {
        Map<String, Object> responseMessage = new HashMap<>();

        String currentUser = getUsername(request);
        if (currentUser == null || currentUser.isEmpty()) {
            responseMessage.put("error", "未登录");
            return new ApiResponse(false, responseMessage);
        }

        List<SongList> songLists = songListService.getSongListsByUsername(currentUser);
        responseMessage.put("songLists", songLists);
        return new ApiResponse(true, responseMessage);
    }

    @PostMapping("/user/songLists/add")
    public ApiResponse addSongList(@RequestBody SongRequest songRequest, HttpServletRequest request) {
        Map<String, Object> responseMessage = new HashMap<>();

        String currentUser = getUsername(request);
        String currentPassword = getPassword(request);

        if (currentUser == null || currentUser.isEmpty()) {
            responseMessage.put("error", "未登录");
            return new ApiResponse(false, responseMessage);
        }

        if (!userServiceClient.validateUser(currentUser, currentPassword)) {
            responseMessage.put("error", "登录信息验证失败");
            return new ApiResponse(false, responseMessage);
        }

        if (songRequest.getListName() == null || songRequest.getListName().isEmpty()) {
            responseMessage.put("error", "歌单名称不能为空");
            return new ApiResponse(false, responseMessage);
        }

        if (songListService.existsByUsernameAndListName(currentUser, songRequest.getListName())) {
            responseMessage.put("error", "歌单已存在");
            return new ApiResponse(false, responseMessage);
        }

        songListService.createSongList(currentUser, songRequest.getListName());
        responseMessage.put("ok", "歌单创建成功");
        return new ApiResponse(true, responseMessage);
    }

    @PostMapping("/song/search/insonglist")
    public ApiResponse searchInSongList(@RequestBody SongRequest songRequest, HttpServletRequest request) {
        Map<String, Object> responseMessage = new HashMap<>();

        String currentUser = getUsername(request);
        String currentPassword = getPassword(request);

        if (currentUser == null || currentUser.isEmpty()) {
            responseMessage.put("error", "未登录");
            return new ApiResponse(false, responseMessage);
        }

        if (!userServiceClient.validateUser(currentUser, currentPassword)) {
            responseMessage.put("error", "登录信息验证失败");
            return new ApiResponse(false, responseMessage);
        }

        List<SongResponse> songs = songListItemsService.findByUsernameAndListName(currentUser, songRequest.getListName());
        responseMessage.put("ok", "歌单音乐查询成功");
        responseMessage.put("songs", songs);
        return new ApiResponse(true, responseMessage);
    }

    @PostMapping("/song/add/tosonglist")
    public ApiResponse addToSongList(@RequestBody SongRequest songRequest, HttpServletRequest request) {
        Map<String, Object> responseMessage = new HashMap<>();

        String currentUser = getUsername(request);
        String currentPassword = getPassword(request);

        if (currentUser == null || currentUser.isEmpty()) {
            responseMessage.put("error", "未登录");
            return new ApiResponse(false, responseMessage);
        }

        if (!userServiceClient.validateUser(currentUser, currentPassword)) {
            responseMessage.put("error", "登录信息验证失败");
            return new ApiResponse(false, responseMessage);
        }

        songListItemsService.addSongToList(
                songRequest.getTitle(),
                songRequest.getAlbumArtist(),
                songRequest.getAlbum(),
                currentUser,
                songRequest.getListName()
        );

        responseMessage.put("ok", "歌曲已成功添加到歌单");
        return new ApiResponse(true, responseMessage);
    }

    @PostMapping("/song/delete/fromsonglist")
    public ApiResponse deleteFromSongList(@RequestBody SongRequest songRequest, HttpServletRequest request) {
        Map<String, Object> responseMessage = new HashMap<>();

        String currentUser = getUsername(request);
        String currentPassword = getPassword(request);

        if (currentUser == null || currentUser.isEmpty()) {
            responseMessage.put("error", "未登录");
            return new ApiResponse(false, responseMessage);
        }

        if (!userServiceClient.validateUser(currentUser, currentPassword)) {
            responseMessage.put("error", "登录信息验证失败");
            return new ApiResponse(false, responseMessage);
        }

        songListItemsService.deleteSongFromList(
                songRequest.getTitle(),
                songRequest.getAlbumArtist(),
                songRequest.getAlbum(),
                currentUser,
                songRequest.getListName()
        );

        responseMessage.put("ok", "歌曲已成功从歌单中删除");
        return new ApiResponse(true, responseMessage);
    }
}
