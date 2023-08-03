package lk.epic.restfulAPI.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lk.epic.restfulAPI.dto.MovieDTO;
import lk.epic.restfulAPI.service.MovieService;
import lk.epic.restfulAPI.util.ResponseUtil;
import lk.epic.restfulAPI.util.ValidImdb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public ResponseUtil getMovieByIMDB(@ValidImdb @Valid @PathVariable String imdb) {
        return movieService.getMovieByIMDB(imdb);
    }

    @PostMapping(path = "/add")
    @ApiOperation(value = "Add a New Movie", response = ResponseEntity.class, code = 200)
    public ResponseUtil addMovie(@Valid @RequestBody MovieDTO movieDTO) {
        return movieService.addMovie(movieDTO);
    }

    @PutMapping(path = "/update")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ApiOperation(value = "Update a Movie", response = ResponseEntity.class, code = 200)
    public ResponseUtil updateMovie(@Valid @RequestBody MovieDTO movieDTO) {
        return movieService.updateMovie(movieDTO);
    }

    @DeleteMapping(path = "/delete/{imdb}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ApiOperation(value = "Delete a Movie", response = ResponseEntity.class, code = 200)
    public ResponseUtil deleteMovie(@ValidImdb @Valid @PathVariable String imdb) {
        return movieService.deleteMovie(imdb);
    }
}
