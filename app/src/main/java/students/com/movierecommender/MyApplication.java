package students.com.movierecommender;

import android.app.Application;
import android.content.Context;
import students.com.movierecommender.di.AppComponent;
import students.com.movierecommender.di.AppModule;
import students.com.movierecommender.di.DaggerAppComponent;
import students.com.movierecommender.di.UtilsModule;


/**
 * Created by Kamil Gonska on sty, 2019
 */

public class MyApplication extends Application {
    AppComponent appComponent;
    Context context;

    @Override
    public void onCreate() {
        try {
            super.onCreate();
            context = this;
            appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).utilsModule(new UtilsModule()).build();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }
}
