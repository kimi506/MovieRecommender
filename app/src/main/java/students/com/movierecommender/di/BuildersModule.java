package students.com.movierecommender.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

import io.reactivex.Single;
import students.com.movierecommender.view.LoginActivity;
import students.com.movierecommender.view.MainActivity;
import students.com.movierecommender.view.SingleActorActivity;
import students.com.movierecommender.view.SingleMovieActivity;

/**
 * Created by Kamil Gonska on sty, 2019
 */
@Module
public abstract class BuildersModule {

    @ContributesAndroidInjector(modules = UtilsModule.class)
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector(modules = UtilsModule.class)
    abstract LoginActivity bindLoginActivity();

    @ContributesAndroidInjector(modules = UtilsModule.class)
    abstract SingleMovieActivity bindSingleMovieActivity();

    @ContributesAndroidInjector(modules = UtilsModule.class)
    abstract SingleActorActivity bindSingleActorActivity();
}
