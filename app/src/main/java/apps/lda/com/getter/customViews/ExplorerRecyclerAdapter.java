package apps.lda.com.getter.customViews;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import apps.lda.com.getter.R;
import apps.lda.com.getter.utils.extraUtils;

public class ExplorerRecyclerAdapter extends RecyclerView.Adapter<ExplorerRecyclerAdapter.ExplorerViewHolder> {
    private ArrayList<ExplorerElement> mDataset = new ArrayList<>();
    private View v;
    private boolean pressed;
    private ExplorerPager pager;
    private ExplorerPagerAdapter adapter;
    private int amount;

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
            textView = v.findViewById(R.id.textView);
            imageButton = v.findViewById(R.id.imageButton);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ExplorerRecyclerAdapter(int amount, ExplorerPager pager, ExplorerPagerAdapter adapter) {
        this.pager = pager;
        this.adapter = adapter;
        this.amount = amount;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ExplorerRecyclerAdapter.ExplorerViewHolder onCreateViewHolder(ViewGroup parent,
                                                                         int viewType) {
        // create a new view
        v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.element_layout_test, parent, false);

        animateElement(parent.getContext(), (ExplorerElement) v);
        ExplorerViewHolder vh = new ExplorerViewHolder(v);

        this.mDataset.add((ExplorerElement) v);

        init(parent.getContext());

        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ExplorerViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
//        holder.textView.setText(mDataset.get(position));

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return this.amount;
    }

    public void init(final Context ctx){

        // listening for

        for (final View elem : this.mDataset){
            elem.setOnTouchListener (new View.OnTouchListener ( ) {
                @Override
                public boolean onTouch (View v, MotionEvent event) {
                    if(event.getAction () == MotionEvent.ACTION_DOWN){
                        resetElevs(ctx);
                        pressed = true;
                        int i = adapter.getCount () - 1;
                        while(i > adapter.current){
                            removeView (adapter.getView (i));
                            i--;
                        }
                        //adapter.addView ( new ExplorerCoordinator (ctx));
                        adapter.notifyDataSetChanged();
                        v.setElevation (0);
                        return true;
                    } else if (event.getAction () == MotionEvent.ACTION_UP){
                        pressed = false;
                        resetElevs (ctx);
                        v.setElevation (extraUtils.fromDpToPx (ctx.getResources ().getDimension (R.dimen.elevation)));
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
                        for (ExplorerElement elem : mDataset) {
                            animateElement(ctx, elem);
                        }

                    }

                }

            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


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

    public void animateElement(Context ctx, ExplorerElement element){
        ObjectAnimator alpha;
        ObjectAnimator elevationAnim;


        alpha = ObjectAnimator.ofFloat(element, "alpha",0f, 1f);
        alpha.setStartDelay (0);
        alpha.setDuration(30);

        elevationAnim = ObjectAnimator.ofFloat(element, "elevation", 0f, ctx.getResources ().getDimension (R.dimen.elevation));
        elevationAnim.setStartDelay (0);
        elevationAnim.setDuration(120);

        AnimatorSet set = new AnimatorSet ();
        set.play (alpha).before (elevationAnim);

        set.start();
    }
}
