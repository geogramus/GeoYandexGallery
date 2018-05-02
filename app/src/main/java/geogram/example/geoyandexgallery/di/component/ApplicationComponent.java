package geogram.example.geoyandexgallery.di.component;

import javax.inject.Singleton;

import dagger.Component;
import geogram.example.geoyandexgallery.adapters.ImageListAdapter;
import geogram.example.geoyandexgallery.adapters.MainPagerAdapter;
import geogram.example.geoyandexgallery.di.modules.ApplicationModule;
import geogram.example.geoyandexgallery.di.modules.RestModule;
import geogram.example.geoyandexgallery.mvp.presenters.ImageListPresenter;
import geogram.example.geoyandexgallery.ui.activityes.SingleImageActivity;

/**
 * Created by geogr on 23.04.2018.
 */

@Singleton
@Component(modules = {ApplicationModule.class, RestModule.class })
public interface ApplicationComponent {
    void inject(SingleImageActivity activity);
    void inject(ImageListPresenter presenter);
    void inject(MainPagerAdapter adapter);
    void inject(ImageListAdapter adapter);
}
