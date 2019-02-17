package students.com.movierecommender.data.rest;

import io.reactivex.Observable;
import retrofit2.Call;
import students.com.movierecommender.data.entity.Review;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

/**
 * Created by Kamil Gonska on sty, 2019
 */
@Singleton
public class ReviewRepository {
    private ReviewService reviewService;

    @Inject
    public ReviewRepository(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    public Observable<List<Review>> getAllReviews() {
        return reviewService.getAllReviews();
    }

    public Observable<Review> getReviewById(Integer id) {
        return reviewService.getReviewById(id);
    }

    public Observable<List<Review>> getReviewsByIdMovie(Integer idMovie) {
        return reviewService.getReviewsByIdMovie(idMovie);
    }

    public Observable<Void> insertReview(Review review) {
        return reviewService.insertReview(review);
    }
}
