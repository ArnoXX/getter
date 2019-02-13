package apps.lda.com.getter.customViews;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.viewpager.widget.ViewPager;
import apps.lda.com.getter.R;
import apps.lda.com.getter.utils.extraUtils;

public class ExplorerMainScrollLines extends LinearLayout {

    private ViewGroup.LayoutParams params;
    private Context context;
    private ArrayList<View> elementList = new ArrayList<> ();
    private ArrayList<ExplorerElement> elems;
    private Display display;
    private int lines;
    private ExplorerPager pager;
    private ExplorerPagerAdapter adapter;
    private boolean pressed = false;
    private NestedScrollView.LayoutParams scroll_params = new NestedScrollView.LayoutParams (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

    public ExplorerMainScrollLines(Context context, ViewGroup.LayoutParams params, int lines, Display display, ExplorerPager pager, ExplorerPagerAdapter adapter) {
        super (context);
        this.params = params;
        this.context = context;
        this.display = display;
        this.lines = lines;
        this.pager = pager;
        this.adapter = adapter;
        init ();
    }
    private void init(){
        this.setOrientation (LinearLayout.VERTICAL);
        this.setLayoutParams (this.params);


        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams last_params = new LinearLayout.LayoutParams (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams icon_params = new RelativeLayout.LayoutParams (extraUtils.fromDpToPx (getResources ().getDimension (R.dimen.explorerElementIconWidth)), extraUtils.fromDpToPx (getResources ().getDimension (R.dimen.explorerElementIconHeight)));
        RelativeLayout.LayoutParams label_params = new RelativeLayout.LayoutParams (ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        params.setMargins (extraUtils.fromDpToPx (getResources ().getDimension (R.dimen.explorerElementViewLeftMargin)), extraUtils.fromDpToPx (getResources ().getDimension (R.dimen.explorerElementViewTopMargin)), extraUtils.fromDpToPx (getResources ().getDimension (R.dimen.explorerElementViewRightMargin)), extraUtils.fromDpToPx (getResources ().getDimension (R.dimen.explorerElementViewBottomMargin)));
        last_params.setMargins (extraUtils.fromDpToPx (getResources ().getDimension (R.dimen.explorerElementViewLeftMargin)), extraUtils.fromDpToPx (getResources ().getDimension (R.dimen.explorerElementViewTopMargin)), extraUtils.fromDpToPx (getResources ().getDimension (R.dimen.explorerElementViewRightMargin)), extraUtils.fromDpToPx (getResources ().getDimension (R.dimen.explorerElementViewBottomMargin_Last)));
        icon_params.addRule (RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
        label_params.addRule (RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
        label_params.addRule (RelativeLayout.END_OF, R.id.explorer_element_icon);
        label_params.setMarginStart (extraUtils.fromDpToPx (getResources ().getDimension (R.dimen.explorerElementLabelMarginStart)));
        icon_params.setMargins(extraUtils.fromDpToPx (getResources ().getDimension (R.dimen.explorerElementIconLeftMargin)), extraUtils.fromDpToPx (getResources ().getDimension (R.dimen.explorerElementIconTopMargin)), extraUtils.fromDpToPx (getResources ().getDimension (R.dimen.explorerElementIconRightMargin)), extraUtils.fromDpToPx (getResources ().getDimension (R.dimen.explorerElementIconBottomMargin)));

        Typeface roboto_thin = Typeface.createFromAsset (getResources ().getAssets(),
                "fonts/roboto_thin.ttf");

        DisplayMetrics outMetrics = new DisplayMetrics ();
        this.display.getMetrics(outMetrics);

        float density  = getResources().getDisplayMetrics().density;
        float dpWidth  = outMetrics.widthPixels / density;

        for(int i = 0; i < this.lines; i++){
            ExplorerElementIcon icon = new ExplorerElementIcon (this.context, icon_params, getResources ().getDrawable (R.drawable.folder_drawable));
            icon.setId (R.id.explorer_element_icon);
            TextView txt = new TextView (this.context);
            txt.setText (String.format ("%d", i));
            txt.setTextColor (getResources ().getColor (R.color.dark));
            txt.setTypeface (roboto_thin);
            txt.setLayoutParams (label_params);
            View[] children = {icon, txt};
            ExplorerElement elem = new ExplorerElement (this.context, i == this.lines - 1 ? last_params : params, getResources ().getDrawable (R.drawable.explorer_element_view_bg), extraUtils.fromDpToPx (getResources ().getDimension (R.dimen.elevation)), children, R.id.explorer_element, 470, i < 20 ? i*20 : 400);
            elementList.add (elem);
            elem.getElemAnimator ().start ();
        }

        for(View elem : elementList){
            this.addView(elem);
        }

        elems = extraUtils.getChildren (this);
        for (final View elem : elems){
            elem.setOnTouchListener (new View.OnTouchListener ( ) {
                @Override
                public boolean onTouch (View v, MotionEvent event) {
                    if(event.getAction () == MotionEvent.ACTION_DOWN){
                        resetElevs();
                        pressed = true;
                            int i = adapter.getCount () - 1;
                            while(i > adapter.current){
                                removeView (adapter.getView (i));
                                i--;
                            }
                            adapter.addView (new ExplorerElementsScroll (context, new ExplorerMainScrollLines (context, scroll_params, 10, display, pager, adapter), scroll_params));
                            adapter.notifyDataSetChanged();
                        v.setElevation (0);
                        return true;
                    } else if (event.getAction () == MotionEvent.ACTION_UP){
                        pressed = false;
                        resetElevs ();
                        v.setElevation (extraUtils.fromDpToPx (getResources ().getDimension (R.dimen.elevation)));
                        int i = adapter.getCount ( ) - 1;
                        while (i > adapter.current) {
                            removeView (adapter.getView (i));
                            i--;
                        }
                        return true;
                    }
                    return false;
                }
            });
        }
        pager.addOnPageChangeListener (new ViewPager.OnPageChangeListener ( ) {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (pressed) {
                    adapter.previous = adapter.current;
                }
            }
            @Override
                public void onPageSelected(int position) {

                if(pressed) {
                    adapter.current = position;
                    adapter.label.setText (String.format ("Count: %d", adapter.getCount ()));
                    adapter.label2.setText (String.format ("Current: %d", adapter.current));
                    if (adapter.current < adapter.previous) {
                        int i = adapter.getCount ( ) - 1;
                        while (i > adapter.current) {
                            removeView (adapter.getView (i));
                            i--;
                        }
                    } else if(adapter.current > adapter.previous){
                        for (ExplorerElement elem : elems) {
                            elem.getElemAnimator ( ).start ( );
                        }

                    }

                }

            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    final void resetElevs(){
        for(View elem : elems){
            elem.setElevation (extraUtils.fromDpToPx (getResources ().getDimension (R.dimen.elevation)));
        }
    }
    public void removeView (View defunctPage)
    {
        int pageIndex = adapter.removeView (pager, defunctPage);
        // You might want to choose what page to display, if the current page was "defunctPage".
        if (pageIndex == adapter.getCount())
            pageIndex--;
        pager.setCurrentItem (pageIndex, true);
    }
}
