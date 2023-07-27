package lk.epic.restfulAPI.service.impl;

import lk.epic.restfulAPI.dto.MovieDTO;
import lk.epic.restfulAPI.service.MovieService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class MovieServiceImpl implements MovieService {

    @Override
    public List<MovieDTO> getAllMovies() {
        return null;
    }

    @Override
    public MovieDTO getMovieByIMDB() {
        return null;
    }

    @Override
    public void addMovie(MovieDTO movieDTO) {

    }

    @Override
    public void updateMovie(MovieDTO movieDTO) {

    }

    @Override
    public void deleteMovie(String imdb) {

    }
}
