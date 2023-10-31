package moviebookingsystem.contract.request;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import moviebookingsystem.constant.Genre;

@Getter
@Setter
public class MovieRequest {
    private String name;
    private Genre genre;

    public MovieRequest(){}

    public MovieRequest(String name, Genre genre) {
        this.name = name;
        this.genre = genre;
    }
}
