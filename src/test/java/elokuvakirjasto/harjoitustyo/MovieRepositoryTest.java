package elokuvakirjasto.harjoitustyo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import elokuvakirjasto.harjoitustyo.domain.Genre;
import elokuvakirjasto.harjoitustyo.domain.GenreRepository;
import elokuvakirjasto.harjoitustyo.domain.Movie;
import elokuvakirjasto.harjoitustyo.domain.MovieRepository;

@DataJpaTest
public class MovieRepositoryTest {

@Autowired
private MovieRepository movieRepository;

@Autowired
private GenreRepository genreRepository;

@Test
public void saveMovieTest() {
    Genre genre = new Genre("Action");
    genreRepository.save(genre);

    Movie movie = new Movie("Inception", "Christopher Nolan", 2010, 9.0, genre);
    movieRepository.save(movie);

    assertNotNull(movie.getId());
    }

@Test
public void findMovieByIdTest() {
    Genre genre = new Genre("Sci-Fi");
    genreRepository.save(genre);

    Movie movie = new Movie("Matrix", "Wachowski", 1999, 8.7, genre);
    movieRepository.save(movie);

    Movie foundMovie = movieRepository.findById(movie.getId()).orElse(null);

    assertNotNull(foundMovie);
    assertEquals("Matrix", foundMovie.getTitle());
}

@Test
public void deleteMovieTest() { //ei löydä poistettua elokuvaa tietokannasta
    Genre genre = new Genre("Drama");
    genreRepository.save(genre);

    Movie movie = new Movie("Titanic", "James Cameron", 1997, 9.5, genre);
    movieRepository.save(movie);

    Long id = movie.getId();
    movieRepository.deleteById(id);
    Movie deletedMovie = movieRepository.findById(id).orElse(null);

    assertNull(deletedMovie);
    }

}
