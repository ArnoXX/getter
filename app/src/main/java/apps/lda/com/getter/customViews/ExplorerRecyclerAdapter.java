package apps.lda.com.getter.customViews;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import androidx.core.content.res.TypedArrayUtils;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import apps.lda.com.getter.R;
import apps.lda.com.getter.utils.extraUtils;

public class ExplorerRecyclerAdapter extends RecyclerView.Adapter<ExplorerRecyclerAdapter.ExplorerViewHolder> {
    public ArrayList<RelativeLayout> mDataset = new ArrayList<>();

    private View v;
    public boolean pressed;
    private JSONObject data;
    private ArrayList<Object> elems;
    private GridLayoutManager manager;
    private ArrayList<ExplorerViewHolder> views = new ArrayList<>();

    private int amount;
    private ExplorerCoordinator parent;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ExplorerViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View view;
        public TextView textView;
        public boolean imageViewState;
        public TextView ext;
        public ImageButton imageButton;

        public ExplorerViewHolder(View v) {
            super(v);
            view = v;
            textView = v.findViewById(R.id.textView);
            imageViewState = false;
            ext = v.findViewById(R.id.textView2);
            imageButton = v.findViewById(R.id.imageButton);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ExplorerRecyclerAdapter(int amount, ExplorerCoordinator parent, JSONObject data, GridLayoutManager manager) {
        this.amount = amount;
        this.parent = parent;
        this.data = data;
        this.manager = manager;

        elems = new ArrayList<>();
        try {
//            Collections.addAll(elems, this.data.getJSONArray("dirs"));
//            Collections.addAll(elems, this.data.getJSONArray("files"));
            for(int i = 0; i < this.data.getJSONArray("dirs").length(); i++){
                elems.add(i, this.data.getJSONArray("dirs").get(i));
                Log.i("Dir #", String.valueOf(i));
            }
            for(int j = this.data.getJSONArray("dirs").length(); j < (this.data.getJSONArray("dirs").length() + this.data.getJSONArray("files").length()); j++){
                elems.add(j, this.data.getJSONArray("files").get(j - this.data.getJSONArray("dirs").length()));
                Log.i("File #", String.valueOf(j));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String extState = Environment.getExternalStorageState();
//you may also want to add (...|| Environment.MEDIA_MOUNTED_READ_ONLY)
//if you are only interested in reading the filesystem
        if(!extState.equals(Environment.MEDIA_MOUNTED)) {
            //handle error here
        }
        else {
            //do your file work here
        }
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ExplorerRecyclerAdapter.ExplorerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {



        // create a new view
            v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.element_layout, parent, false);
        RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        params.setMargins(
                0,
                extraUtils.fromDpToPx(v.getContext().getResources().getDimension(R.dimen.explorerElementViewTopMargin)) / 2,
                extraUtils.fromDpToPx(v.getContext().getResources().getDimension(R.dimen.explorerElementViewRightMargin)) / 2,
                0);
        v.setLayoutParams(params);
        ExplorerViewHolder vh = new ExplorerViewHolder(v);
        views.add(vh);

        this.mDataset.add((RelativeLayout) v);

        init(parent.getContext());
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ExplorerViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        //File root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();

        if(views.contains(holder)){
            final int pos = views.indexOf(holder);
            try {
                String name = ((JSONObject) elems.get(pos)).getString("name");
                holder.textView.setText(name);
                if(((JSONObject) elems.get(pos)).has("ext")){
                    holder.ext.setText(((JSONObject) elems.get(pos)).getString("ext"));
                    holder.imageButton.setImageDrawable(holder.view.getContext().getResources().getDrawable(R.drawable.file_drawable));
                } else {
                    holder.ext.setText(String.valueOf(((JSONObject) elems.get(pos)).getJSONObject("children").getJSONArray("dirs").length()) + " dirs; " + String.valueOf(((JSONObject) elems.get(pos)).getJSONObject("children").getJSONArray("files").length()) + " files");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            // File[] str = root.listFiles();
            //This will return an array with all the Files (directories and files) String.format("%d", root.listFiles().length)
            //in the external storage folder
            //holder.textView.setText(new File(String.valueOf(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS))).getParent());

            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(((JSONObject) elems.get(pos)).has("children")){
                        try {
                            parent.loadData(((JSONObject) elems.get(pos)));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } // else if(((JSONObject) elems.get(position)).has("ext")){
//                   holder.textView.setSingleLine(!holder.textViewState);
//                   holder.textViewState = !holder.textViewState;
//                }
                }
            });
            holder.imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(holder.imageViewState){
                        // deselect

                        holder.imageButton.setColorFilter(v.getContext().getResources().getColor(R.color.dark, v.getContext().getTheme()));
                        holder.imageViewState = false;
                    } else {
                        // select
                        holder.imageButton.setColorFilter(v.getContext().getResources().getColor(R.color.colorAccent, v.getContext().getTheme()));
                        holder.imageViewState = true;
                    }
                }
            });
        }
        if(position % manager.getSpanCount() == 0){
            RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            params.setMargins(
                    extraUtils.fromDpToPx(holder.view.getContext().getResources().getDimension(R.dimen.explorerElementViewLeftMargin)) / 2,
                    extraUtils.fromDpToPx(holder.view.getContext().getResources().getDimension(R.dimen.explorerElementViewTopMargin)) / 2,
                    extraUtils.fromDpToPx(holder.view.getContext().getResources().getDimension(R.dimen.explorerElementViewRightMargin)) / 2,
                    0);

            holder.view.setLayoutParams(params);

        }


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return this.amount;
    }

    public void init(final Context ctx){

        // listening for


    }
    public void deselectAll(){
        for(ExplorerViewHolder viewHolder : this.views){
            viewHolder.imageButton.setColorFilter(v.getContext().getResources().getColor(R.color.dark, v.getContext().getTheme()));
            viewHolder.imageViewState = false;
        }
    }
}
