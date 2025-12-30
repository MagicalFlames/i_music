package xyz.mywebs.imusic.playlist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.mywebs.imusic.playlist.entity.SongList;

import java.util.List;

@Repository
public interface SongListRepository extends JpaRepository<SongList, Long> {
    List<SongList> findByUsername(String username);
    SongList findByUsernameAndListName(String username, String listName);
}
