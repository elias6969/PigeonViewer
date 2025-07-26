package com.mrrice.movieflix.service;

import org.springframework.stereotype.Service;
import com.mrrice.movieflix.Model.Movie;
import com.mrrice.movieflix.Repository.MovieRepository;

import java.util.List;

@Service
public class MovieService {
  private final MovieRepository movieRepository;

  public MovieService(MovieRepository movieRepository) {
    this.movieRepository = movieRepository;
  }

  public List<Movie> getAllMovies() {
    return movieRepository.findAll();
  }

  public Movie saveMovie(Movie movie) {
    return movieRepository.save(movie);
  }

  public void saveAll(List<Movie> movies) {
    movieRepository.saveAll(movies);
  }

  public Movie getMovieById(Long id) {
    return movieRepository.findById(id).orElse(null);
  }
}
