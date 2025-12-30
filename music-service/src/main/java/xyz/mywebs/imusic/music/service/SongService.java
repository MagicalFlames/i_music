package xyz.mywebs.imusic.music.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import xyz.mywebs.imusic.music.entity.Song;
import xyz.mywebs.imusic.music.repository.SongRepository;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class SongService {
    private final SongRepository songRepository;
    private final ObjectMapper objectMapper;

    public SongService(SongRepository songRepository, ObjectMapper objectMapper) {
        this.songRepository = songRepository;
        this.objectMapper = objectMapper;
    }

    @PostConstruct
    public void init() {
        updateSongsFromJson();
    }

    public Song findByAlbumArtistAndAlbumAndTitle(String albumArtist, String album, String title) {
        return songRepository.findByAlbumArtistAndAlbumAndTitle(albumArtist, album, title);
    }

    public List<Song> findAll() {
        return songRepository.findAll();
    }

    public List<Song> findByAlbumArtistOrTitleWithSorting(String songIdentifier) {
        List<Song> songs = songRepository.findByAlbumArtistOrTitle(songIdentifier);
        songs.sort((s1, s2) -> {
            int score1 = calculateMatchScore(s1, songIdentifier);
            int score2 = calculateMatchScore(s2, songIdentifier);
            return Integer.compare(score2, score1);
        });
        return songs;
    }

    private int calculateMatchScore(Song song, String songIdentifier) {
        int score = 0;
        if (song.getTitle().contains(songIdentifier)) {
            score++;
        }
        if (song.getAlbumArtist().contains(songIdentifier)) {
            score++;
        }
        return score;
    }

    public void updateSongsFromJson() {
        try {
            ClassPathResource resource = new ClassPathResource("music_info.json");
            File jsonFile = resource.getFile();
            List<Song> songs = objectMapper.readValue(jsonFile,
                    objectMapper.getTypeFactory().constructCollectionType(List.class, Song.class));

            for (Song song : songs) {
                Song existingSong = songRepository.findByAlbumArtistAndAlbumAndTitle(
                        song.getAlbumArtist(), song.getAlbum(), song.getTitle());
                if (existingSong == null) {
                    songRepository.save(song);
                }
            }
        } catch (IOException e) {
            System.out.println("music_info.json not found, skipping initial data load");
        }
    }
}
