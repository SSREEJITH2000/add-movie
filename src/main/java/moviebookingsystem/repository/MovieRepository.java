package moviebookingsystem.repository;

import moviebookingsystem.constant.Genre;
import moviebookingsystem.model.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Long> {
    List<Movie> findByName(String name);
    List<Movie> findAll();
    Optional<Movie> findById(long id);

    List<Movie> findAllByGenre(Genre genre);
}
