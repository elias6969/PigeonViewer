package com.mrrice.movieflix.Repository;

import com.mrrice.movieflix.Model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {}
