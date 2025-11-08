package elokuvakirjasto.harjoitustyo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import elokuvakirjasto.harjoitustyo.domain.Genre;
import elokuvakirjasto.harjoitustyo.domain.GenreRepository;
import elokuvakirjasto.harjoitustyo.domain.Movie;
import elokuvakirjasto.harjoitustyo.domain.MovieRepository;

@SpringBootApplication
public class HarjoitustyoApplication {
	private static final Logger log = LoggerFactory.getLogger(HarjoitustyoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(HarjoitustyoApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(MovieRepository movieRepository, GenreRepository genreRepository) {
		return (args) -> {
		
		log.info("Save genres");
        Genre action = new Genre("Action", "Toimintaa ja jännitystä");
        Genre comedy = new Genre("Drama", "Tunteita ja tarinoita");
		Genre horror = new Genre("Horror", "Kauhua ja jännitystä");
		Genre thriller = new Genre("Thriller", "Jännitystä ja adrenaliinia");
		Genre romance = new Genre("Romance", "Romantiikkaa ilmassa");

        genreRepository.save(action);
        genreRepository.save(comedy);
		genreRepository.save(horror);
		genreRepository.save(thriller);
		genreRepository.save(romance);

		
		log.info("Save movies");
		Movie movie1 = new Movie("Inception", "Christopher Nolan", 2010, 9.0, action);
        Movie movie2 = new Movie("Mean Girls", "Mark Waters", 2004, 8.5, comedy);
		Movie movie3 = new Movie("Scream", "Wes Craven", 2004, 9.1, horror);
		Movie movie4 = new Movie("Shutter Island", "Martin Scorsese", 2010, 8.9, thriller);
		Movie movie5 = new Movie("The Notebook", "Nick Cassavetes", 2004, 8.2, romance);


		movieRepository.save(movie1);
		movieRepository.save(movie2);
		movieRepository.save(movie3);
		movieRepository.save(movie4);
		movieRepository.save(movie5);
		};
	}

}
