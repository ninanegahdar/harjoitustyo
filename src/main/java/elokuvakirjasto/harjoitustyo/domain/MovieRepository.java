package elokuvakirjasto.harjoitustyo.domain;
import java.util.List;

import org.springframework.data.repository.CrudRepository;


public interface  MovieRepository extends CrudRepository<Movie, Long>{
    List<Movie> findByTitle(String title);
    List<Movie> findByGenreId(Long genreId);
    List<Movie> findByTitleContainingIgnoreCaseOrDirectorContainingIgnoreCase(String title, String director);
}
