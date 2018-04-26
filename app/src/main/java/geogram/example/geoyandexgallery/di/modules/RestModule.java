package geogram.example.geoyandexgallery.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import geogram.example.geoyandexgallery.rest.RestClient;
import geogram.example.geoyandexgallery.rest.api.ImageService;

/**
 * Created by geogr on 23.04.2018.
 */

@Module
public class RestModule {
    private RestClient mRestClient;
    public RestModule(){
        mRestClient=new RestClient();
    }
    @Singleton
    @Provides
    public RestClient providesRestClient(){return mRestClient;}
    @Singleton
    @Provides
    public ImageService getImages(){return mRestClient.createService(ImageService.class);}

}