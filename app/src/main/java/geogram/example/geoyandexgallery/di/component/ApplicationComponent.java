package geogram.example.geoyandexgallery.di.component;

import javax.inject.Singleton;

import dagger.Component;
import geogram.example.geoyandexgallery.di.modules.ApplicationModule;
import geogram.example.geoyandexgallery.di.modules.RestModule;
import geogram.example.geoyandexgallery.mvp.presenters.ImageListPresenter;

/**
 * Created by geogr on 23.04.2018.
 */

@Singleton
@Component(modules = {ApplicationModule.class, RestModule.class})
public interface ApplicationComponent {
    void inject(ImageListPresenter presenter);
}
