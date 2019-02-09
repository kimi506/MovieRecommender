package students.com.movierecommender.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import students.com.movierecommender.view.LoginActivity;
import students.com.movierecommender.view.MovieActivity;

/**
 * Created by Kamil Gonska on sty, 2019
 */
@Module
public abstract class BuildersModule {

    @ContributesAndroidInjector(modules = UtilsModule.class)
    abstract MovieActivity bindMovieActivity();

    @ContributesAndroidInjector(modules = UtilsModule.class)
    abstract LoginActivity bindLoginActivity();



}
