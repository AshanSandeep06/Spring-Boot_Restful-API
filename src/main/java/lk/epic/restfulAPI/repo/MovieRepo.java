package lk.epic.restfulAPI.repo;

import lk.epic.restfulAPI.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepo extends JpaRepository<Movie, String> {

}
