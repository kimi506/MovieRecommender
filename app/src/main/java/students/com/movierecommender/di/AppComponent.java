package students.com.movierecommender.di;


import dagger.Component;
import students.com.movierecommender.view.MainActivity;
import students.com.movierecommender.view.MovieActivity;

import javax.inject.Singleton;

/**
 * Created by Kamil Gonska on sty, 2019
 */
@Component(modules = {AppModule.class, UtilsModule.class})
@Singleton
public interface AppComponent {
    void doInjection(MainActivity movieActivity);
}
