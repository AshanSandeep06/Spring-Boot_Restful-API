package lk.epic.restfulAPI.service;

import lk.epic.restfulAPI.dto.MovieDTO;
import lk.epic.restfulAPI.util.ResponseUtil;

import java.util.List;

public interface MovieService {
    ResponseUtil getAllMovies();
    ResponseUtil getMovieByIMDB(String imdb);
    ResponseUtil addMovie(MovieDTO movieDTO);
    ResponseUtil updateMovie(MovieDTO movieDTO);
    ResponseUtil deleteMovie(String imdb);
}
