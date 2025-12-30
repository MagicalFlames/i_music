package xyz.mywebs.imusic.playlist.service;

import org.springframework.stereotype.Service;
import xyz.mywebs.imusic.playlist.entity.SongList;
import xyz.mywebs.imusic.playlist.repository.SongListRepository;

import java.util.List;

@Service
public class SongListService {
    private final SongListRepository songListRepository;

    public SongListService(SongListRepository songListRepository) {
        this.songListRepository = songListRepository;
    }

    public void createSongList(String username, String listName) {
        SongList songList = new SongList(listName, username);
        songListRepository.save(songList);
    }

    public List<SongList> getSongListsByUsername(String username) {
        return songListRepository.findByUsername(username);
    }

    public void deleteSongList(String username, String listName) {
        SongList songList = songListRepository.findByUsernameAndListName(username, listName);
        if (songList != null) {
            songListRepository.delete(songList);
        }
    }

    public boolean existsByUsernameAndListName(String username, String listName) {
        return songListRepository.findByUsernameAndListName(username, listName) != null;
    }
}
