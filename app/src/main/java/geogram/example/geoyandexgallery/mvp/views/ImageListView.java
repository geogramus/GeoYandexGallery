package geogram.example.geoyandexgallery.mvp.views;

import java.util.List;

import geogram.example.geoyandexgallery.rest.models.Item;

/**
 * Created by geogr on 23.04.2018.
 */


public interface ImageListView{
    void addNewItems(List<Item> items, int total);
    void error();
}
