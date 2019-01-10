package apps.lda.com.getter.customViews;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageButton;
import android.view.ViewGroup;

public class ExplorerElementIcon extends AppCompatImageButton {
    private ViewGroup.LayoutParams params;
    private Drawable src;
    public ExplorerElementIcon(Context context, ViewGroup.LayoutParams params, Drawable src) {
        super (context);
        this.src = src;
        this.params = params;
        init ();
    }
    private void init(){
        this.setLayoutParams (this.params);
        this.setImageDrawable (this.src);
    }
}
