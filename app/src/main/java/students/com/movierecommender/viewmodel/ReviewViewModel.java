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

    public ReviewViewModel(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public MutableLiveData<List<Review>> getReviewsLiveData() {
        return reviewsLiveData;
    }

    public ReviewRepository getReviewRepository() {
        return reviewRepository;
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
                        reviewsLiveData::setValue,
                        throwable -> reviewsLiveDataByMovie.setValue(Arrays.asList(new Review()))
                ));
    }

    public void insertReview(Review review) {
        reviewRepository.insertReview(review).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });

    }

}
