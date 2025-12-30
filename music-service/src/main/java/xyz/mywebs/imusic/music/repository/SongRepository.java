package xyz.mywebs.imusic.music.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import xyz.mywebs.imusic.music.entity.Song;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
    Song findByAlbumArtistAndAlbumAndTitle(String albumArtist, String album, String title);

    @Query("SELECT s FROM Song s WHERE s.title LIKE %:songIdentifier% OR s.albumArtist LIKE %:songIdentifier%")
    List<Song> findByAlbumArtistOrTitle(@Param("songIdentifier") String songIdentifier);
}
