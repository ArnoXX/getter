package apps.lda.com.getter.customViews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import apps.lda.com.getter.R;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;
import apps.lda.com.getter.utils.extraUtils;

public class ExplorerPager extends ViewPager {
    private ExplorerPagerAdapter adapter;
    public ExplorerPager(@NonNull Context ctx) {
        super (ctx);
    }

    public ExplorerPager(@NonNull Context ctx, @Nullable AttributeSet attrs) {
        super (ctx, attrs);


    }
    final void resetElevs(Context ctx){
        for(View elem : ((ExplorerCoordinator) this.adapter.getView(this.getCurrentItem())).recyclerAdapter.mDataset){
            elem.setElevation (extraUtils.fromDpToPx (ctx.getResources ().getDimension (R.dimen.elevation)));
        }
    }
    public void launchListener(){
        this.adapter = (ExplorerPagerAdapter) getAdapter();
        this.addOnPageChangeListener (new ViewPager.OnPageChangeListener ( ) {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (((ExplorerCoordinator) ((ExplorerPagerAdapter)getAdapter()).getView(getCurrentItem())).recyclerAdapter.pressed) {
                    adapter.previous = adapter.current;
                }
            }
            @Override
            public void onPageSelected(int position) {

                if(((ExplorerCoordinator) ((ExplorerPagerAdapter)getAdapter()).getView(getCurrentItem())).recyclerAdapter.pressed) {
                    adapter.current = position;
                    adapter.label.setText (String.format ("Count: %d", adapter.getCount ()));
                    adapter.label2.setText (String.format ("Current: %d", adapter.current));
                    resetElevs(adapter.label.getContext());
                    if (adapter.current < adapter.previous) {
                        int i = adapter.getCount ( ) - 1;
                        while (i > adapter.current) {
                            removeView (adapter.getView (i));
                            i--;
                        }
                    }

                }

            }
            @Override
            public void onPageScrollStateChanged(int state) {
                if(state == SCROLL_STATE_IDLE){
                    resetElevs(adapter.label.getContext());
                    int i = adapter.getCount ( ) - 1;
                    while (i > adapter.current) {
                        removeView (adapter.getView (i));
                        i--;
                    }
                    ((ExplorerCoordinator) ((ExplorerPagerAdapter)getAdapter()).getView(getCurrentItem())).child = null;
                }
            }
        });
    }
    public void removeView (View defunctPage)
    {
        int pageIndex = adapter.removeView (this, defunctPage);
        // You might want to choose what page to display, if the current page was "defunctPage".
        if (pageIndex == adapter.getCount())
            pageIndex--;
        this.setCurrentItem (pageIndex, true);
    }
}
