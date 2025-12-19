package xyz.mywebs.imusic.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

@Entity
public class SongListItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 自动生成主键
    private Long id;  // 主键

    @Column(nullable = false)  // 歌曲标题，不允许为空
    private String title;         // 歌曲标题

    @Column(nullable = false)  // 专辑艺术家，不允许为空
    private String albumArtist;   // 专辑艺术家

    @Column(nullable = false)  // 专辑名称，不允许为空
    private String album;         // 专辑名称

    @Column(nullable = false) // 用户名，不允许为空
    private String username;     // 所属用户

    @Column(nullable = false)  // 歌单名称，不允许为空
    private String listName;     // 歌单名称

    // 默认构造函数
    public SongListItems() {}

    // 带参构造函数
    public SongListItems(String title, String albumArtist, String album, String username, String listName) {
        this.title = title;
        this.albumArtist = albumArtist;
        this.album = album;
        this.listName = listName;
        this.username = username;
    }

    // Getter 和 Setter 方法
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlbumArtist() {
        return albumArtist;
    }

    public void setAlbumArtist(String albumArtist) {
        this.albumArtist = albumArtist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }
}
