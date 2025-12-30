package xyz.mywebs.imusic.playlist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.mywebs.imusic.playlist.entity.SongListItems;

import java.util.List;

@Repository
public interface SongListItemsRepository extends JpaRepository<SongListItems, Long> {
    List<SongListItems> findByUsernameAndListName(String username, String listName);

    List<SongListItems> findByTitleAndAlbumArtistAndAlbumAndUsernameAndListName(
            String title, String albumArtist, String album, String username, String listName);

    boolean existsByTitleAndAlbumArtistAndAlbumAndUsernameAndListName(
            String title, String albumArtist, String album, String username, String listName);
}
