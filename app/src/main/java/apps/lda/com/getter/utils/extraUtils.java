package apps.lda.com.getter.utils;

import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import apps.lda.com.getter.customViews.ExplorerElement;
import apps.lda.com.getter.customViews.ExplorerMainScrollLines;

public class extraUtils {
    public static int fromDpToPx(float dp){
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 320f);
        return Math.round(px);
    }
    public static ArrayList<ExplorerElement> getChildren(ExplorerMainScrollLines v) {

        ArrayList<ExplorerElement> result = new ArrayList<>();

        ExplorerMainScrollLines viewGroup = v;
        for (int i = 0; i < viewGroup.getChildCount(); i++) {

            ExplorerElement child = (ExplorerElement) viewGroup.getChildAt(i);

            result.add (child);
        }
        return result;
    }
}
