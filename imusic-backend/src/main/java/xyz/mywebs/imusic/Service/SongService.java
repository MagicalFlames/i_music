package xyz.mywebs.imusic.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import xyz.mywebs.imusic.Entity.Song;
import xyz.mywebs.imusic.Repository.SongRepository;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class SongService {

    private final SongRepository songRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public SongService(SongRepository songRepository, ObjectMapper objectMapper) {
        this.songRepository = songRepository;
        this.objectMapper = objectMapper;
    }

    @PostConstruct
    public void init() {
        updateSongsFromJson();  // 启动时执行一次更新
    }

    public Song findByAlbumArtistAndAlbumAndTitle(String albumArtist, String album, String title) {
        return songRepository.findByAlbumArtistAndAlbumAndTitle(albumArtist, album, title);
    }

    public List<Song> findByAlbumArtistOrTitleWithSorting(String songIdentifier) {
        List<Song> songs = songRepository.findByAlbumArtistOrTitle(songIdentifier);
        // 按匹配度降序
        songs.sort((s1, s2) -> {
            int score1 = calculateMatchScore(s1, songIdentifier);
            int score2 = calculateMatchScore(s2, songIdentifier);
            return Integer.compare(score2, score1); // 降序排列
        });

        return songs;
    }
    private int calculateMatchScore(Song song, String songIdentifier) {
        int score = 0;
        if (song.getTitle().contains(songIdentifier)) score++;
        if (song.getAlbumArtist().contains(songIdentifier)) score++;
        return score;
    }

    public void updateSongsFromJson() {
        try {
            // 读取 JSON 文件
            ClassPathResource resource = new ClassPathResource("music_info.json");
            File jsonFile = resource.getFile();
            List<Song> songs = objectMapper.readValue(jsonFile, objectMapper.getTypeFactory().constructCollectionType(List.class, Song.class));
            System.out.println("歌曲已更新");
            // 更新数据库中的歌曲信息
            for (Song song : songs) {
                Song existingSong = songRepository.findByAlbumArtistAndAlbumAndTitle(
                        song.getAlbumArtist(), song.getAlbum(), song.getTitle());
                if (existingSong == null) {
                    songRepository.save(song);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
