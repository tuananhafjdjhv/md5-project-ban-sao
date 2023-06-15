package ra.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.dtos.MovieDTO;
import ra.entities.entity.Movie;
import ra.repositories.IMovieRepository;
import ra.services.movie.IMovieService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping( value="/api/movies", produces = "application/json")
@CrossOrigin("*")
public class MovieApi {
    @Autowired
    private IMovieService movieService;
    @Autowired
    private IMovieRepository movieRepository;
    @GetMapping("/showing")
    public ResponseEntity<List<MovieDTO>>  findAllShowingMovies(){
        return new ResponseEntity<>(movieService.findAllShowingMovies(), HttpStatus.OK);
    }
    @GetMapping("/details/{movieId}")
    public Optional<Movie> getMovieById( @PathVariable String movieId){
        return movieService.getById(Integer.valueOf(movieId));
    }

    @GetMapping("/search/{name}")
    public List<MovieDTO> findAllShowingMoviesByName(@PathVariable String name){
        return movieService.findAllShowingMoviesByName(name);
    }


}
