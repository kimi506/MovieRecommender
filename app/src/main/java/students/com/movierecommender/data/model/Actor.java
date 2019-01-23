package students.com.movierecommender.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Kamil Gonska on sty, 2019
 */
public class Actor {
//    @SerializedName("Id")
    private Long id;
//    @SerializedName("Name")
    private String name;
//    @SerializedName("Surname")
    private String surname;
//    @SerializedName("Image")
    private byte[] image;

    public Actor(Long id, String name, String surname, byte[] image) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
