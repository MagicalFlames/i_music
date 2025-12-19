package xyz.mywebs.imusic.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.mywebs.imusic.Dto.SongResponse;
import xyz.mywebs.imusic.Entity.SongListItems;
import xyz.mywebs.imusic.Entity.Song;
import xyz.mywebs.imusic.Repository.SongListItemsRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SongListItemsService {

    private final SongListItemsRepository songListItemsRepository;
    private final SongService songService;  // 注入 SongService

    @Autowired
    public SongListItemsService(SongListItemsRepository songListItemsRepository, SongService songService) {
        this.songListItemsRepository = songListItemsRepository;
        this.songService = songService;
    }

    public List<SongResponse> findByUsernameAndListName(String username, String listName) {
        // 查询 SongListItems 列表
        List<SongListItems> songListItemsList = songListItemsRepository.findByUsernameAndListName(username, listName);

        // 将 SongListItems 列表转换为 SongResponse 列表
        return songListItemsList.stream()
                .map(songListItem -> {
                    // 根据 songListItem.getTitle() 查询歌曲详细信息
                    Song song = songService.findByAlbumArtistAndAlbumAndTitle(
                            songListItem.getAlbumArtist(),
                            songListItem.getAlbum(),
                            songListItem.getTitle()
                    );
                    // 返回 SongResponse，封装 Song 信息
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
                })
                .collect(Collectors.toList());  // 收集为 List<SongResponse>
    }

    public void addSongToList(String title,String albumArtist,String album,String username,String listName) {

        SongListItems songListItem = new SongListItems(
                title,
                albumArtist,
                album,
                username,
                listName
        );
        songListItemsRepository.save(songListItem);
    }

    public void deleteSongFromList(String title,String albumArtist,String album,String username,String listName) {
        SongListItems songListItem=songListItemsRepository.findByTitleAndAlbumArtistAndAlbumAndUsernameAndListName(title,albumArtist,album,username,listName);
        songListItemsRepository.delete(songListItem);
    }
}
