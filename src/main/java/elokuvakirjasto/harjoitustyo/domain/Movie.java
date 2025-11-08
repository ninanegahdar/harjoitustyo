package elokuvakirjasto.harjoitustyo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    

    // Bean-validointi

    @NotBlank(message = "Elokuvan nimi on pakollinen")
    private String title;

    @NotBlank(message = "Ohjaajan nimi on pakollinen")
    private String director;

    @Min(value = 1900, message = "Syötä kelvollinen vuosi")
    @Max(value = 2025, message = "Vuosi ei voi olla tulevaisuudessa")
    private int releaseYear;

    @Min(value = 1, message = "Arvion tulee olla vähintään 1")
    @Max(value = 10, message = "Arvion tulee olla enintään 10")
    private double rating;

    @NotNull(message = "Genre on valittava")
    @ManyToOne
    @JoinColumn(name = "genre_id")
    @JsonIgnoreProperties("movies")
    private Genre genre;


    public Movie() {}

    public Movie(String title, String director, int releaseYear, double rating, Genre genre) {
        this.title = title;
        this.director = director;
        this.releaseYear = releaseYear;
        this.rating = rating;
        this.genre = genre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
    
    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
    

}