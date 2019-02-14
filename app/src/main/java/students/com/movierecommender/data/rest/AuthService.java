package students.com.movierecommender.data.rest;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import students.com.movierecommender.data.entity.Authentication;
import students.com.movierecommender.data.entity.ResponseMessage;
import students.com.movierecommender.data.entity.Token;
import students.com.movierecommender.utils.Urls;


/**
 * Created by Kamil Gonska on lut, 2019
 */
public interface AuthService {
    @Headers("Content-Type: application/json")
    @POST(Urls.USERS + Urls.AUTH)
    Call<Token> getToken(@Body Authentication authentication);

    @POST(Urls.USERS + Urls.REGISTER)
    Call<ResponseMessage> register(@Body Authentication authentication);
}
