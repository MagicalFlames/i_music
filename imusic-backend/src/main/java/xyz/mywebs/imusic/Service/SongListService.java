package xyz.mywebs.imusic.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.mywebs.imusic.Entity.SongList;
import xyz.mywebs.imusic.Repository.SongListRepository;

import java.util.List;

@Service
public class SongListService {

    private final SongListRepository songListRepository;

    @Autowired
    public SongListService(SongListRepository songListRepository) {
        this.songListRepository = songListRepository;
    }

    public void createSongList(String username,String listName) {
        SongList songList=new SongList(listName,username);
        songListRepository.save(songList);
    }

    // 根据用户名查询用户的歌单
    public List<SongList> getSongListsByUsername(String username) {
        return songListRepository.findByUsername(username);
    }

    // 删除歌单
    public void deleteSongList(String username,String listName) {
        SongList songList = songListRepository.findByUsernameAndListName(username,listName);
        songListRepository.delete(songList);
    }
}
