package students.com.movierecommender.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import students.com.movierecommender.data.entity.Authentication;
import students.com.movierecommender.data.entity.Token;
import students.com.movierecommender.data.rest.AuthRepository;

import javax.inject.Inject;

/**
 * Created by Kamil Gonska on lut, 2019
 */
public class LoginViewModel extends ViewModel {

    private final AuthRepository authRepository;

    private final CompositeDisposable disposables = new CompositeDisposable();
    private final MutableLiveData<Token> tokenLiveData = new MutableLiveData<>();

    @Inject
    public LoginViewModel(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public MutableLiveData<Token> getTokenLiveData() {
        return tokenLiveData;
    }

    public void hitTokenByAuth(Authentication authentication) {
        disposables.add(authRepository.getToken(authentication)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        tokenLiveData::setValue,
                        throwable -> {
                            throwable.printStackTrace();
                            tokenLiveData.setValue(new Token());
                        }
                ));
    }

    @Override
    protected void onCleared() {
        disposables.clear();
    }

}
