package apps.lda.com.getter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.HashMap;

import androidx.viewpager.widget.ViewPager;
import apps.lda.com.getter.customViews.ExplorerElement;
import apps.lda.com.getter.customViews.ExplorerElementIcon;
import apps.lda.com.getter.customViews.ExplorerPagerAdapter;

public class MainActivity extends AppCompatActivity {
    public static int fromDpToPx(float dp){
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 320f);
        return Math.round(px);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        getSupportActionBar().hide();
        setContentView (R.layout.activity_main);
        // TODO complete this shit
        HashMap<String, ? extends ViewGroup.LayoutParams> params_set = new HashMap<> (); // TODO organize params hash table
        Typeface roboto_thin = Typeface.createFromAsset (getAssets(),
                "fonts/roboto_thin.ttf");
        ViewPager pager = findViewById(R.id.pager);
        pager.setAdapter(new ExplorerPagerAdapter (getSupportFragmentManager()));


    }
}
