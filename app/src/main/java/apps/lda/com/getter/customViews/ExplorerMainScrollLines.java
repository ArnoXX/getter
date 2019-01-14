package apps.lda.com.getter.customViews;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import apps.lda.com.getter.R;

public class ExplorerMainScrollLines extends LinearLayout {
    private Context context;
    private int page;
    public static int fromDpToPx(float dp){
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 320f);
        return Math.round(px);
    }
    public ExplorerMainScrollLines(Context context, int page) {
        super (context);
        this.context = context;
        this.page = page;
        init ();
    }
    private void init(){
        this.setOrientation (LinearLayout.VERTICAL);

        Typeface roboto_thin = Typeface.createFromAsset (this.context.getAssets(),
                "fonts/roboto_thin.ttf");


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

        for(int i = 0; i < this.page+1; i++){
            ExplorerElementIcon icon = new ExplorerElementIcon (this.context, icon_params, this.context.getDrawable (R.drawable.folder_drawable));
            icon.setId (R.id.explorer_element_icon);
            TextView txt = new TextView (this.context);
            txt.setText (R.string.app_name);
            txt.setTextColor (this.context.getColor (R.color.dark));
            txt.setTypeface (roboto_thin);
            txt.setLayoutParams (label_params);
            View[] children = {icon, txt};
            this.addView (new ExplorerElement (this.context, i == this.page ? last_params : params, this.context.getDrawable (R.drawable.explorer_element_view_bg), fromDpToPx (getResources ().getDimension (R.dimen.elevation)), children, R.id.explorer_element));
        }

    }

}
