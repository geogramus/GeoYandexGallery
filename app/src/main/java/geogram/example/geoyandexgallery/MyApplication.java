package geogram.example.geoyandexgallery;

import android.app.Application;

import geogram.example.geoyandexgallery.di.component.ApplicationComponent;
import geogram.example.geoyandexgallery.di.component.DaggerApplicationComponent;
import geogram.example.geoyandexgallery.di.modules.ApplicationModule;

/**
 * Created by geogr on 23.04.2018.
 */

public class MyApplication extends Application{
    private static ApplicationComponent sApplicationComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        initComponent();
    }
    private void initComponent(){
        sApplicationComponent= DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();
    }
    public static ApplicationComponent getsApplicationComponent(){
        return sApplicationComponent;
    }
}