package moviebookingsystem.repository;

import moviebookingsystem.constant.Genre;
import moviebookingsystem.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findAll();
    Optional<Movie> findById(long id);
    List<Movie> findAllByGenre(Genre genre);
}
