package xyz.mywebs.imusic.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.mywebs.imusic.Entity.SongListItems;

import java.util.List;

public interface SongListItemsRepository extends JpaRepository<SongListItems, Long> {
    List<SongListItems> findByUsernameAndListName(String username, String listName);
    List<SongListItems> findByTitleAndAlbumArtistAndAlbumAndUsernameAndListName(
            String title, String albumArtist, String album, String username, String listName);
    boolean existsByTitleAndAlbumArtistAndAlbumAndUsernameAndListName(
            String title, String albumArtist, String album, String username, String listName);
}
