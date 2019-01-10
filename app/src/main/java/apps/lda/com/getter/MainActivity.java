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

import java.util.HashMap;

import apps.lda.com.getter.customViews.ExplorerElement;
import apps.lda.com.getter.customViews.ExplorerElementIcon;

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

        ScrollView mainScroll = findViewById (R.id.mainScroll);
        LinearLayout mainScrollLayout = findViewById (R.id.mainScrollLines);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams last_params = new LinearLayout.LayoutParams (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams icon_params = new RelativeLayout.LayoutParams (fromDpToPx (getResources ().getDimension (R.dimen.explorerElementIconWidth)), fromDpToPx (getResources ().getDimension (R.dimen.explorerElementIconHeight)));
        RelativeLayout.LayoutParams label_params = new RelativeLayout.LayoutParams (ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        params.setMargins (fromDpToPx (getResources ().getDimension (R.dimen.explorerElementViewLeftMargin)), fromDpToPx (getResources ().getDimension (R.dimen.explorerElementViewTopMargin)), fromDpToPx (getResources ().getDimension (R.dimen.explorerElementViewRightMargin)), fromDpToPx (getResources ().getDimension (R.dimen.explorerElementViewBottomMargin)));
        last_params.setMargins (fromDpToPx (getResources ().getDimension (R.dimen.explorerElementViewLeftMargin)), fromDpToPx (getResources ().getDimension (R.dimen.explorerElementViewTopMargin)), fromDpToPx (getResources ().getDimension (R.dimen.explorerElementViewRightMargin)), fromDpToPx (getResources ().getDimension (R.dimen.explorerElementViewBottomMargin_Last)));
        icon_params.addRule (RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
        label_params.addRule (RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
        label_params.addRule (RelativeLayout.END_OF, R.id.explorer_element_icon);
        label_params.setMarginStart (fromDpToPx (getResources ().getDimension (R.dimen.explorerElementLabelMarginStart)));
        icon_params.setMargins(fromDpToPx (getResources ().getDimension (R.dimen.explorerElementIconLeftMargin)), fromDpToPx (getResources ().getDimension (R.dimen.explorerElementIconTopMargin)), fromDpToPx (getResources ().getDimension (R.dimen.explorerElementIconRightMargin)), fromDpToPx (getResources ().getDimension (R.dimen.explorerElementIconBottomMargin)));

        for(int i = 0; i < 40; i++){
            ExplorerElementIcon icon = new ExplorerElementIcon (this, icon_params, getDrawable (R.drawable.folder_drawable));
            icon.setId (R.id.explorer_element_icon);
            TextView txt = new TextView (this);
            txt.setText (R.string.app_name);
            txt.setTypeface (Typeface.createFromAsset (getAssets(),
                    "fonts/roboto_thin.ttf"));
            txt.setLayoutParams (label_params);
            View[] children = {icon, txt};
            mainScrollLayout.addView (new ExplorerElement (this, i == 39 ? last_params : params, getDrawable (R.drawable.explorer_element_view_bg), fromDpToPx (getResources ().getDimension (R.dimen.elevation)), children));
        }
    }
}
