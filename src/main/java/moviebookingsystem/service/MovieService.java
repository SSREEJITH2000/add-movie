package moviebookingsystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import moviebookingsystem.constant.Genre;
import moviebookingsystem.contract.request.BookingRequest;
import moviebookingsystem.contract.request.MovieRequest;
import moviebookingsystem.model.Booking;
import moviebookingsystem.model.Movie;
import moviebookingsystem.model.ShowTime;
import moviebookingsystem.repository.BookingRepository;
import moviebookingsystem.repository.MovieRepository;
import moviebookingsystem.repository.ShowTimeRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;
    private final ShowTimeRepository showTimeRepository;
    private final BookingRepository bookingRepository;

    public Long addMovie(MovieRequest request) {
        Movie entity = new Movie(request.getName(), request.getGenre());
        return this.movieRepository.save(entity).getId();
    }

    public List<Movie> getAllMovies() {
        Iterable<Movie> movies = this.movieRepository.findAll();
        List<Movie> moviesList = new ArrayList<>();
        movies.forEach(moviesList::add);
        return moviesList;
    }

    public Movie getMovieById(long id) {
        Optional<Movie> movie = this.movieRepository.findById(id);
        if (!movie.isPresent()) {
            throw new RuntimeException("Movie not found");
        }
        return movie.get();
    }

    public Long updateMovieById(long id, MovieRequest request) {
        Optional<Movie> movie = this.movieRepository.findById(id);
        if (!movie.isPresent()) {
            throw new RuntimeException("Movie not found");
        }
        Movie existingMovie = movie.get();
        existingMovie.setName(request.getName());
        existingMovie.setGenre(request.getGenre());
        return this.movieRepository.save(existingMovie).getId();
    }

    public void deleteMovieById(long id) {
        if (!movieRepository.existsById(id)) {
            throw new RuntimeException("Movie not found");
        }
        movieRepository.deleteById(id);
    }

    public List<Movie> getAllMoviesByGenre(Genre genre) {
        List<Movie> movies = movieRepository.findAllByGenre(genre);
        return movies;
    }

    public Long addShowTimesToMovie(long movieId, List<ShowTime> showTimes) {
        Optional<Movie> optionalMovie = movieRepository.findById(movieId);

        if (!optionalMovie.isPresent()) {
            throw new RuntimeException("Movie not found");
        }
        Movie movie = optionalMovie.get();
        showTimes.forEach(
                showTime -> {
                    showTime.setMovie(movie);
                    showTimeRepository.save(showTime);
                });
        movie.getShowTimes().addAll(showTimes);
        return movieRepository.save(movie).getId();
    }

    public List<ShowTime> getAllShowTimesForMovie(Long movieId) {
        Movie movie =
                this.movieRepository
                        .findById(movieId)
                        .orElseThrow(() -> new RuntimeException("Movie not found"));
        return movie.getShowTimes();
    }

    public void deleteShowTime(Long showTimeId) {
        ShowTime showTime =
                this.showTimeRepository
                        .findById(showTimeId)
                        .orElseThrow(() -> new RuntimeException("ShowTime not found"));
        this.showTimeRepository.delete(showTime);
    }

    public long createBooking(BookingRequest request) {
        Movie movie =
                movieRepository
                        .findById(request.getMovieId())
                        .orElseThrow(() -> new RuntimeException("Movie not found"));
        ShowTime showTime =
                showTimeRepository
                        .findById(request.getShowTimeId())
                        .orElseThrow(() -> new RuntimeException("ShowTime not found"));
        Booking booking = Booking.builder().showTime(showTime).movie(movie).build();
        bookingRepository.save(booking);
        return booking.getId();
    }
}
