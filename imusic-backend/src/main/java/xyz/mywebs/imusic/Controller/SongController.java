package xyz.mywebs.imusic.Controller;


import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.mywebs.imusic.Dto.ApiResponse;
import xyz.mywebs.imusic.Dto.SongRequest;
import xyz.mywebs.imusic.Dto.SongResponse;
import xyz.mywebs.imusic.Dto.*;
import xyz.mywebs.imusic.Entity.Song;
import xyz.mywebs.imusic.Entity.User;
import xyz.mywebs.imusic.Service.SongListItemsService;
import xyz.mywebs.imusic.Service.SongService;
import xyz.mywebs.imusic.Service.UserService;

import java.util.*;

@RestController
@RequestMapping("api/song")
public class SongController {

    private final SongService songService;
    private final SongListItemsService songListItemsService;
    private final UserService userService;

    @Autowired
    public SongController(SongService songService, SongListItemsService songListItemsService,UserService userService) {
        this.songService= songService;
        this.songListItemsService=songListItemsService;
        this.userService=userService;
    }


    @PostMapping("/search/all")
    public ApiResponse searchAll(@RequestBody SongRequest songRequest) {
        Map<String, Object> responseMessage = new HashMap<>();

        String songIdentifier = songRequest.getTitle();
        if(songIdentifier==null) songIdentifier= songRequest.getAlbumArtist();

        List<Song> songs =songService.findByAlbumArtistOrTitleWithSorting(songIdentifier);

        List<SongResponse> responseList = new ArrayList<>();
        for (Song song : songs) {
            SongResponse response = new SongResponse(
                    song.getFilePath(),
                    song.getCoverFilePath(),
                    song.getTitle(),
                    song.getArtist(),
                    song.getAlbum(),
                    song.getAlbumArtist(),
                    song.getYear(),
                    song.getGenre(),
                    song.getTrackNumber(),
                    song.getBitrate(),
                    song.getDuration()
            );
            responseList.add(response);
        }
        responseMessage.put("ok","查询成功");
        responseMessage.put("songs", responseList);
        return new ApiResponse(true,responseMessage);
    }

    @PostMapping("/play")
    public ApiResponse play(@RequestBody SongRequest songRequest){
        Map<String, Object> responseMessage = new HashMap<>();
        Song song=songService.findByAlbumArtistAndAlbumAndTitle(songRequest.getAlbumArtist(),songRequest.getAlbum(),songRequest.getTitle());
        responseMessage.put("ok","播放成功");
        responseMessage.put("song", song);
        return new ApiResponse(true,responseMessage);
    }

    @PostMapping("/search/insonglist")
    public ApiResponse seachInSongList(@RequestBody SongRequest songRequest, HttpSession session) {
        Map<String, Object> responseMessage = new HashMap<>();
        String currentUser = (String) session.getAttribute("username");
        String currentPassword=(String) session.getAttribute("password");
        if (currentUser == null) {
            responseMessage.put("error", "未登录");
            return new ApiResponse(false, responseMessage);
        }
        else{
            User user=userService.findByUsername(currentUser);
            if(!Objects.equals(user.getPassword(), currentPassword)){
                responseMessage.put("error","登录信息错误");
                return new ApiResponse(false, responseMessage);
            }
        }
        System.out.println(currentUser);
        System.out.println(songRequest.getListName());
        List<SongResponse> songsResponses = songListItemsService.findByUsernameAndListName(currentUser,songRequest.getListName());

        responseMessage.put("ok","歌单音乐查询成功");
        responseMessage.put("songs", songsResponses);
        return new ApiResponse(true, responseMessage);
    }
    @PostMapping("/add/tosonglist")
    public ApiResponse addToSongList(@RequestBody SongRequest songRequest,HttpSession session) {
        Map<String, Object> responseMessage = new HashMap<>();

        String currentUser = (String) session.getAttribute("username");
        String currentPassword=(String) session.getAttribute("password");
        if (currentUser == null) {
            responseMessage.put("error", "未登录");
            return new ApiResponse(false, responseMessage);
        }
        else{
            User user=userService.findByUsername(currentUser);
            if(!Objects.equals(user.getPassword(), currentPassword)){
                responseMessage.put("error","登录信息错误");
                return new ApiResponse(false, responseMessage);
            }
        }
            songListItemsService.addSongToList(songRequest.getTitle(),songRequest.getAlbumArtist(),songRequest.getAlbum(),currentUser,songRequest.getListName());
            responseMessage.put("ok", "歌曲已成功添加到歌单");
            return new ApiResponse(true, responseMessage);
    }

    @PostMapping("/delete/fromsonglist")
    public ApiResponse deleteFromSongList(@RequestBody SongRequest songRequest,HttpSession session) {
        Map<String, Object> responseMessage = new HashMap<>();

        String currentUser = (String) session.getAttribute("username");
        String currentPassword=(String) session.getAttribute("password");
        if (currentUser == null) {
            responseMessage.put("error", "未登录");
            return new ApiResponse(false, responseMessage);
        }
        else{
            User user=userService.findByUsername(currentUser);
            if(!Objects.equals(user.getPassword(), currentPassword)){
                responseMessage.put("error","登录信息错误");
                return new ApiResponse(false, responseMessage);
            }
        }
        songListItemsService.deleteSongFromList(songRequest.getTitle(),songRequest.getAlbumArtist(),songRequest.getAlbum(),currentUser,songRequest.getListName());
        responseMessage.put("ok", "歌曲已成功从歌单中删除");
        return new ApiResponse(true, responseMessage);
    }
}
