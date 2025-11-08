package elokuvakirjasto.harjoitustyo;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import elokuvakirjasto.harjoitustyo.domain.Genre;
import elokuvakirjasto.harjoitustyo.domain.GenreRepository;

@DataJpaTest
public class GenreRepositoryTest {

@Autowired
GenreRepository genreRepository;

@Test
public void saveGenreTest() {
    Genre genre = new Genre("Action", "Toimintaleffat");
    genreRepository.save(genre);
    assertNotNull(genre.getId());
}
}
