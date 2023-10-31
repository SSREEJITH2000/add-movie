# Movie Booking API

This API allows you to manage movies, showTimes, and bookings.

## Endpoints

### Movies

- `GET /movies`: Retrieve all movies.
- `POST /movies`: Add a new movie.
- `GET /movies/{id}`: Retrieve a movie by ID.
- `PUT /movies/{id}`: Update a movie by ID.
- `DELETE /movies/{id}`: Delete a movie by ID.
- `GET /movies/genre/{genre}`: Retrieve movies by genre.

### ShowTimes

- `POST /movies/{id}/showtimes`: Add a showtime for a movie.
- `GET /movies/{id}/showtimes`: Retrieve all showTimes of a movie.
- `DELETE /movies/{id}/showtimes/{showtimeId}`: Delete a showtime.

### Bookings

- `POST /movies/{id}/showtimes/{showtimeId}/bookings`: Make a booking for a showtime.

## Usage

To use this API, send an HTTP request to the desired endpoint. For endpoints that require an ID, replace `{id}` or `{showtimeId}` with the actual ID. For endpoints that require a genre, replace `{genre}` with the actual genre.

For `POST` and `PUT` requests, include the necessary data in the request body. For `DELETE` requests, no additional data is needed.

## Errors

If an error occurs, the API will return an HTTP status code and a message describing the error. For example, if you try to retrieve a movie that does not exist, you will receive a "Movie not found" error.
