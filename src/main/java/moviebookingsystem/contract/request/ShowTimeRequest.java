package moviebookingsystem.contract.request;

import lombok.Getter;
import lombok.Setter;
import moviebookingsystem.model.Movie;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ShowTimeRequest {
    private List<LocalDateTime> showTimes;
    private Movie movie;
}
