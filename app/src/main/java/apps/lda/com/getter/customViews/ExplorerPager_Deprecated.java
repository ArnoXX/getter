package apps.lda.com.getter.customViews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;
import apps.lda.com.getter.R;
import apps.lda.com.getter.utils.extraUtils;

public class ExplorerPager_Deprecated extends ViewPager {
    private ExplorerRecycler.ExplorerPagerAdapter adapter;
    public ExplorerPager_Deprecated(@NonNull Context ctx) {
        super (ctx);
    }

    public ExplorerPager_Deprecated(@NonNull Context ctx, @Nullable AttributeSet attrs) {
        super (ctx, attrs);


    }
    final void resetElevs(Context ctx){
        for(View elem : ((ExplorerCoordinator) this.adapter.getView(this.getCurrentItem())).recyclerAdapter.mDataset){
            elem.setElevation (extraUtils.fromDpToPx (ctx.getResources ().getDimension (R.dimen.elevation)));
        }
    }
    public void launchListener(){
        this.adapter = (ExplorerRecycler.ExplorerPagerAdapter) getAdapter();
        this.addOnPageChangeListener (new ViewPager.OnPageChangeListener ( ) {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    adapter.previous = adapter.current;

            }
            @Override
            public void onPageSelected(int position) {


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
            @Override
            public void onPageScrollStateChanged(int state) {
//                if(state == SCROLL_STATE_IDLE){
//                    resetElevs(adapter.label.getContext());
//                    int i = adapter.getCount ( ) - 1;
//                    while (i > adapter.current) {
//                        removeView (adapter.getView (i));
//                        i--;
//                    }
//                    ((ExplorerCoordinator) ((ExplorerPagerAdapter)getAdapter()).getView(getCurrentItem())).child = null;
//                }
            }
        });
    }
    public void removeView (View defunctPage)
    {
        //int pageIndex =
                adapter.removeView (this, defunctPage);
        // You might want to choose what page to display, if the current page was "defunctPage".
//        if (pageIndex == adapter.getCount())
//            pageIndex--;
//        this.setCurrentItem (pageIndex, true);
    }
}
