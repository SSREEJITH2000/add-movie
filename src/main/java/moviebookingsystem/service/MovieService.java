package moviebookingsystem.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import moviebookingsystem.constant.Genre;
import moviebookingsystem.contract.request.BookingRequest;
import moviebookingsystem.contract.request.MovieRequest;
import moviebookingsystem.contract.request.ShowTimeRequest;
import moviebookingsystem.model.Booking;
import moviebookingsystem.model.Movie;
import moviebookingsystem.model.ShowTime;
import moviebookingsystem.repository.BookingRepository;
import moviebookingsystem.repository.MovieRepository;
import moviebookingsystem.repository.ShowTimeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;
    private final ShowTimeRepository showTimeRepository;
    private final BookingRepository bookingRepository;
    private final ModelMapper modelMapper;

    public Long addMovie(MovieRequest request) {
        Movie movie = movieRepository.save(modelMapper.map(request, Movie.class));
        return movie.getId();
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
        } else {
            Movie updatedMovie = movie.get();
            updatedMovie = Movie.builder()
                    .id(updatedMovie.getId())
                    .name(request.getName())
                    .genre(request.getGenre())
                    .build();
            movieRepository.save(updatedMovie);
            return updatedMovie.getId();
        }
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

    public List<Long> addShowTimeToMovie(long movieId, ShowTimeRequest request) {
        Movie movie = getMovieById(movieId);
        List<Long> savedShowTimeIds = new ArrayList<>();

        for (LocalDateTime showTime : request.getShowTimes()) {
            ShowTime newShowTime = ShowTime.builder()
                    .movie(movie)
                    .showTimes(Collections.singletonList(showTime))
                    .build();
            ShowTime savedShowTime = showTimeRepository.save(newShowTime);
            savedShowTimeIds.add(savedShowTime.getId());
        }
        return savedShowTimeIds;
    }

    public List<ShowTime> getAllShowTimesForMovie(Long movieId) {
        Movie movie = getMovieById(movieId);
        return showTimeRepository.findByMovie(movie);
    }

    public void deleteShowTime(Long showTimeId) {
        ShowTime showTime =
                this.showTimeRepository
                        .findById(showTimeId)
                        .orElseThrow(() -> new RuntimeException("ShowTime not found"));
        this.showTimeRepository.delete(showTime);
    }

    public ShowTime getShowTime(Long showtimeId) {
        return showTimeRepository.findById(showtimeId)
                .orElseThrow(() -> new RuntimeException("ShowTime not found with id " + showtimeId));
    }

    public Booking makeBooking(Long movieId, Long showtimeId, BookingRequest bookingRequest) {
        Movie movie = getMovieById(movieId);
        ShowTime showTime = getShowTime(showtimeId);

        if (!showTime.getMovie().equals(movie)) {
            throw new RuntimeException("ShowTime " + showtimeId + " does not belong to Movie " + movieId);
        }

        Booking booking = Booking.builder()
                .showTime(showTime)
                .movie(movie)
                .customerName(bookingRequest.getCustomerName())
                .build();

        return bookingRepository.save(booking);
    }
}
