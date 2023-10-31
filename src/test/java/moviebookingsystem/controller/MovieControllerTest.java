package moviebookingsystem.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.List;
import moviebookingsystem.constant.Genre;
import moviebookingsystem.contract.request.MovieRequest;
import moviebookingsystem.model.Movie;
import moviebookingsystem.model.ShowTime;
import moviebookingsystem.service.MovieService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class MovieControllerTest {
    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;
    @MockBean private MovieService movieService;

    @Test
    void testAddMovie() throws Exception {
        String requestJson = "{\"name\": \"genre\"}";
        Long movieId = 1L;
        when(movieService.addMovie(any(MovieRequest.class))).thenReturn(movieId);

        mockMvc.perform(
                        post("/movies")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(movieId));
        verify(movieService, times(1)).addMovie(any(MovieRequest.class));
    }

    @Test
    void testGetAllMovies() throws Exception {
        List<Movie> expectedMovies = Arrays.asList(new Movie(), new Movie());
        when(movieService.getAllMovies()).thenReturn(expectedMovies);
        mockMvc.perform(get("/movies"))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(expectedMovies)));
        verify(movieService, times(1)).getAllMovies();
    }

    @Test
    void testGetMovieById() throws Exception {
        Long movieId = 1L;
        Movie expectedMovie = new Movie();
        expectedMovie.setId(movieId);
        when(movieService.getMovieById(movieId)).thenReturn(expectedMovie);
        mockMvc.perform(get("/movies/" + movieId))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(expectedMovie)));
        verify(movieService, times(1)).getMovieById(movieId);
    }

    @Test
    void testUpdateMovieById() throws Exception {
        long movieId = 1L;
        String requestJson = "{\"name\": \"genre\"}";
        MovieRequest movieRequest = new MovieRequest();
        Movie updatedMovie = new Movie();
        updatedMovie.setId(movieId);
        when(movieService.updateMovieById(eq(movieId), any(MovieRequest.class)))
                .thenReturn(updatedMovie.getId());
        mockMvc.perform(
                        put("/movies/" + movieId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(movieId));
        verify(movieService, times(1)).updateMovieById(eq(movieId), any(MovieRequest.class));
    }

    @Test
    void testDeleteMovieById() throws Exception {
        long movieId = 1L;
        doNothing().when(movieService).deleteMovieById(movieId);
        mockMvc.perform(delete("/movies/" + movieId)).andExpect(status().isOk());
        verify(movieService, times(1)).deleteMovieById(movieId);
    }

    @Test
    void testGetAllMoviesByGenre() throws Exception {
        Genre genre = Genre.ACTION;
        List<Movie> movies = Arrays.asList(new Movie());
        when(movieService.getAllMoviesByGenre(genre)).thenReturn(movies);
        mockMvc.perform(get("/movies/genre/" + genre))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
        verify(movieService, times(1)).getAllMoviesByGenre(genre);
    }

    @Test
    void testAddShowTimesToMovie() throws Exception {
        Long movieId = 1L;
        List<ShowTime> showTimes = Arrays.asList(new ShowTime(), new ShowTime());

        when(movieService.addShowTimesToMovie(movieId, showTimes)).thenReturn(movieId);

        mockMvc.perform(
                        post("/movies/{id}/showTimes", movieId)
                                .contentType("application/json")
                                .content(objectMapper.writeValueAsString(showTimes)))
                .andExpect(status().isOk());
    }

    @Test
    void testGetAllShowTimesForMovie() throws Exception {
        List<ShowTime> expectedShowTimes =
                Arrays.asList(new ShowTime(), new ShowTime(), new ShowTime());
        when(movieService.getAllShowTimesForMovie(anyLong())).thenReturn(expectedShowTimes);
        MovieController movieController = new MovieController(movieService);
        List<ShowTime> actualShowTimes = movieController.getAllShowTimesForMovie(1L);
        assertEquals(expectedShowTimes, actualShowTimes);
    }

    @Test
    void testDeleteShowTime() throws Exception {
        Long id = 1L;
        Long showTimeId = 1L;
        doNothing().when(movieService).deleteShowTime(showTimeId);
        mockMvc.perform(
                        delete("/movies/{id}/showTimes/{showTimeId}", id, showTimeId)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(movieService, times(1)).deleteShowTime(showTimeId);
    }

    @Test
    void testCreateBooking() throws Exception {
        String jsonRequest =
                "{ \"movieId\": 1, \"showTimeId\": 1, \"userId\": 1, \"seats\": [\"A1\", \"A2\"] }";

        mockMvc.perform(
                        post("/movies/1/showTimes/1/bookings")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonRequest))
                .andExpect(status().isOk());
    }
}
