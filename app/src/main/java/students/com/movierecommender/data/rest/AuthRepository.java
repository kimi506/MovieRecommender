package students.com.movierecommender.data.rest;

import retrofit2.Call;
import retrofit2.Response;
import students.com.movierecommender.data.entity.Authentication;
import students.com.movierecommender.data.entity.Token;

import javax.inject.Inject;

/**
 * Created by Kamil Gonska on lut, 2019
 */
public class AuthRepository {
    private final AuthService authService;

    @Inject
    public AuthRepository(AuthService authService) {
        this.authService = authService;
    }

    public Call<Token> getToken(Authentication authentication) {
        return authService.getToken(authentication);
    }

    public Call<Response<Void>> register(Authentication authentication) {
        return authService.register(authentication);
    }


}
