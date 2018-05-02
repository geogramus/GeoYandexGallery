package geogram.example.geoyandexgallery;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

import geogram.example.geoyandexgallery.di.component.ApplicationComponent;
import geogram.example.geoyandexgallery.di.component.DaggerApplicationComponent;
import geogram.example.geoyandexgallery.di.modules.ApplicationModule;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by geogr on 23.04.2018.
 */

public class MyApplication extends Application{
    private static ApplicationComponent sApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initComponent();
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
        LeakCanary.install(this);


    }
    private void initComponent(){
        sApplicationComponent= DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();
    }
    public static ApplicationComponent getsApplicationComponent(){
        return sApplicationComponent;
    }
}