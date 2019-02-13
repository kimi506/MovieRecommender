package students.com.movierecommender.data.entity;

/**
 * Created by Kamil Gonska on lut, 2019
 */
public class Token {
    private String token;
    private Integer id;

    public Token() {
    }

    public Token(String token) {
        this.token = token;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
