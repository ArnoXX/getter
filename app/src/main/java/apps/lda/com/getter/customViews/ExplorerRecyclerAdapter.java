package apps.lda.com.getter.customViews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import apps.lda.com.getter.R;
import apps.lda.com.getter.utils.extraUtils;

import static androidx.viewpager.widget.ViewPager.SCROLL_STATE_IDLE;

public class ExplorerRecyclerAdapter extends RecyclerView.Adapter<ExplorerRecyclerAdapter.ExplorerViewHolder> {
    public ArrayList<RelativeLayout> mDataset = new ArrayList<>();

    private View v;
    public boolean pressed;
    private ExplorerPager pager;
    private ExplorerPagerAdapter adapter;
    private int amount;
    private ExplorerCoordinator parent;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ExplorerViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View view;
        public TextView textView;
        public ImageButton imageButton;

        public ExplorerViewHolder(View v) {
            super(v);
            view = v;
            textView = v.findViewById(R.id.textView);
            imageButton = v.findViewById(R.id.imageButton);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ExplorerRecyclerAdapter(int amount, ExplorerPager pager, ExplorerPagerAdapter adapter, ExplorerCoordinator parent) {
        this.pager = pager;
        this.adapter = adapter;
        this.amount = amount;
        this.parent = parent;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ExplorerRecyclerAdapter.ExplorerViewHolder onCreateViewHolder(ViewGroup parent,
                                                                         int viewType) {
        // create a new view
        v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.element_layout, parent, false);
        ExplorerViewHolder vh = new ExplorerViewHolder(v);

        this.mDataset.add((RelativeLayout) v);

        init(parent.getContext());

        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ExplorerViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
//        holder.textView.setText(mDataset.get(position));

//        holder.view.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if(event.getAction () == MotionEvent.ACTION_DOWN){
//                    v.setElevation(0);
//                } else if(event.getAction () == MotionEvent.ACTION_UP){
//                    v.setElevation(extraUtils.fromDpToPx (v.getContext().getResources().getDimension (R.dimen.elevation)));
//                    resetElevs(v.getContext());
//                }
//                return false;
//            }
//        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return this.amount;
    }

    public void init(final Context ctx){

        // listening for


//        pager.addOnPageChangeListener (new ViewPager.OnPageChangeListener ( ) {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                if (pressed) {
//                    adapter.previous = adapter.current;
//                }
//            }
//            @Override
//            public void onPageSelected(int position) {
//
//                if(pressed) {
//                    adapter.current = position;
//                    adapter.label.setText (String.format ("Count: %d", adapter.getCount ()));
//                    adapter.label2.setText (String.format ("Current: %d", adapter.current));
//                    resetElevs(ctx);
//                    if (adapter.current < adapter.previous) {
//                        int i = adapter.getCount ( ) - 1;
//                        while (i > adapter.current) {
//                            removeView (adapter.getView (i));
//                            i--;
//                        }
//                    }
//
//                }
//
//            }
//            @Override
//            public void onPageScrollStateChanged(int state) {
//                if(state == SCROLL_STATE_IDLE){
//                    resetElevs(ctx);
//                    int i = adapter.getCount ( ) - 1;
//                    while (i > adapter.current) {
//                        removeView (adapter.getView (i));
//                        i--;
//                    }
//                    parent.child = null;
//                }
//            }
//        });
    }
    final void resetElevs(Context ctx){
        for(View elem : this.mDataset){
            elem.setElevation (extraUtils.fromDpToPx (ctx.getResources ().getDimension (R.dimen.elevation)));
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
    public void setPressed(boolean prsd){
        this.pressed = prsd;
    }
}
