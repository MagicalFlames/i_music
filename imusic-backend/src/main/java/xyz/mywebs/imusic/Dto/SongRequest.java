package xyz.mywebs.imusic.Dto;

public class SongRequest {

    private String title;          // 歌曲标题
    private String albumArtist;    // 专辑艺术家
    private String album;
    private String username;       // 用户名
    private String password;       // 密码
    private String listName;   // 歌单名称

    // 默认构造函数
    public SongRequest() {}

    // 带参构造函数
    public SongRequest(String title, String albumArtist, String artist, String listName) {
        this.title = title;
        this.albumArtist = albumArtist;
        this.album = artist;
        this.listName = listName;
    }

    // Getter 和 Setter 方法
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

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }
}
