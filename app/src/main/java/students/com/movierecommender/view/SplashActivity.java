package students.com.movierecommender.view;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import students.com.movierecommender.R;

public class SplashActivity extends AppCompatActivity {
    private final int SPLASH_TIME_OUT = 2000;
    private ImageView splashScreenLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        splashScreenLogo = findViewById(R.id.splash_screen_logo);
        Animation transitionAnimation = AnimationUtils.loadAnimation(this, R.anim.splash_screen_transition);
        splashScreenLogo.startAnimation(transitionAnimation);

        new Handler().postDelayed(() -> {
            Intent loginIntent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(loginIntent);
            finish();
        }, SPLASH_TIME_OUT);
    }
}
