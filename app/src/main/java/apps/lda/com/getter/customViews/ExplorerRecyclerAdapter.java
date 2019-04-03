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
import apps.lda.com.getter.R;
import apps.lda.com.getter.utils.extraUtils;

public class ExplorerRecyclerAdapter extends RecyclerView.Adapter<ExplorerRecyclerAdapter.ExplorerViewHolder> {
    public ArrayList<RelativeLayout> mDataset = new ArrayList<>();

    private View v;
    public boolean pressed;

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
    public ExplorerRecyclerAdapter(int amount, ExplorerCoordinator parent) {
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
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ExplorerCoordinator c = new ExplorerCoordinator (view.getContext(), pager, adapter);
//                adapter.addView (c);
//                adapter.notifyDataSetChanged();
//                pager.setCurrentItem(adapter.getItemPosition (c), true );
            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return this.amount;
    }

    public void init(final Context ctx){

        // listening for


    }
    final void resetElevs(Context ctx){
        for(View elem : this.mDataset){
            elem.setElevation (extraUtils.fromDpToPx (ctx.getResources ().getDimension (R.dimen.elevation)));
        }
    }
    public void setPressed(boolean prsd){
        this.pressed = prsd;
    }
}
