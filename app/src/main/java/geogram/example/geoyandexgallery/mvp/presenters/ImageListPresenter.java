package geogram.example.geoyandexgallery.mvp.presenters;


import java.util.List;

import javax.inject.Inject;

import geogram.example.geoyandexgallery.MyApplication;
import geogram.example.geoyandexgallery.mvp.views.ImageListView;
import geogram.example.geoyandexgallery.rest.api.ImageService;
import geogram.example.geoyandexgallery.rest.models.Item;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by geogr on 23.04.2018.
 */


public class ImageListPresenter {


    private ImageListView fragment;

    public ImageListPresenter(ImageListView fragment) {
        MyApplication.getsApplicationComponent().inject(this);
        this.fragment = fragment;
    }

    @Inject
    ImageService service;



    public void getPosts(int offset, String type) {

        service.get_posts(type, offset)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(generalModel -> {
                    if (generalModel != null) {
                        List<Item> list=generalModel.getEmbedded().getItems();
                        int total=generalModel.getEmbedded().getTotal();
                            fragment.addNewItems(list, total);

                    }
                }, throwable -> fragment.error());

    }

}