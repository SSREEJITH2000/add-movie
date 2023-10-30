package moviebookingsystem.repository;

import moviebookingsystem.model.Seat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepository extends CrudRepository<Seat,Long> {
}
