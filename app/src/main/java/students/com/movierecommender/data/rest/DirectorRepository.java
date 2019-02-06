package students.com.movierecommender.data.rest;

import io.reactivex.Observable;
import students.com.movierecommender.data.entity.Director;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

/**
 * Created by Kamil Gonska on sty, 2019
 */
@Singleton
public class DirectorRepository {
    private DirectorService directorService;

    @Inject
    public DirectorRepository(DirectorService directorService) {
        this.directorService = directorService;
    }

    public Observable<List<Director>> getAllDirectors(){
        return directorService.getAllDirectors();
    }

    public Observable<Director> getDirectorById(Integer id){
        return directorService.getDirectorById(id);
    }
}
