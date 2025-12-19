package xyz.mywebs.imusic.Dto;

public class SongResponse {

    private String filePath;        // 文件路径
    private String coverFilePath;   //封面图片路径
    private String title;           // 歌曲标题
    private String artist;          // 歌手
    private String album;           // 专辑
    private String albumArtist;     // 专辑艺术家
    private String year;            // 发布年份
    private String genre;           // 音乐类型
    private String trackNumber;     // 曲目编号
    private String bitrate;         // 比特率
    private String duration;        // 时长

    // 默认构造函数
    public SongResponse() {}

    // 带参构造函数
    public SongResponse(String filePath, String coverFilePath, String title, String artist, String album,
                        String albumArtist, String year, String genre, String trackNumber,
                        String bitrate, String duration) {
        this.filePath = filePath;
        this.coverFilePath = coverFilePath;
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.albumArtist = albumArtist;
        this.year = year;
        this.genre = genre;
        this.trackNumber = trackNumber;
        this.bitrate = bitrate;
        this.duration = duration;
    }

    // Getter 和 Setter 方法
    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getCoverFilePath() {
        return coverFilePath;
    }

    public void setCoverFilePath(String coverFilePath) {
        this.coverFilePath = coverFilePath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getAlbumArtist() {
        return albumArtist;
    }

    public void setAlbumArtist(String albumArtist) {
        this.albumArtist = albumArtist;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(String trackNumber) {
        this.trackNumber = trackNumber;
    }

    public String getBitrate() {
        return bitrate;
    }

    public void setBitrate(String bitrate) {
        this.bitrate = bitrate;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
