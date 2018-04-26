package geogram.example.geoyandexgallery.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import geogram.example.geoyandexgallery.R;
import geogram.example.geoyandexgallery.rest.models.Item;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by geogr on 23.04.2018.
 */
public class ImageListAdapter extends RecyclerView.Adapter<ImageListAdapter.ImageListViewHolder> {

    private List<Item> images = new ArrayList<>();
    private Picasso builder;
    public ImageListAdapter(Context context) {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request newRequest = chain.request().newBuilder()
                                .header("Authorization", "OAuth AQAAAAAl58iKAADLWwMHDJ2qOUS4kgTuqylgprU")
                                .build();
                        return chain.proceed(newRequest);
                    }
                })
                .build();
        builder = new Picasso.Builder(context).downloader(new OkHttp3Downloader(client)).build();
    }

    @Override
    public ImageListAdapter.ImageListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ImageListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item, null));
    }

    @Override
    public void onBindViewHolder(ImageListAdapter.ImageListViewHolder holder, int position) {
        builder.load(images.get(position).getPreview())
                .placeholder(R.drawable.ic_image_icon)
                .error(R.drawable.fail)
                .networkPolicy(NetworkPolicy.OFFLINE)
                .into(holder.imageView, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {
                        builder.load(images.get(position).getPreview())
                                .placeholder(R.drawable.ic_image_icon)
                                .error(R.drawable.fail)
                                .into(holder.imageView);
                    }
                });

    }

    @Override
    public int getItemCount() {
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

    public class ImageListViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.imageViewItem)
        ImageView imageView;

        public ImageListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
