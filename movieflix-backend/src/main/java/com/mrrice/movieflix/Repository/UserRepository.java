package com.mrrice.movieflix.Repository;
import com.mrrice.movieflix.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> { 
  Optional<User> findByUsername(String username);
}
