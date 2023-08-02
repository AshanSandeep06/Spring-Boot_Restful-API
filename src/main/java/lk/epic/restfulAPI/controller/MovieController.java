package lk.epic.restfulAPI.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lk.epic.restfulAPI.dto.LoginDTO;
import lk.epic.restfulAPI.dto.MovieDTO;
import lk.epic.restfulAPI.service.LoginService;
import lk.epic.restfulAPI.service.MovieService;
import lk.epic.restfulAPI.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/movie")
@CrossOrigin
@Api(value = "Movie Controller", protocols = "http")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping
    @ApiOperation(value = "Get All Movies", response = ResponseEntity.class, code = 200)
    public ResponseUtil getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping(path = "/{imdb}")
    @ApiOperation(value = "Get Movie by Imdb", response = ResponseEntity.class, code = 200)
    public ResponseUtil getMovieByIMDB(@PathVariable String imdb) {
        return movieService.getMovieByIMDB(imdb);
    }

    @PostMapping(path = "/add")
    @ApiOperation(value = "Add a New Movie", response = ResponseEntity.class, code = 200)
    public ResponseUtil addMovie(@RequestBody MovieDTO movieDTO) {
        return movieService.addMovie(movieDTO);
    }

    @PutMapping(path = "/update")
    @ApiOperation(value = "Update a Movie", response = ResponseEntity.class, code = 200)
    public ResponseUtil updateMovie(@RequestBody MovieDTO movieDTO) {
        return movieService.updateMovie(movieDTO);
    }

    @DeleteMapping(path = "/delete/{imdb}")
    @ApiOperation(value = "Delete a Movie", response = ResponseEntity.class, code = 200)
    public ResponseUtil deleteMovie(@PathVariable String imdb) {
        return movieService.deleteMovie(imdb);
    }
}
