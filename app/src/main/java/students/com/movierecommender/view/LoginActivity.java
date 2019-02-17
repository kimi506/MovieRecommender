package students.com.movierecommender.view;

import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;
import students.com.movierecommender.R;
import students.com.movierecommender.data.entity.Authentication;
import students.com.movierecommender.data.entity.Token;
import students.com.movierecommender.utils.SharedPrefHelper;
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
        SharedPrefHelper.getInstance().Initialize(getApplicationContext());
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        loginViewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel.class);
        loginViewModel.getTokenLiveData().observe(this, this::reload);
        loginViewModel.getRegisterResponseCode().observe(this, this::acceptRegistration);

        reload(new Token(SharedPrefHelper.getInstance().getToken()));
    }

    @OnClick(R.id.register)
    public void register(View view) {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(LoginActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.dialog_login, null);
        EditText mEmail = mView.findViewById(R.id.etEmail);
        EditText mPassword = mView.findViewById(R.id.etPassword);
        Button mLogin = mView.findViewById(R.id.btnLogin);

        mBuilder.setView(mView);
        mBuilder.create();
        final AlertDialog dialog = mBuilder.create();
        dialog.show();
        mLogin.setOnClickListener(view1 -> {
            Authentication authentication = new Authentication(mEmail.getText().toString(), mPassword.getText().toString(), mEmail.getText().toString());
            loginViewModel.register(authentication);
            dialog.dismiss();
        });
    }

    private void acceptRegistration(String responseMessage) {
        if (responseMessage != null && responseMessage.isEmpty()) {
            Toast.makeText(LoginActivity.this,
                    R.string.success_login_msg,
                    Toast.LENGTH_SHORT).show();
        } else if (responseMessage != null && !responseMessage.isEmpty()) {
            Toast.makeText(LoginActivity.this,
                    responseMessage,
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(LoginActivity.this,
                    R.string.error_login_msg,
                    Toast.LENGTH_SHORT).show();
        }
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
                }, 2000);
    }

    private void reload(Token token) {
        if (token != null) {
            SharedPrefHelper.getInstance().saveToken(token.getToken());
        }

        if (SharedPrefHelper.getInstance().getToken() != null && !SharedPrefHelper.getInstance().getToken().isEmpty()) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
            SharedPrefHelper.getInstance().Initialize(getApplicationContext());
            SharedPrefHelper.getInstance().saveIdUser(token.getId());
            SharedPrefHelper.getInstance().saveToken(token.getToken());
        }
    }

    public void logIn(View view) {
        Authentication auth = new Authentication(email.getText().toString(), password.getText().toString());
        loginViewModel.hitTokenByAuth(auth);
        login();
    }

    @Override
    public void onResume(){
        super.onResume();
        SharedPrefHelper.getInstance().Initialize(getApplicationContext());
    }
}
