package students.com.movierecommender.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import students.com.movierecommender.data.entity.Authentication;
import students.com.movierecommender.data.entity.ResponseMessage;
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
    private final MutableLiveData<String> registerResponseCode = new MutableLiveData<>();

    @Inject
    public LoginViewModel(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public MutableLiveData<Token> getTokenLiveData() {
        return tokenLiveData;
    }

    public MutableLiveData<String> getRegisterResponseCode() {
        return registerResponseCode;
    }

    public void hitTokenByAuth(Authentication authentication) {
        authRepository.getToken(authentication).enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                if (response.code() == 200) {
                    tokenLiveData.setValue(response.body());
                } else {
                    tokenLiveData.setValue(new Token());
                }
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                tokenLiveData.setValue(new Token());
            }
        });
    }

    public void register(Authentication authentication) {
        authRepository.register(authentication).enqueue(new Callback<ResponseMessage>() {
            @Override
            public void onResponse(Call<ResponseMessage> call, Response<ResponseMessage> response) {
                if (response.errorBody() == null) {
                    registerResponseCode.setValue("Successful");
                } else {
                    registerResponseCode.setValue("Incorrect registration");
                }
            }

            @Override
            public void onFailure(Call<ResponseMessage> call, Throwable t) {
                registerResponseCode.setValue(null);
            }
        });
    }

    @Override
    protected void onCleared() {
        disposables.clear();
    }

}
