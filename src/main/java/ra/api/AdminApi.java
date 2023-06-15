package ra.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ra.entities.entity.Movie;
import ra.repositories.IMovieRepository;
import ra.services.movie.IMovieService;



@RestController
@RequestMapping("/api/admin")
public class AdminApi {
    @Autowired
    private IMovieService movieService;
    @Autowired
    private IMovieRepository movieRepository;
    @PostMapping("/create")
    public void createMovie(@RequestBody Movie movie){
        movieRepository.save(movie);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteMovie(@PathVariable String id){
        movieRepository.deleteById(Integer.valueOf(id));
    }
    @PutMapping("/update")
    public Movie updateMovie(@RequestBody Movie movie){
        Movie update = movieRepository.findById(movie.getId()).get();
        if (update != null){
            return movieRepository.save(movie);
        }
        return  null;
    }
}
