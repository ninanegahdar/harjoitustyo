package elokuvakirjasto.harjoitustyo.web;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import elokuvakirjasto.harjoitustyo.domain.GenreRepository;
import elokuvakirjasto.harjoitustyo.domain.Movie;
import elokuvakirjasto.harjoitustyo.domain.MovieRepository;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api")
public class MovieRestController {

    private MovieRepository movieRepository;
    private GenreRepository genreRepository;

    public MovieRestController (MovieRepository movieRepository, GenreRepository genreRepository) {
        this.movieRepository = movieRepository;
        this.genreRepository = genreRepository;
    }
    //Hakee kaikki leffat
    @GetMapping("/movies")
    public List<Movie> getAllMovies() {
        return (List<Movie>) movieRepository.findAll();
    }
    //Hakee tietyn elokuvan id avulla
    @GetMapping("/movies/{id}")
    public Movie getMovieById(@PathVariable Long id) {
    return movieRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Movie not found: " + id));
    }
    // Lisää uusi elokuva
    @PostMapping("/movies")
    public Movie addMovie(@RequestBody @Valid Movie movie) {
        return movieRepository.save(movie);
    }
    // Päivitä
    @PutMapping("/movies/{id}")
    public Movie updateMovie(@PathVariable Long id, @RequestBody @Valid Movie movie) {
        movie.setId(id);
        return movieRepository.save(movie);
    }
    //Poista tietty elokuva
    @DeleteMapping("/movies/{id}")
    public void deleteMovie(@PathVariable Long id) {
        movieRepository.deleteById(id);
    }
    
}