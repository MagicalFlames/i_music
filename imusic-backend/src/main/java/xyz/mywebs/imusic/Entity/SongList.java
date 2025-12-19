package xyz.mywebs.imusic.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

@Entity
public class SongList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 自动生成主键
    private Long id;  // 主键

    @Column(nullable = false)  // 不允许为空
    private String listName;   // 歌单名称

    @Column(nullable = false)  // 不允许为空
    private String username;   // 创建歌单的用户名

    // 默认构造函数
    public SongList() {}

    // 带参构造函数
    public SongList(String listName, String username) {
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
