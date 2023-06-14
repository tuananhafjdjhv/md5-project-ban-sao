package ra.services.movie;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.dtos.MovieDTO;
import ra.entities.entity.Movie;
import ra.repositories.IMovieRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieService implements IMovieService{
    @Autowired
    private IMovieRepository movieRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public List<MovieDTO> findAllShowingMovies() {
        return movieRepository.findMoviesByIsShowingOrderByIdDesc(1)
                .stream()
                .map(movie -> modelMapper.map(movie, MovieDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Movie> getById(Integer movieId) {
        return movieRepository.findById(movieId);
    }

    @Override
    public List<MovieDTO> findAllShowingMoviesByName(String name) {
        return movieRepository.findMoviesByIsShowingAndNameContaining(1,name)
                .stream().map(movie -> modelMapper.map(movie , MovieDTO.class))
                .collect(Collectors.toList());
    }
}
