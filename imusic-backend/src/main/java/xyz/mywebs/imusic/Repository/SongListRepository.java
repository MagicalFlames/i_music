package xyz.mywebs.imusic.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.mywebs.imusic.Entity.SongList;

import java.util.List;

@Repository
public interface SongListRepository extends JpaRepository<SongList, Long> {
    // 根据用户名查询
    List<SongList> findByUsername(String username);

    SongList findByUsernameAndListName(String username,String listName);
}
