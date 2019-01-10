package apps.lda.com.getter.customViews;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class ExplorerElement extends RelativeLayout {
    private ViewGroup.LayoutParams params;
    private Drawable bg;
    private int elevation;
    private View[] children;
    public ExplorerElement(Context context, ViewGroup.LayoutParams params, Drawable bg, int elevation, View[] children) {
        super (context);
        this.params = params;
        this.bg = bg;
        this.elevation = elevation;
        this.children = children;
        init ();
    }
    private void init(){
        this.setLayoutParams (this.params);
        this.setBackground (this.bg);
        this.setElevation (this.elevation);
        for(View child : this.children){
            this.addView (child);
        }
    }
}