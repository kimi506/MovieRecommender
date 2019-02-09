package students.com.movierecommender.di;

import android.content.Context;
import dagger.Module;
import dagger.Provides;
import students.com.movierecommender.MyApplication;

import javax.inject.Singleton;

/**
 * Created by Kamil Gonska on sty, 2019
 */

@Module
public class AppModule {

    @Provides
    Context provideContext(MyApplication application) {
        return application.getApplicationContext();
    }
}
