package lk.epic.restfulAPI.controller;

import lk.epic.restfulAPI.dto.LoginDTO;
import lk.epic.restfulAPI.dto.MovieDTO;
import lk.epic.restfulAPI.service.LoginService;
import lk.epic.restfulAPI.service.MovieService;
import lk.epic.restfulAPI.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/movie")
@CrossOrigin
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping
    public ResponseUtil getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping(path = "/{imdb}")
    public ResponseUtil getMovieByIMDB(@PathVariable String imdb) {
        return movieService.getMovieByIMDB(imdb);
    }

    @PostMapping(path = "/add")
    public ResponseUtil addMovie(@RequestBody MovieDTO movieDTO) {
        return movieService.addMovie(movieDTO);
    }

    @PutMapping(path = "/update")
    public ResponseUtil updateMovie(@RequestBody MovieDTO movieDTO) {
        return movieService.updateMovie(movieDTO);
    }

    @DeleteMapping(path = "/delete/{imdb}")
    public ResponseUtil deleteMovie(@PathVariable String imdb) {
        return movieService.deleteMovie(imdb);
    }
}
