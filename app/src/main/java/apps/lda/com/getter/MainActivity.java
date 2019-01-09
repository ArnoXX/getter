package apps.lda.com.getter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import apps.lda.com.getter.customViews.ExplorerElement;
import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

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
        ScrollView mainScroll = findViewById (R.id.mainScroll);
        LinearLayout mainScrollLayout = findViewById (R.id.mainScrollLines);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams last_params = new LinearLayout.LayoutParams (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams (ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        params1.addRule (RelativeLayout.CENTER_IN_PARENT);
        params.setMargins (fromDpToPx (getResources ().getDimension (R.dimen.explorerElementViewLeftMargin)), fromDpToPx (getResources ().getDimension (R.dimen.explorerElementViewTopMargin)), fromDpToPx (getResources ().getDimension (R.dimen.explorerElementViewRightMargin)), fromDpToPx (getResources ().getDimension (R.dimen.explorerElementViewBottomMargin)));
        last_params.setMargins (fromDpToPx (getResources ().getDimension (R.dimen.explorerElementViewLeftMargin)), fromDpToPx (getResources ().getDimension (R.dimen.explorerElementViewTopMargin)), fromDpToPx (getResources ().getDimension (R.dimen.explorerElementViewRightMargin)), fromDpToPx (getResources ().getDimension (R.dimen.explorerElementViewBottomMargin_Last)));

        for(int i = 0; i < 20; i++){
            TextView txt  = new TextView (this);
            txt.setText (String.format ("%d, %d, %d", i, params.bottomMargin, params.topMargin));
            Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/roboto_thin.ttf");
            txt.setTypeface (custom_font);
            txt.setLayoutParams (params1);
            View[] children = {txt};
            mainScrollLayout.addView (new ExplorerElement (this, i == 19 ? last_params : params, getDrawable (R.drawable.explorer_element_view_bg), fromDpToPx (getResources ().getDimension (R.dimen.elevation)), children));
        }
    }
}
