package geogram.example.geoyandexgallery.ui.customlayouts;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.LinearLayout;

/**
 * Created by geogr on 23.04.2018.
 */
public class RectangleSquare extends LinearLayout {
    public RectangleSquare(Context context) {
        super(context);
    }

    public RectangleSquare(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RectangleSquare(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        DisplayMetrics displaymetrics = getResources().getDisplayMetrics();
        int widthPixels=displaymetrics.widthPixels/2;
        int heightPixels= (int) ((widthPixels/1.5)-50);
        setMeasuredDimension(widthPixels, heightPixels);
    }
}
