package lk.epic.restfulAPI.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "Movie Controller", description = "Movie API Endpoint")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping
    @ApiOperation("Get All Movies")
    public ResponseUtil getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping(path = "/{imdb}")
    @ApiOperation("Get Movie by Imdb")
    public ResponseUtil getMovieByIMDB(@PathVariable String imdb) {
        return movieService.getMovieByIMDB(imdb);
    }

    @PostMapping(path = "/add")
    @ApiOperation("Add a New Movie")
    public ResponseUtil addMovie(@RequestBody MovieDTO movieDTO) {
        return movieService.addMovie(movieDTO);
    }

    @PutMapping(path = "/update")
    @ApiOperation("Update a Movie")
    public ResponseUtil updateMovie(@RequestBody MovieDTO movieDTO) {
        return movieService.updateMovie(movieDTO);
    }

    @DeleteMapping(path = "/delete/{imdb}")
    @ApiOperation("Delete a Movie")
    public ResponseUtil deleteMovie(@PathVariable String imdb) {
        return movieService.deleteMovie(imdb);
    }
}
