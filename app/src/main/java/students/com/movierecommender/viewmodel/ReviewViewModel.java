package students.com.movierecommender.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import students.com.movierecommender.data.entity.Review;
import students.com.movierecommender.data.rest.ReviewRepository;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Kamil Gonska on lut, 2019
 */
public class ReviewViewModel extends ViewModel {
    private final ReviewRepository reviewRepository;
    private final CompositeDisposable disposables = new CompositeDisposable();
    private final MutableLiveData<List<Review>> reviewsLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<Review>> reviewsLiveDataByMovie = new MutableLiveData<>();
    private final MutableLiveData<Review> reviewLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> reviewInserted = new MutableLiveData<>();

    public ReviewViewModel(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
        this.reviewInserted.setValue(false);
    }

    public MutableLiveData<List<Review>> getReviewsLiveData() {
        return reviewsLiveData;
    }

    public ReviewRepository getReviewRepository() {
        return reviewRepository;
    }

    public MutableLiveData<Boolean> getReviewInserted() {
        return reviewInserted;
    }

    public MutableLiveData<List<Review>> getReviewsLiveDataByMovie() {
        return reviewsLiveDataByMovie;
    }

    public void hitAllReviews() {
        disposables.add(reviewRepository.getAllReviews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        reviewsLiveData::setValue,
                        throwable -> reviewsLiveData.setValue(Arrays.asList(new Review()))
                ));
    }

    public void hitReviewById(Integer idReview) {
        disposables.add(reviewRepository.getReviewById(idReview)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        reviewLiveData::setValue,
                        throwable -> reviewLiveData.setValue(new Review())
                ));
    }

    public void hitReviewsByIdMovie(Integer idMovie) {
        disposables.add(reviewRepository.getReviewsByIdMovie(idMovie)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        reviewsLiveDataByMovie::setValue,
                        throwable -> reviewsLiveDataByMovie.setValue(Arrays.asList(new Review()))
                ));
    }

    public void insertReview(Review review) {
        disposables.add(reviewRepository.insertReview(review)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        result -> {
                            reviewInserted.setValue(true);
                        },
                        throwable -> {
                            reviewInserted.setValue(true);
                        }
                ));
    }
}
