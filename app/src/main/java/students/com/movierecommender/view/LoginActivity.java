package students.com.movierecommender.view;

import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import students.com.movierecommender.R;
import students.com.movierecommender.data.entity.Authentication;
import students.com.movierecommender.data.entity.Token;
import students.com.movierecommender.utils.Urls;
import students.com.movierecommender.utils.ViewModelFactory;
import students.com.movierecommender.viewmodel.LoginViewModel;

import javax.inject.Inject;

public class LoginActivity extends AppCompatActivity {

    @Inject
    ViewModelFactory viewModelFactory;
    LoginViewModel loginViewModel;

    @BindView(R.id.username_edit_text)
    EditText email;

    @BindView(R.id.password_edit_text)
    EditText password;
    @BindView(R.id.next_button)
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences preferences = getSharedPreferences("myPrefs", MODE_PRIVATE);
        reload(new Token(preferences.getString("token", "")));
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        loginViewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel.class);
        loginViewModel.getTokenLiveData().observe(this, this::reload);
    }

    public void login() {
        loginButton.setEnabled(false);

        ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        loginButton.setEnabled(true);
                        progressDialog.dismiss();
                    }
                }, 3000);
    }

    private void reload(Token token) {
        Urls.TOKEN = token.getToken();
        if (Urls.TOKEN != null && !Urls.TOKEN.isEmpty()) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
            SharedPreferences preferences = getSharedPreferences("myPrefs", MODE_PRIVATE);
            preferences.edit().putString("token", token.getToken()).commit();
        }
    }

    public void testActor(View view) {
        Authentication auth = new Authentication("testuser@gmail.com", "TestUser1!");
        loginViewModel.hitTokenByAuth(auth);
    }

    public void logIn(View view) {
        Authentication auth = new Authentication(email.getText().toString(), password.getText().toString());
        loginViewModel.hitTokenByAuth(auth);
        login();
    }
}
