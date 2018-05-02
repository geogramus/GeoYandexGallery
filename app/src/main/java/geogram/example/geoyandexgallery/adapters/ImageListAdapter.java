package geogram.example.geoyandexgallery.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import geogram.example.geoyandexgallery.MyApplication;
import geogram.example.geoyandexgallery.R;
import geogram.example.geoyandexgallery.rest.models.Item;

/**
 * Created by geogr on 23.04.2018.
 */
public class ImageListAdapter extends RecyclerView.Adapter<ImageListAdapter.ImageListViewHolder> {

    private List<Item> images = new ArrayList<>();
    @Inject
    Picasso builder;

    public ImageListAdapter() {
        MyApplication.getsApplicationComponent().inject(this);
    }

    @Override
    public ImageListAdapter.ImageListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ImageListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item, null));
    }

    @Override
    public void onBindViewHolder(ImageListAdapter.ImageListViewHolder holder, int position) {
        builder.load(images.get(position).getPreview())
                .fit().centerCrop()
                .placeholder(R.drawable.ic_image_icon)
                .error(R.drawable.fail)
                .into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        if(images==null)
            return 0;
        return images.size();
    }

    public void addAll(List<Item> list) {
        images.addAll(list);
        notifyDataSetChanged();
    }

    public void clear() {
        images.clear();
        notifyDataSetChanged();
    }
    public Item getItem(int position){
        return images.get(position);
    }

    public class ImageListViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageViewItem)
        ImageView imageView;

        public ImageListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
