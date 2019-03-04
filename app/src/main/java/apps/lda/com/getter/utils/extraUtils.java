package apps.lda.com.getter.utils;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import java.util.ArrayList;

import apps.lda.com.getter.customViews.ExplorerElement;
import apps.lda.com.getter.customViews.ExplorerMainScrollLines_Deprecated;

public class extraUtils {
    public static int fromDpToPx(float dp){
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 320f);
        return Math.round(px);
    }
    public static ArrayList<ExplorerElement> getChildren(ExplorerMainScrollLines_Deprecated v) {

        ArrayList<ExplorerElement> result = new ArrayList<>();

        ExplorerMainScrollLines_Deprecated viewGroup = v;
        for (int i = 0; i < viewGroup.getChildCount(); i++) {

            ExplorerElement child = (ExplorerElement) viewGroup.getChildAt(i);

            result.add (child);
        }
        return result;
    }
}
