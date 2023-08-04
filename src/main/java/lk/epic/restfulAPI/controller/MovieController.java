package lk.epic.restfulAPI.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import lk.epic.restfulAPI.dto.MovieDTO;
import lk.epic.restfulAPI.service.MovieService;
import lk.epic.restfulAPI.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@RestController
@RequestMapping("/api/v1/movie")
@CrossOrigin
@Api(value = "Movie Controller", tags = "Movies Controller APIs")
@Validated
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping
    @ApiOperation(value = "Get All Movies", notes = "Get All Movies")
    @ApiResponse(code = 200, message = "Success", response = ResponseUtil.class)
    public ResponseUtil getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping(path = "/{imdb}")
    @ApiOperation(value = "Get Movie by Imdb", notes = "Get Movie by Imdb")
    @ApiResponse(code = 200, message = "Success", response = ResponseUtil.class)
    public ResponseUtil getMovieByIMDB(@Valid @NotEmpty @NotBlank
                                       @Size(min = 9, max = 9, message = "Imdb should be fixed with 9 characters")
                                       @Pattern(regexp = "^(tt)[0-9]{7}$", message = "Imdb should be in this pattern eg:-tt0371743, tt0371769")
                                       @PathVariable String imdb) {
        return movieService.getMovieByIMDB(imdb);
    }

    @PostMapping(path = "/add")
    @ApiOperation(value = "Save a New Movie", notes = "Save a New Movie")
    @ApiResponse(code = 200, message = "Success", response = ResponseUtil.class)
    public ResponseUtil addMovie(@Valid @RequestBody MovieDTO movieDTO) {
        return movieService.addMovie(movieDTO);
    }

    @PutMapping(path = "/update")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ApiOperation(value = "Update a Movie", notes = "Update a Movie")
    @ApiResponse(code = 200, message = "Success", response = ResponseUtil.class)
    public ResponseUtil updateMovie(@Valid @RequestBody MovieDTO movieDTO) {
        return movieService.updateMovie(movieDTO);
    }

    @DeleteMapping(path = "/delete/{imdb}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ApiOperation(value = "Delete a Movie", notes = "Delete a Movie")
    @ApiResponse(code = 200, message = "Success", response = ResponseUtil.class)
    public ResponseUtil deleteMovie(@Valid @NotEmpty @NotBlank
                                    @Size(min = 9, max = 9, message = "Imdb should be fixed with 9 characters")
                                    @Pattern(regexp = "^(tt)[0-9]{7}$", message = "Imdb should be in this pattern eg:-tt0371743, tt0371769")
                                    @PathVariable String imdb) {
        return movieService.deleteMovie(imdb);
    }
}
