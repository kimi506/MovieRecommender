package students.com.movierecommender.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

import students.com.movierecommender.view.LoginActivity;
import students.com.movierecommender.view.MainActivity;

/**
 * Created by Kamil Gonska on sty, 2019
 */
@Module
public abstract class BuildersModule {

    @ContributesAndroidInjector(modules = UtilsModule.class)
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector(modules = UtilsModule.class)
    abstract LoginActivity bindLoginActivity();
}
