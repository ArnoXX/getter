package apps.lda.com.getter.customViews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

public class ExplorerCoordinator extends CoordinatorLayout {
    private ArrayList<View> children;
    public ExplorerCoordinator(@NonNull Context context, ArrayList<View> children ) {
        super (context);

        this.children = children;
        init();
    }
    private void init(){
        for(View elem : this.children){
            this.addView (elem);
        }
    }
}
