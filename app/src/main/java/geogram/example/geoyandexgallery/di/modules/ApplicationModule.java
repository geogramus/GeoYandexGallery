package geogram.example.geoyandexgallery.di.modules;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by geogr on 23.04.2018.
 */

@Module
public class ApplicationModule {
    private Application application;
    public ApplicationModule(Application application1){

        this.application = application1;
    }
    @Singleton
    @Provides
    public Context provideContext(){
        return application;
    }
}