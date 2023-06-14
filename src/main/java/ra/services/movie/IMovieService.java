package ra.services.movie;

import ra.dtos.MovieDTO;
import ra.entities.entity.Movie;

import java.util.List;
import java.util.Optional;

public interface IMovieService {
    List<MovieDTO> findAllShowingMovies();
    Optional<Movie> getById(Integer movieId);
    List<MovieDTO> findAllShowingMoviesByName(String name);
}
