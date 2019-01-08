package apps.lda.com.getter;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

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
        TextView btn  = new TextView (this);
        ScrollView mainScroll = findViewById (R.id.mainScroll);
        LinearLayout mainScrollLayout = findViewById (R.id.mainScrollLines);
        RelativeLayout element = new RelativeLayout (this);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams (ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins (0, fromDpToPx (getResources ().getDimension (R.dimen.explorerElementViewTopMargin)), 0, 0);
        btn.setText (String.format ("%d", fromDpToPx (getResources ().getDimension (R.dimen.explorerElementViewTopMargin))));
        params1.addRule (RelativeLayout.CENTER_IN_PARENT);
        btn.setLayoutParams (params1);
        mainScrollLayout.setClipToPadding (false);
        element.setBackground(getDrawable (R.drawable.explorer_element_view_bg));
        element.setElevation (fromDpToPx (getResources ().getDimension (R.dimen.elevation)));

        element.setLayoutParams (params);

        element.addView (btn);

        mainScrollLayout.addView (element);
    }
}
