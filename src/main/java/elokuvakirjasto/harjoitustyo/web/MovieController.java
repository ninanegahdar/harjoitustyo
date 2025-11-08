package elokuvakirjasto.harjoitustyo.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import elokuvakirjasto.harjoitustyo.domain.GenreRepository;
import elokuvakirjasto.harjoitustyo.domain.Movie;
import elokuvakirjasto.harjoitustyo.domain.MovieRepository;
import jakarta.validation.Valid;



@Controller
public class MovieController {

    private MovieRepository movieRepository;
    private GenreRepository genreRepository;

    public MovieController (MovieRepository movieRepository, GenreRepository genreRepository) {
        this.movieRepository = movieRepository;
        this.genreRepository = genreRepository;
    }

    @GetMapping("/")
    public String redirectToMovies() {
        return "redirect:/movies";
    }
    
    @GetMapping("/movies")
    public String movies(Model model) {
        model.addAttribute("movies", movieRepository.findAll());
        model.addAttribute("genres", genreRepository.findAll());
        return "movies";
    }

    @GetMapping("/add")
    public String addMovie(Model model){
        model.addAttribute("movie", new Movie());
        model.addAttribute("genres", genreRepository.findAll());
        return "addmovie";
    }
    // Bean- validointi
    @PostMapping("/save")
    public String saveMovie(@Valid @ModelAttribute("movie") Movie movie,
    BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
        model.addAttribute("genres", genreRepository.findAll());
        return "addmovie";
    }
    movieRepository.save(movie);
    return "redirect:/movies";
}

    @GetMapping("/editMovie/{id}")
    public String editMovie(@PathVariable(name="id") Long id, Model model) {
        Movie movie = movieRepository.findById(id).get();
        model.addAttribute("movie", movie);
        model.addAttribute("genres", genreRepository.findAll());
        return "editMovie";
    }

    @PostMapping("/updateMovie")
    public String updateMovie(Movie movie) {
    movieRepository.save(movie);
    return "redirect:/movies";
    }

    @GetMapping("/delete/{id}")
    public String deleteMovie(@PathVariable("id") Long id) {
        movieRepository.deleteById(id);
        return "redirect:/movies";
    }

    @GetMapping("/search")
    public String searchMovies(@RequestParam(required = false) String keyword, @RequestParam(required = false) Long genreId, Model model) {

    List<Movie> movies = (genreId != null)
        ? movieRepository.findByGenreId(genreId)
        : movieRepository.findByTitleContainingIgnoreCaseOrDirectorContainingIgnoreCase(keyword, keyword);

    model.addAttribute("movies", movies);
    model.addAttribute("genres", genreRepository.findAll());
    return "movies";
    }
}