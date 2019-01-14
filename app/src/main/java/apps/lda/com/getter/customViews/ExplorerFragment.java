package apps.lda.com.getter.customViews;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import apps.lda.com.getter.R;

public class ExplorerFragment extends Fragment {
    private int pageNumber;
    private boolean scrolling;

    public static ExplorerFragment newInstance(int page) {
        ExplorerFragment fragment = new ExplorerFragment ();
        Bundle args=new Bundle();
        args.putInt("num", page);
        fragment.setArguments(args);
        return fragment;
    }

    public ExplorerFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageNumber = getArguments() != null ? getArguments().getInt("num") : 1;
    }
    public static int fromDpToPx(float dp){
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 320f);
        return Math.round(px);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View result=inflater.inflate(R.layout.explorer_list_layout, container, false);

        ScrollView.LayoutParams scroll_params = new ScrollView.LayoutParams (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ExplorerMainScrollLines lines = new ExplorerMainScrollLines (result.getContext (), pageNumber);
        lines.setLayoutParams (scroll_params);
        ScrollView scroll = result.findViewById (R.id.mainScroll);
        scroll.addView (lines);
        scroll.getViewTreeObserver ().addOnScrollChangedListener (new ViewTreeObserver.OnScrollChangedListener () {
            @Override
            public void onScrollChanged() {
                scrolling = true;
            }
        });
        ArrayList<View> elems = getChildren (lines);
        for(View elem : elems){
            elem.setOnTouchListener (new View.OnTouchListener ( ) {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if(event.getAction () == MotionEvent.ACTION_DOWN || event.getAction () == MotionEvent.ACTION_HOVER_ENTER){
                        v.setElevation (0);
                        return true;
                    } else if (event.getAction () == MotionEvent.ACTION_UP || scrolling){
                        scrolling = false;
                        v.setElevation (fromDpToPx (getResources ().getDimension (R.dimen.elevation)));
                        return true;
                    }
                    return false;
                }
            });
        }
        return result;
    }
    public ArrayList<View> getChildren(View v) {

        if (!(v instanceof ViewGroup)) {
            ArrayList<View> viewArrayList = new ArrayList<>();
            viewArrayList.add(v);
            return viewArrayList;
        }

        ArrayList<View> result = new ArrayList<>();

        ViewGroup viewGroup = (ViewGroup) v;
        for (int i = 0; i < viewGroup.getChildCount(); i++) {

            View child = viewGroup.getChildAt(i);

            result.add (child);
        }
        return result;
    }
}
