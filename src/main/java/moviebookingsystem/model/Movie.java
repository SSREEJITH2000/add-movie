package moviebookingsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import moviebookingsystem.constant.Genre;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Genre genre;
    @OneToMany(mappedBy = "movie")
    @JsonIgnore
    private List<ShowTime> showTimes = new ArrayList<>();
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<Booking> bookings = new ArrayList<>();

    public Movie(){}
    public Movie(String name, Genre genre) {
        this.name = name;
        this.genre = genre;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void setShowTimes(List<ShowTime> showTimes) {
        this.showTimes = showTimes;
    }
}
