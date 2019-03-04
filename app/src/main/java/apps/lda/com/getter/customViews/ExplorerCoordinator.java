package apps.lda.com.getter.customViews;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import apps.lda.com.getter.R;
import apps.lda.com.getter.utils.extraUtils;

public class ExplorerCoordinator extends CoordinatorLayout {
    private ExplorerPager pager;
    private ExplorerPagerAdapter adapter;

    private ExplorerRecycler recycler;
    private LinearLayoutManager manager;
    private ExplorerRecyclerAdapter recyclerAdapter;

    private CoordinatorLayout.LayoutParams lay_params;

    private FloatingActionButton mainFab;
    private FloatingActionButton subFab1;
    private FloatingActionButton subFab2;
    private FloatingActionButton subFab3;
    private FloatingActionButton subFab4;

    private Context context;

    private LayoutInflater inflater;
    public ExplorerCoordinator(@NonNull Context context, ExplorerPager pager, ExplorerPagerAdapter adapter) {
        super (context);
        this.inflater = LayoutInflater.from(context);

        this.context = context;

        this.pager = pager;
        this.adapter = adapter;

        this.subFab1 = (FloatingActionButton) inflater.inflate (R.layout.sub_fab, this, false);
        this.subFab2 = (FloatingActionButton) inflater.inflate (R.layout.sub_fab, this, false);
        this.subFab3 = (FloatingActionButton) inflater.inflate (R.layout.sub_fab, this, false);
        this.subFab4 = (FloatingActionButton) inflater.inflate (R.layout.sub_fab, this, false);
        this.mainFab = (FloatingActionButton) inflater.inflate(R.layout.main_fab, this, false);

        init();
    }
    private void init(){

        this.recycler = new ExplorerRecycler(this.context);

        this.manager = new LinearLayoutManager(this.context);
        this.recycler.setLayoutManager(this.manager);

        this.recycler.setItemAnimator(new DefaultItemAnimator());

        this.recyclerAdapter = new ExplorerRecyclerAdapter(40, this.pager, this.adapter);
        this.recycler.setAdapter(this.recyclerAdapter);

        this.lay_params = new CoordinatorLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

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
                    showFABMenu();
                }else{
                    closeFABMenu();
                }
            }
        });

        this.recycler.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent event) {
                if(event.getAction () == MotionEvent.ACTION_DOWN){
                    recyclerAdapter.setPressed(true);
                    int i = adapter.getCount () - 1;
                    while(i > adapter.current){
                        removeView (adapter.getView (i));
                        i--;
                    }
                    adapter.addView ( new ExplorerCoordinator (context, pager, adapter));
                    adapter.notifyDataSetChanged();
                    return true;
                } else if (event.getAction () == MotionEvent.ACTION_UP){
                    recyclerAdapter.setPressed(false);
                    int i = adapter.getCount ( ) - 1;
                    while (i > adapter.current) {
                        removeView (adapter.getView (i));
                        i--;
                    }
                    return true;
                }
                return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent event) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
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
    public void removeView (View defunctPage)
    {
        int pageIndex = adapter.removeView (pager, defunctPage);
        // You might want to choose what page to display, if the current page was "defunctPage".
        if (pageIndex == adapter.getCount())
            pageIndex--;
        pager.setCurrentItem (pageIndex, true);
    }
}
