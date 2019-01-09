package apps.lda.com.getter.utils;

import android.content.res.Resources;
import android.util.DisplayMetrics;

public class extraUtils {
    public static int fromDpToPx(float dp){
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 320f);
        return Math.round(px);
    }
}
