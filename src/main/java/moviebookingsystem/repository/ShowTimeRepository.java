package moviebookingsystem.repository;

import moviebookingsystem.model.Movie;
import moviebookingsystem.model.ShowTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowTimeRepository extends JpaRepository<ShowTime, Long> {
    List<ShowTime> findByMovie(Movie movie);
}
