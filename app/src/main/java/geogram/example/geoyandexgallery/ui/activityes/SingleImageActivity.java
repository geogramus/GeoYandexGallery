package geogram.example.geoyandexgallery.ui.activityes;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.io.File;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import geogram.example.geoyandexgallery.MyApplication;
import geogram.example.geoyandexgallery.R;

public class SingleImageActivity extends AppCompatActivity {

    @BindView(R.id.bigImageView)
    ImageView imageView;
    @Inject
    Picasso builder;
    private final String probel = " ";
    private String imageFile;
    private String imageName;
    private final String loadDir="/GeoYandexGallery";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_image);
        MyApplication.getsApplicationComponent().inject(this);
        ButterKnife.bind(this);
        imageFile = getIntent().getStringExtra("imageFile");//получаем ссылку на картинку
        imageName =getIntent().getStringExtra("name");//получаем название картинки
        String toolbarTitle = String.valueOf(getIntent().getIntExtra("position", 0)) +
                probel + getString(R.string.from) + probel
                + String.valueOf(getIntent().getIntExtra("total", 0));
        setTitle(toolbarTitle);//устанавливаем название тулбара
        builder.load(imageFile)
                .placeholder(R.drawable.logotip)//картинка заглушки
                .error(R.drawable.fail)//картинка ошибки загрузки картинки
                .networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE)//отменяем кэширование
                .into(imageView);//загружаем картинку imageView
    }

    public void loadImage(String url) {//скачивание картинке на устройство
        File direct = new File(Environment.getExternalStorageDirectory()
                + loadDir);//получаем папку на для загрузки в нее картинки

        if (!direct.exists()) {//проверяем существование папки
            direct.mkdirs();//выбираем папку
        }

        DownloadManager dManager = (DownloadManager) this.getSystemService(Context.DOWNLOAD_SERVICE);
        //получаем сервис для загрузки изображения
        Uri downloadUri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(downloadUri);
        //создаем запрос, утсанавливаем ссылку для скачивания картинки
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)//устанавливаем увиедомление по завершению загрузки
                .setAllowedNetworkTypes(
                        DownloadManager.Request.NETWORK_WIFI
                                | DownloadManager.Request.NETWORK_MOBILE)//устанавливаем типы интернет соединения для загрузки
                .setAllowedOverRoaming(false)//запрещаем скачивание в роуминге
                .setTitle(imageName)//устанавливаем название файла
                .setDestinationInExternalPublicDir(loadDir, imageName);//устанавливаем папку в которую необходимо поместить скаченный файл

        if (dManager != null) {
            dManager.enqueue(request);//запускаем запрос на исполнение в отдельном потоке
        }else{
            Toast.makeText(this, getString(R.string.loadError), Toast.LENGTH_SHORT).show();
            //показываем ошибку загрзки
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_single_image, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.load_image) {
            if(imageFile!=null)//проверяем успешность передачи ссылки
            loadImage(imageFile);//скачиваем картинку на телефон при нажатии на пункт меню
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
