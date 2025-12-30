package xyz.mywebs.imusic.playlist.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "song_list_items")
public class SongListItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String albumArtist;

    @Column(nullable = false)
    private String album;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String listName;

    public SongListItems() {}

    public SongListItems(String title, String albumArtist, String album, String username, String listName) {
        this.title = title;
        this.albumArtist = albumArtist;
        this.album = album;
        this.username = username;
        this.listName = listName;
    }

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
