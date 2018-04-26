package geogram.example.geoyandexgallery.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import geogram.example.geoyandexgallery.R;
import geogram.example.geoyandexgallery.adapters.ImageListAdapter;
import geogram.example.geoyandexgallery.mvp.presenters.ImageListPresenter;
import geogram.example.geoyandexgallery.mvp.views.ImageListView;
import geogram.example.geoyandexgallery.rest.models.Item;

import static geogram.example.geoyandexgallery.adapters.MainPagerAdapter.ARG_TYPE;

/**
 * Created by geogr on 23.04.2018.
 */

public class ImagesListFragment extends Fragment implements ImageListView {


    ImageListPresenter presenter;

    @BindView(R.id.imageList)
    RecyclerView listView;
    @BindView(R.id.textCount)
    TextView textCount;
    @BindView(R.id.refresh)
    SwipeRefreshLayout swipeRefreshLayout;
    List<Item> imageList = new ArrayList<>();
    private ImageListAdapter adapter;
    private final int startOffset = 0;
    private boolean loadStart = false;
    private String type;
    private GridLayoutManager layoutManager;
    private int total=0;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.content_main, container, false);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("sas", "sas");
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        setRetainInstance(true);
        layoutManager = new GridLayoutManager(getContext(), 2);
        listView.setLayoutManager(layoutManager);
        presenter = new ImageListPresenter(this);
        type=getArguments().getString(ARG_TYPE);
        if (savedInstanceState == null) {
            adapter = new ImageListAdapter(getContext());
            listView.setAdapter(adapter);
            presenter.getPosts(startOffset, type);
            loadStart=true;
        } else {
//            int trtrl=imageList.size();
            listView.setAdapter(adapter);
            textCount.setText(String.format("%s %s", String.valueOf(total), getString(R.string.photoString)));
        }
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter.clear();
                imageList.clear();
                loadStart = true;
                presenter.getPosts(startOffset, type);
            }
        });
        listView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int totalItemCount = layoutManager.getItemCount();
                int visibleItemCount = layoutManager.getChildCount();
                int firstVisibleItems = layoutManager.findFirstVisibleItemPosition();
                if (!loadStart) {
                    if ((visibleItemCount + firstVisibleItems) >= totalItemCount || visibleItemCount==0) {
                        loadStart = true;
                        presenter.getPosts(totalItemCount, type);
                    }
                }
            }
        });
    }


    @Override
    public void addNewItems(List<Item> items, int total) {
        this.total=total;
        imageList.addAll(items);
        adapter.addAll(items);
        textCount.setText(String.format("%s %s", String.valueOf(total), getString(R.string.photoString)));
        swipeRefreshLayout.setRefreshing(false);
        loadStart = false;
    }

    @Override
    public void error() {
        Toast.makeText(getContext(), "Load error", Toast.LENGTH_LONG).show();
        swipeRefreshLayout.setRefreshing(false);
        loadStart=false;
    }

}
