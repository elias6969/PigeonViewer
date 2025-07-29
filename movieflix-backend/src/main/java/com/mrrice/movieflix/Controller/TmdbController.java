package com.mrrice.movieflix.Controller;

import com.mrrice.movieflix.Model.Movie;
import com.mrrice.movieflix.DTO.MovieResponse;
import com.mrrice.movieflix.service.TmdbService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tmdb")
public class TmdbController {

  private final TmdbService tmdbService;

  public TmdbController(TmdbService tmdbService) {
    this.tmdbService = tmdbService;
  }

  @GetMapping("/fetch")
  public List<Movie> fetchPopularMovies() {
    System.out.println("🔥 /api/tmdb/fetch GET endpoint hit");
    return tmdbService.fetchAndSavePopularMovies();
  }

  @RequestMapping(value = "/clean-fetch", method = { RequestMethod.GET, RequestMethod.POST })
  public List<MovieResponse> fetchPrettyMovies() {
    System.out.println("🌈 /api/tmdb/clean-fetch endpoint hit");
    List<Movie> movies = tmdbService.fetchAndSavePopularMovies();
    return movies.stream()
        .map(this::mapToResponse)
        .collect(Collectors.toList());
  }

  private MovieResponse mapToResponse(Movie movie) {
    return new MovieResponse(
        movie.getId(),
        movie.getTitle(),
        movie.getOverview(),
        movie.getPosterPath(),
        movie.getReleaseDate(),
        movie.getVoteAverage());
  }
}
