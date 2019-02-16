package students.com.movierecommender.data.rest;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import students.com.movierecommender.data.entity.Review;
import students.com.movierecommender.utils.Urls;

import java.util.List;

/**
 * Created by Kamil Gonska on sty, 2019
 */
public interface ReviewService {
    @GET(Urls.REVIEWS)
    Observable<List<Review>> getAllReviews();

    @GET(Urls.REVIEWS + "{id}")
    Observable<Review> getReviewById(@Path("id") Integer id);

    @GET(Urls.MOVIES + "{id}/" + Urls.REVIEWS)
    Observable<List<Review>> getReviewsByIdMovie(@Path("id") Integer idMovie);

    @POST(Urls.REVIEWS)
    Call<Void> insertReview(@Body Review review);
}
