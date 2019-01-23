package students.com.movierecommender.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import students.com.movierecommender.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void logIn(View view) {
        Intent intent = new Intent(this, MovieActivity.class);
        startActivity(intent);
    }
}
