package xyz.mywebs.imusic.playlist.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "song_list")
public class SongList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String listName;

    @Column(nullable = false)
    private String username;

    public SongList() {}

    public SongList(String listName, String username) {
        this.listName = listName;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
