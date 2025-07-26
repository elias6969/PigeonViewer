package com.mrrice.movieflix.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mrrice.movieflix.Model.Movie;
import com.mrrice.movieflix.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class TmdbService {

    @Value("${tmdb.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;
    private final MovieRepository movieRepository;

    public TmdbService(MovieRepository movieRepository) {
        this.restTemplate = new RestTemplate();
        this.movieRepository = movieRepository;
    }

    public List<Movie> fetchAndSavePopularMovies() {
        String url = "https://api.themoviedb.org/3/movie/popular?api_key=" + apiKey;

        List<Movie> savedMovies = new ArrayList<>();

        try {
            String response = restTemplate.getForObject(url, String.class);
            JsonNode results = new ObjectMapper().readTree(response).path("results");

            for (JsonNode movieNode : results) {
                Movie movie = new Movie();
                movie.setTitle(movieNode.get("title").asText());
                movie.setOverview(movieNode.get("overview").asText());
                movie.setPosterPath(movieNode.get("poster_path").asText());
                movie.setReleaseDate(movieNode.get("release_date").asText());
                movie.setVoteAverage(movieNode.get("vote_average").asDouble());

                savedMovies.add(movieRepository.save(movie));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return savedMovies;
    }
}
