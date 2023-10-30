package moviebookingsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class ShowTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "start_time")
    @JsonProperty("startTime")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    @JsonProperty("endTime")
    private LocalDateTime endTime;
    @ManyToOne
    @JoinColumn(name = "movie_id")
    @JsonIgnore
    private Movie movie;

    @OneToMany(mappedBy = "showTime", cascade = CascadeType.ALL)
    private List<Booking> bookings;
    

    public ShowTime(){}

    public ShowTime(Long id, LocalDateTime startTime, LocalDateTime endTime, Movie movie) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.movie = movie;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public boolean isSeatAvailable(Seat selectedSeat) {
        return true;
    }

    public void bookSeats(List<Seat> selectedSeats) {
    }
}
