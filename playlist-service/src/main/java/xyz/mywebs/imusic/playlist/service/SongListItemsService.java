package xyz.mywebs.imusic.playlist.service;

import org.springframework.stereotype.Service;
import xyz.mywebs.imusic.playlist.client.MusicServiceClient;
import xyz.mywebs.imusic.playlist.dto.SongResponse;
import xyz.mywebs.imusic.playlist.entity.SongListItems;
import xyz.mywebs.imusic.playlist.repository.SongListItemsRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SongListItemsService {
    private final SongListItemsRepository songListItemsRepository;
    private final MusicServiceClient musicServiceClient;

    public SongListItemsService(SongListItemsRepository songListItemsRepository, MusicServiceClient musicServiceClient) {
        this.songListItemsRepository = songListItemsRepository;
        this.musicServiceClient = musicServiceClient;
    }

    public List<SongResponse> findByUsernameAndListName(String username, String listName) {
        List<SongListItems> songListItemsList = songListItemsRepository.findByUsernameAndListName(username, listName);

        return songListItemsList.stream()
                .map(songListItem -> musicServiceClient.getSong(
                        songListItem.getTitle(),
                        songListItem.getAlbumArtist(),
                        songListItem.getAlbum()
                ))
                .filter(song -> song != null)
                .collect(Collectors.toList());
    }

    public void addSongToList(String title, String albumArtist, String album, String username, String listName) {
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
