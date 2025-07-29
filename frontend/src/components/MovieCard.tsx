import './MovieCard.css';

interface MovieCardProps {
  movie: {
    id: number;
    title: string;
    overview: string;
    posterPath: string; // Adjust this if your API uses a different key
  };
  onFavoriteToggle?: (id: number) => void;
  isFavorited?: boolean;
}

const MovieCard = ({ movie, onFavoriteToggle, isFavorited }: MovieCardProps) => {
  const handleFavorite = () => {
    if (onFavoriteToggle) {
      onFavoriteToggle(movie.id);
    }
  };

  return (
    <div className="movie-card">
      <img
        src={movie.posterPath}
        alt={movie.title}
        className="movie-poster"
      />
      <div className="movie-info">
        <h3 className="movie-title">{movie.title}</h3>
        <p className="movie-overview">{movie.overview}</p>
        {onFavoriteToggle && (
          <button className="favorite-button" onClick={handleFavorite}>
            {isFavorited ? '💙' : '🤍'}
          </button>
        )}
      </div>
    </div>
  );
};

export default MovieCard;
