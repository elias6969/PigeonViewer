package com.mrrice.movieflix.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mrrice.movieflix.DTO.MovieResponse;
import com.mrrice.movieflix.Model.Movie;
import com.mrrice.movieflix.Repository.MovieRepository;

import jakarta.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class TmdbService {

  private static final Logger logger = LoggerFactory.getLogger(TmdbService.class);



  @Value("${tmdb.api.key}")
  private String apiKey;

  @PostConstruct
  public void logApiKeyOnStartup() {
    logger.info("TMDB API KEY ON STARTUP: {}", apiKey);
  }

  private final RestTemplate restTemplate;
  private final MovieRepository movieRepository;

  public TmdbService(MovieRepository movieRepository) {
    this.restTemplate = new RestTemplate();
    this.movieRepository = movieRepository;
  }


private MovieResponse mapToResponse(Movie movie) {
    return new MovieResponse(
        movie.getId(),
        movie.getTitle(),
        movie.getOverview(),
        movie.getPosterPath(),
        movie.getReleaseDate(),
        movie.getVoteAverage()
    );
}
  public List<Movie> fetchAndSavePopularMovies() {
    String url = "https://api.themoviedb.org/3/movie/popular?api_key=" + apiKey;
    logger.info("Attempting to fetch popular movies from TMDB...");
    logger.debug("TMDB API key in use: {}", apiKey); // 🔒 remove this in production
    logger.debug("TMDB URL: {}", url);

    List<Movie> savedMovies = new ArrayList<>();

    try {
      String response = restTemplate.getForObject(url, String.class);
      logger.debug("TMDB raw response: {}", response);

      JsonNode results = new ObjectMapper().readTree(response).path("results");

      if (!results.isArray()) {
        logger.warn("TMDB 'results' field is not an array. Something went wrong.");
        return savedMovies;
      }

      for (JsonNode movieNode : results) {
        Movie movie = new Movie();
        movie.setTitle(movieNode.get("title").asText());
        movie.setOverview(movieNode.get("overview").asText());
        movie.setPosterPath(movieNode.get("poster_path").asText());
        movie.setReleaseDate(movieNode.get("release_date").asText());
        movie.setVoteAverage(movieNode.get("vote_average").asDouble());

        Movie saved = movieRepository.save(movie);
        savedMovies.add(saved);

        logger.info("Saved movie: {}", saved.getTitle());
      }

      logger.info("Successfully saved {} movies from TMDB.", savedMovies.size());
    } catch (Exception e) {
      logger.error("Error fetching or processing TMDB data", e);
    }

    return savedMovies;
  }
}
