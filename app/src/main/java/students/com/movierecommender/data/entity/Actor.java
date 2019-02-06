package students.com.movierecommender.data.entity;

/**
 * Created by Kamil Gonska on sty, 2019
 */
public class Actor {
    private Long id;
    private String name;
    private String surname;
    private byte[] image;

    public Actor() {
    }

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
