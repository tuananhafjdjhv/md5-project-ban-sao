package ra.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.entities.entity.Movie;
import ra.repositories.IMovieRepository;
import ra.securities.dto.response.ResponseMessage;
import ra.securities.reponsitory.IUserRepository;
import ra.services.movie.IMovieService;



@RestController
@RequestMapping("/api/admin")
public class AdminApi {
    @Autowired
    private IMovieService movieService;
    @Autowired
    private IMovieRepository movieRepository;
    @Autowired
    private IUserRepository userRepository;
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
        Movie update = movieRepository.findById(Math.toIntExact(movie.getId())).get();
        if (update != null){
            return movieRepository.save(movie);
        }
        return  null;
    }
    @PutMapping("/block-user/{id}")
    public ResponseEntity<ResponseMessage> blockUser(@PathVariable String id){
        userRepository.blockUser(Long.valueOf(id));
        return new ResponseEntity<>(new ResponseMessage("","Block success!!",null), HttpStatus.OK);
    }
    @PutMapping("/unblock-user/{id}")
    public ResponseEntity<ResponseMessage>  unblockUser(@PathVariable String id){
        userRepository.unBlockUser(Long.valueOf(id));
        return new ResponseEntity<>(new ResponseMessage("","Unblock success!!",null), HttpStatus.OK);
    }
}
