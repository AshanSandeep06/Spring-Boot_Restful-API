package lk.epic.restfulAPI.service;

import lk.epic.restfulAPI.dto.MovieDTO;
import java.util.List;

public interface MovieService {
    List<MovieDTO> getAllMovies();
    MovieDTO getMovieByIMDB();
    void addMovie(MovieDTO movieDTO);
    void updateMovie(MovieDTO movieDTO);
    void deleteMovie(String imdb);
}
