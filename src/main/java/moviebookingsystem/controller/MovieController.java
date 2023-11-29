package moviebookingsystem.controller;

import java.util.List;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import moviebookingsystem.constant.Genre;
import moviebookingsystem.contract.request.BookingRequest;
import moviebookingsystem.contract.request.MovieRequest;
import moviebookingsystem.contract.request.ShowTimeRequest;
import moviebookingsystem.model.Booking;
import moviebookingsystem.model.Movie;
import moviebookingsystem.model.ShowTime;
import moviebookingsystem.service.MovieService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @PostMapping
    public @ResponseBody Long addMovie(@Valid @RequestBody MovieRequest request) {
        return this.movieService.addMovie(request);
    }

    @GetMapping
    public @ResponseBody List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/{id}")
    public @ResponseBody Movie getMovieById(@PathVariable Long id) {
        return this.movieService.getMovieById(id);
    }

    @PutMapping("/{id}")
    public @ResponseBody Long updateMovieById(
            @PathVariable long id,@Valid @RequestBody MovieRequest request) {
        return movieService.updateMovieById(id, request);
    }

    @DeleteMapping("/{id}")
    public @ResponseBody void deleteMovieById(@PathVariable long id) {
        movieService.deleteMovieById(id);
    }

    @GetMapping("/genre/{genre}")
    public @ResponseBody List<Movie> getAllMoviesByGenre(@PathVariable Genre genre) {
        return movieService.getAllMoviesByGenre(genre);
    }

    @PostMapping("/{movieId}/showTimes")
    public @ResponseBody List<Long> addShowTimeToMovie(@PathVariable Long movieId, @RequestBody ShowTimeRequest request) {
        return movieService.addShowTimeToMovie(movieId, request);
    }

    @GetMapping("/{id}/showTimes")
    public @ResponseBody List<ShowTime> getAllShowTimesForMovie(@PathVariable long id) {
        return movieService.getAllShowTimesForMovie(id);
    }

    @DeleteMapping("/{id}/showTimes/{showTimeId}")
    public void deleteShowTime(@PathVariable Long showTimeId) {
        movieService.deleteShowTime(showTimeId);
    }

    @GetMapping("/showTime/{id}")
    public @ResponseBody ShowTime getShowtime(@PathVariable Long id) {
        return movieService.getShowTime(id);
    }

    @PostMapping("/{id}/showTimes/{showTimeId}/bookings")
    public @ResponseBody Booking makeBooking(@PathVariable Long id, @PathVariable Long showTimeId, @RequestBody BookingRequest booking) {
        return movieService.makeBooking(id, showTimeId, booking);
    }
}
