package students.com.movierecommender.data.rest;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;
import students.com.movierecommender.data.entity.Authentication;
import students.com.movierecommender.data.entity.Token;
import students.com.movierecommender.utils.Urls;


/**
 * Created by Kamil Gonska on lut, 2019
 */
public interface AuthService {
    @POST(Urls.USERS + Urls.AUTH)
    Observable<Token> getToken(@Body Authentication authentication);
}
