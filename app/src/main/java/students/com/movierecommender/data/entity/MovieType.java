package students.com.movierecommender.data.entity;

import java.util.List;

/**
 * Created by Kamil Gonska on sty, 2019
 */
public class MovieType {
    private Integer id;
    private String name;
    private List<Movie> movies;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
