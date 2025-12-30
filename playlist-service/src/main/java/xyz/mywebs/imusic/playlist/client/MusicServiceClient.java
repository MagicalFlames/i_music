package xyz.mywebs.imusic.playlist.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import xyz.mywebs.imusic.playlist.dto.SongResponse;

import java.util.Map;

@Component
public class MusicServiceClient {
    private final RestTemplate restTemplate;

    @Value("${service.music-url}")
    private String musicServiceUrl;

    public MusicServiceClient() {
        this.restTemplate = new RestTemplate();
    }

    @SuppressWarnings("unchecked")
    public SongResponse getSong(String title, String albumArtist, String album) {
        try {
            String url = musicServiceUrl + "/api/song/get?title=" + title + "&albumArtist=" + albumArtist + "&album=" + album;
            Map<String, Object> response = restTemplate.getForObject(url, Map.class);
            if (response != null && (Boolean) response.get("success")) {
                Map<String, Object> songMap = (Map<String, Object>) response.get("message");
                Map<String, Object> song = (Map<String, Object>) songMap.get("song");
                if (song != null) {
                    return new SongResponse(
                            (String) song.get("filePath"),
                            (String) song.get("coverFilePath"),
                            (String) song.get("title"),
                            (String) song.get("artist"),
                            (String) song.get("album"),
                            (String) song.get("albumArtist"),
                            (String) song.get("year"),
                            (String) song.get("genre"),
                            (String) song.get("trackNumber"),
                            (String) song.get("bitrate"),
                            (String) song.get("duration")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
