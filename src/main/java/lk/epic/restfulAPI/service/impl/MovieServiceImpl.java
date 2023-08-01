package lk.epic.restfulAPI.service.impl;

import lk.epic.restfulAPI.dto.MovieDTO;
import lk.epic.restfulAPI.entity.Movie;
import lk.epic.restfulAPI.repo.MovieRepo;
import lk.epic.restfulAPI.service.MovieService;
import lk.epic.restfulAPI.util.ResponseUtil;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MovieServiceImpl implements MovieService {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private MovieRepo movieRepo;

    @Override
    public ResponseUtil getAllMovies() {
        // First we should check JWT token can Authorized or not
        // return new ResponseUtil("03", "Not Authorized", null);

        List<Movie> allMovies = movieRepo.findAll();
        if (allMovies.size() != 0) {
            return new ResponseUtil("00", "Success", mapper.map(allMovies, new TypeToken<List<MovieDTO>>() {
            }.getType()));
        } else {
            return new ResponseUtil("02", "No Movies Found", null);
        }
    }

    @Override
    public ResponseUtil getMovieByIMDB(String imdb) {
        // First we should check JWT token can Authorized or not
        // return new ResponseUtil("03", "Not Authorized", null);

        if (movieRepo.existsById(imdb)) {
            return new ResponseUtil("00", "Success", movieRepo.findById(imdb));
        } else {
            return new ResponseUtil("02", "No Such Movie Found", null);
        }
    }

    @Override
    public ResponseUtil addMovie(MovieDTO movieDTO) {
        // First we should check JWT token can Authorized or not
        // return new ResponseUtil("03", "Not Authorized", null);

        if (!movieRepo.existsById(movieDTO.getImdb())) {
            movieRepo.save(mapper.map(movieDTO, Movie.class));
            return new ResponseUtil("00", "Success", null);
        } else {
            return new ResponseUtil("04", "Movie Already Exists", null);
        }
    }

    @Override
    public ResponseUtil updateMovie(MovieDTO movieDTO) {
        // First we should check JWT token can Authorized or not
        // return new ResponseUtil("03", "Not Authorized", null);

        if (movieRepo.existsById(movieDTO.getImdb())) {
            Movie movie = movieRepo.findById(movieDTO.getImdb()).get();
            if (movie != null) {
                movie.setCategory(movieDTO.getCategory());
                movie.setTitle(movieDTO.getTitle());
                movie.setDescription(movieDTO.getDescription());
                movie.setRating(movieDTO.getRating());
                movie.setYear(movieDTO.getYear());
                movie.setImageUrl(movieDTO.getImageUrl());

                movieRepo.save(movie);
            }
            return new ResponseUtil("00", "Success", null);
        } else {
            return new ResponseUtil("02", "No Such Movie Exists", null);
        }
    }

    @Override
    public ResponseUtil deleteMovie(String imdb) {
        // First we should check JWT token can Authorized or not
        // return new ResponseUtil("03", "Not Authorized", null);

        if (movieRepo.existsById(imdb)) {
            movieRepo.deleteById(imdb);
            if (!movieRepo.existsById(imdb)) {
                return new ResponseUtil("00", "Success", null);
            } else {
                return new ResponseUtil("03", "Movie was not Deleted", null);
            }
        } else {
            return new ResponseUtil("02", "No Such Movie Exists", null);
        }
    }
}
