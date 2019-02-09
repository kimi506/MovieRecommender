package students.com.movierecommender;

import android.app.Activity;
import android.app.Application;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import students.com.movierecommender.di.DaggerAppComponent;

import javax.inject.Inject;


/**
 * Created by Kamil Gonska on sty, 2019
 */
public class MyApplication extends Application implements HasActivityInjector {
    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();

        DaggerAppComponent
                .builder()
                .application(this)
                .build()
                .inject(this);
    }

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }

    private void initDagger() {
        DaggerAppComponent.builder().build().inject(this);
    }
}