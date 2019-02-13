package apps.lda.com.getter.customViews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

public class ExplorerPager extends ViewPager {
    public ExplorerPager(@NonNull Context context) {
        super (context);
    }

    public ExplorerPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super (context, attrs);
    }
}
