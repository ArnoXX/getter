package apps.lda.com.getter.customViews;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class ExplorerElementView extends RelativeLayout {

    public ExplorerElementView(Context context, Drawable bg) {
       super(context);
       this.setBackground (bg);
    }
}
