package xyz.mywebs.imusic.music.controller;

import org.springframework.web.bind.annotation.*;
import xyz.mywebs.imusic.music.dto.ApiResponse;
import xyz.mywebs.imusic.music.dto.SongRequest;
import xyz.mywebs.imusic.music.dto.SongResponse;
import xyz.mywebs.imusic.music.entity.Song;
import xyz.mywebs.imusic.music.service.SongService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/song")
public class SongController {
    private final SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }

    @PostMapping("/search/all")
    public ApiResponse searchAll(@RequestBody SongRequest songRequest) {
        Map<String, Object> responseMessage = new HashMap<>();

        String searchKey = songRequest.getTitle() != null ? songRequest.getTitle() : songRequest.getAlbumArtist();

        List<Song> songs;
        if (searchKey == null || searchKey.isEmpty()) {
            // 空搜索关键词时返回所有歌曲
            songs = songService.findAll();
        } else {
            songs = songService.findByAlbumArtistOrTitleWithSorting(searchKey);
        }
        List<SongResponse> songResponses = songs.stream()
                .map(this::convertToSongResponse)
                .collect(Collectors.toList());

        responseMessage.put("ok", "查询成功");
        responseMessage.put("songs", songResponses);
        return new ApiResponse(true, responseMessage);
    }

    @PostMapping("/play")
    public ApiResponse play(@RequestBody SongRequest songRequest) {
        Map<String, Object> responseMessage = new HashMap<>();

        Song song = songService.findByAlbumArtistAndAlbumAndTitle(
                songRequest.getAlbumArtist(),
                songRequest.getAlbum(),
                songRequest.getTitle()
        );

        if (song == null) {
            responseMessage.put("error", "歌曲不存在");
            return new ApiResponse(false, responseMessage);
        }

        responseMessage.put("ok", "播放成功");
        responseMessage.put("song", convertToSongResponse(song));
        return new ApiResponse(true, responseMessage);
    }

    @GetMapping("/get")
    public ApiResponse getSong(@RequestParam String title, @RequestParam String albumArtist, @RequestParam String album) {
        Map<String, Object> responseMessage = new HashMap<>();

        Song song = songService.findByAlbumArtistAndAlbumAndTitle(albumArtist, album, title);
        if (song == null) {
            responseMessage.put("error", "歌曲不存在");
            return new ApiResponse(false, responseMessage);
        }

        responseMessage.put("song", convertToSongResponse(song));
        return new ApiResponse(true, responseMessage);
    }

    private SongResponse convertToSongResponse(Song song) {
        return new SongResponse(
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
    }
}
