package geogram.example.geoyandexgallery.ui.activityes;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by geogr on 26.04.2018.
 */

public class SplashActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SystemClock.sleep(1500);//показываем превью 1,5 секунды
        Intent intent=new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);//запускаем основную активность
        finish();////завершаем превью активити
    }
}
