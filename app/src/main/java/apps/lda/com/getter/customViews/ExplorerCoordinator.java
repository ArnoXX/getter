package apps.lda.com.getter.customViews;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Stack;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import apps.lda.com.getter.R;

public class ExplorerCoordinator extends CoordinatorLayout {

    private JSONObject data;

    private ExplorerRecycler recycler;
    private GridLayoutManager manager;
    public ExplorerRecyclerAdapter recyclerAdapter;
    private Stack<JSONObject> path = new Stack<>();
    private Stack<String> breadcr = new Stack<>();

    private TextView breadcrumbs;

    private CoordinatorLayout.LayoutParams lay_params;

    private FloatingActionButton mainFab;
    private FloatingActionButton subFab1;
    private FloatingActionButton subFab2;
    private FloatingActionButton subFab3;
    private FloatingActionButton subFab4;

    private Context context;

    private LayoutInflater inflater;
    public ExplorerCoordinator(@NonNull Context context) {
        super (context);
        this.inflater = LayoutInflater.from(context);

        this.context = context;

        this.subFab1 = (FloatingActionButton) inflater.inflate (R.layout.sub_fab0, this, false);
        this.subFab2 = (FloatingActionButton) inflater.inflate (R.layout.sub_fab1, this, false);
        this.subFab3 = (FloatingActionButton) inflater.inflate (R.layout.sub_fab2, this, false);
        this.subFab4 = (FloatingActionButton) inflater.inflate (R.layout.sub_fab3, this, false);
        this.mainFab = (FloatingActionButton) inflater.inflate(R.layout.main_fab, this, false);

        init();
    }
    private void init(){
        this.breadcr.push("0/");
    }

    @SuppressLint("RestrictedApi")
    private void showFABMenu(){
        this.subFab1.setVisibility(View.VISIBLE);
        this.subFab2.setVisibility(View.VISIBLE);
        this.subFab3.setVisibility(View.VISIBLE);
        this.subFab4.setVisibility(View.VISIBLE);

        this.subFab1.animate().translationY(-getResources().getDimension(R.dimen.standard_55));
        this.subFab2.animate().translationY(-getResources().getDimension(R.dimen.standard_105));
        this.subFab3.animate().translationY(-getResources().getDimension(R.dimen.standard_155));
        this.subFab4.animate().translationY(-getResources().getDimension(R.dimen.standard_205));
    }
    @SuppressLint("RestrictedApi")
    public void closeFABMenu(){
        this.subFab1.animate().translationY(0);
        this.subFab2.animate().translationY(0);
        this.subFab3.animate().translationY(0);
        this.subFab4.animate().translationY(0);

        this.subFab1.setVisibility(View.INVISIBLE);
        this.subFab2.setVisibility(View.INVISIBLE);
        this.subFab3.setVisibility(View.INVISIBLE);
        this.subFab4.setVisibility(View.INVISIBLE);
    }

    public void render() throws JSONException {
        this.path.push(this.data);
        this.removeAllViews();

        this.recycler = new ExplorerRecycler(this.context);

        this.manager = new GridLayoutManager(this.context, 4);

        this.recycler.setLayoutManager(this.manager);

        this.recycler.setItemAnimator(new DefaultItemAnimator());

        int am = this.data.getJSONArray("dirs").length() + this.data.getJSONArray("files").length();

        this.recyclerAdapter = new ExplorerRecyclerAdapter(am, this, this.data.getJSONObject("children"), this.manager);
        this.recycler.setAdapter(this.recyclerAdapter);

        this.lay_params = new CoordinatorLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        this.recycler.setLayoutParams(this.lay_params);

        this.addView(this.recycler);

        this.addView (this.subFab1);
        this.addView (this.subFab2);
        this.addView (this.subFab3);
        this.addView (this.subFab4);
        this.addView (this.mainFab);

        this.mainFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(subFab1.getTranslationY() == 0){
                    try {
                        loadPreviousData();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    mainFab.setImageDrawable(view.getContext().getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp, view.getContext().getTheme()));
                    closeFABMenu();
                }
            }
        });
        this.mainFab.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(subFab1.getTranslationY() == 0){
                    mainFab.setImageDrawable(v.getContext().getResources().getDrawable(R.drawable.ic_plus, v.getContext().getTheme()));
                    showFABMenu();
                    return true;
                }
                return false;
            }
        });
        this.subFab1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerAdapter.deselectAll();
            }
        });
    }

    public void loadData(JSONObject obj) throws JSONException {
        this.data = obj;

        render();

        if(!this.data.getString("name").contentEquals("root")){
            this.breadcr.push(this.breadcr.peek() + this.data.getString("name"));
            this.breadcrumbs.setText(this.breadcr.peek());
        }
    }
    public JSONObject getData(){
        return this.data;
    }

    public void loadPreviousData() throws JSONException {
        JSONObject popped = this.path.pop();
        if(this.path.empty()){
            this.path.push(popped);
        } else {
            this.loadData(this.path.pop());
        }

    }
    public void setBreadcrubs(TextView v){
        this.breadcrumbs = v;
        this.breadcrumbs.setText(this.breadcr.peek());
    }
}
