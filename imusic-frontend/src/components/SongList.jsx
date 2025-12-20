import './SongList.css'

function SongList({ songs, favorites, onPlay, onAddToFavorites, onRemoveFromFavorites, currentSong }) {
  const formatDuration = (seconds) => {
    const mins = Math.floor(seconds / 60)
    const secs = seconds % 60
    return `${mins}:${secs.toString().padStart(2, '0')}`
  }

  const isFavorite = (song) => {
    return favorites.some(fav => fav.title === song.title && fav.artist === song.artist)
  }

  const handleFavoriteClick = (song) => {
    if (isFavorite(song)) {
      onRemoveFromFavorites(song)
    } else {
      onAddToFavorites(song)
    }
  }

  if (songs.length === 0) {
    return (
      <div className="empty-state">
        <div className="empty-icon">🎵</div>
        <p className="empty-text">搜索你喜欢的音乐</p>
      </div>
    )
  }

  return (
    <div className="song-list">
      <h2 className="section-title">搜索结果</h2>
      <div className="songs-grid">
        {songs.map((song) => (
          <div
            key={song.id}
            className={`song-card ${currentSong?.id === song.id ? 'playing' : ''}`}
          >
            <div className="song-cover-wrapper">
              <img
                src={song.cover}
                alt={song.title}
                className="song-cover"
                onError={(e) => {
                  console.error('封面加载失败:', song.cover)
                  e.target.src = 'https://via.placeholder.com/300x300/667eea/ffffff?text=No+Cover'
                }}
              />
              <div className="song-overlay">
                <button
                  className="play-btn-large"
                  onClick={() => onPlay(song)}
                >
                  {currentSong?.id === song.id ? '⏸' : '▶'}
                </button>
              </div>
            </div>
            <div className="song-info">
              <h3 className="song-title">{song.title}</h3>
              <p className="song-artist">{song.artist}</p>
              <p className="song-album">{song.album}</p>
              <div className="song-footer">
                <span className="song-duration">{formatDuration(song.duration)}</span>
                <button
                  className={`favorite-btn ${isFavorite(song) ? 'favorited' : ''}`}
                  onClick={() => handleFavoriteClick(song)}
                >
                  {isFavorite(song) ? '❤️' : '🤍'}
                </button>
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  )
}

export default SongList
