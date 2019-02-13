package apps.lda.com.getter.customViews;

import android.content.Context;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.core.widget.NestedScrollView;
import apps.lda.com.getter.R;
import apps.lda.com.getter.utils.extraUtils;


public class ExplorerElementsScroll extends NestedScrollView {
    private View child;
    private Context context;
    private ViewGroup.LayoutParams params;
    public ExplorerElementsScroll(Context context, View child, ViewGroup.LayoutParams params) {
        super (context);
        this.child = child;
        this.context = context;
        this.params = params;
        init ();
    }
    private void init(){
        this.setLayoutParams (this.params);
        this.addView (this.child);
    }

}
