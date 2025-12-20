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
    private final SongService songService;

    @Autowired
    public SongListItemsService(SongListItemsRepository songListItemsRepository, SongService songService) {
        this.songListItemsRepository = songListItemsRepository;
        this.songService = songService;
    }

    public List<SongResponse> findByUsernameAndListName(String username, String listName) {
        List<SongListItems> songListItemsList = songListItemsRepository.findByUsernameAndListName(username, listName);

        return songListItemsList.stream()
                .map(songListItem -> {
                    Song song = songService.findByAlbumArtistAndAlbumAndTitle(
                            songListItem.getAlbumArtist(),
                            songListItem.getAlbum(),
                            songListItem.getTitle()
                    );
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
                .collect(Collectors.toList());
    }

    public void addSongToList(String title, String albumArtist, String album, String username, String listName) {
        // 检查是否已存在，避免重复添加
        if (!songListItemsRepository.existsByTitleAndAlbumArtistAndAlbumAndUsernameAndListName(
                title, albumArtist, album, username, listName)) {
            SongListItems songListItem = new SongListItems(title, albumArtist, album, username, listName);
            songListItemsRepository.save(songListItem);
        }
    }

    public void deleteSongFromList(String title, String albumArtist, String album, String username, String listName) {
        List<SongListItems> items = songListItemsRepository.findByTitleAndAlbumArtistAndAlbumAndUsernameAndListName(
                title, albumArtist, album, username, listName);
        if (!items.isEmpty()) {
            songListItemsRepository.deleteAll(items);
        }
    }
}
