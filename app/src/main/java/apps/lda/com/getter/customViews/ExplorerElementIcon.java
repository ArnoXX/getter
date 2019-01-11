package apps.lda.com.getter.customViews;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import androidx.appcompat.widget.AppCompatImageButton;
import android.view.ViewGroup;

import apps.lda.com.getter.R;

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
