package moviebookingsystem.repository;

import moviebookingsystem.model.ShowTime;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowTimeRepository extends CrudRepository<ShowTime,Long> {
}
