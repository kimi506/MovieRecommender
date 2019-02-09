package students.com.movierecommender.data.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by Kamil Gonska on sty, 2019
 */
@Entity
public class Movie implements Serializable {
    @PrimaryKey
    private Integer id;
    private String description;
    private String name;
    private Float rating;
    private byte[] frontImage;
    private Integer productionYear;
    @Ignore
    private Review reviews;
    @Ignore
    private MovieType movieTypes;
    @Ignore
    private Director directors;
    @Ignore
    private Actor actors;
    private Date lastRefresh;

    public Date getLastRefresh() {
        return lastRefresh;
    }

    public void setLastRefresh(Date lastRefresh) {
        this.lastRefresh = lastRefresh;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public byte[] getFrontImage() {
        return frontImage;
    }

    public void setFrontImage(byte[] frontImage) {
        this.frontImage = frontImage;
    }

    public Integer getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(Integer productionYear) {
        this.productionYear = productionYear;
    }

    public Review getReviews() {
        return reviews;
    }

    public void setReviews(Review reviews) {
        this.reviews = reviews;
    }

    public MovieType getMovieTypes() {
        return movieTypes;
    }

    public void setMovieTypes(MovieType movieTypes) {
        this.movieTypes = movieTypes;
    }

    public Director getDirectors() {
        return directors;
    }

    public void setDirectors(Director directors) {
        this.directors = directors;
    }

    public Actor getActors() {
        return actors;
    }

    public void setActors(Actor actors) {
        this.actors = actors;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                ", rating=" + rating +
                ", frontImage=" + Arrays.toString(frontImage) +
                ", productionYear=" + productionYear +
                ", reviews=" + reviews +
                ", movieTypes=" + movieTypes +
                ", directors=" + directors +
                ", actors=" + actors +
                '}';
    }
}
