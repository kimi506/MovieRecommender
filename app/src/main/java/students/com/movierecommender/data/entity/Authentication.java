package students.com.movierecommender.data.entity;

/**
 * Created by Kamil Gonska on lut, 2019
 */
public class Authentication {
    private String email;
    private String password;

    public Authentication() {
    }

    public Authentication(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
