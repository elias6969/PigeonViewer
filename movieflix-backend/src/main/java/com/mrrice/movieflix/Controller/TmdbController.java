package com.mrrice.movieflix.Controller;

import com.mrrice.movieflix.Model.Movie;
import com.mrrice.movieflix.service.TmdbService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tmdb")
@CrossOrigin
public class TmdbController {

    private final TmdbService tmdbService;

    public TmdbController(TmdbService tmdbService) {
        this.tmdbService = tmdbService;
    }

    @PostMapping("/fetch")
    public List<Movie> fetchPopularMovies() {
        return tmdbService.fetchAndSavePopularMovies();
    }
}
