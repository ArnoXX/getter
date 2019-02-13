package apps.lda.com.getter;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import apps.lda.com.getter.customViews.ExplorerCoordinator;
import apps.lda.com.getter.customViews.ExplorerElementsScroll;
import apps.lda.com.getter.customViews.ExplorerMainScrollLines;
import apps.lda.com.getter.customViews.ExplorerPager;
import apps.lda.com.getter.customViews.ExplorerPagerAdapter;

public class MainActivity extends AppCompatActivity {


    private ExplorerPagerAdapter adapter;
    private ExplorerPager pager;
    private FloatingActionButton fab1;
    private FloatingActionButton fab2;
    private FloatingActionButton fab3;
    private FloatingActionButton fab4;
    private boolean isFABOpen;
    private  ExplorerCoordinator coordinator;
    private  FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        // TODO complete this shit
        HashMap<String, ? extends ViewGroup.LayoutParams> params_set = new HashMap<> (); // TODO organize params hash table
        Typeface roboto_thin = Typeface.createFromAsset (getAssets(),
                "fonts/roboto_thin.ttf");
        adapter = new ExplorerPagerAdapter ((TextView) findViewById (R.id.breadcrumbsLine), (TextView) findViewById (R.id.breadcrumbsLine2));
        pager = findViewById(R.id.pager);
        pager.setAdapter(adapter);


        // ----------
        // TODO complete
        ScrollView.LayoutParams scroll_params = new ScrollView.LayoutParams (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        CoordinatorLayout.LayoutParams coord_params = new CoordinatorLayout.LayoutParams (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        CoordinatorLayout.LayoutParams coord_params1 = new CoordinatorLayout.LayoutParams (ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ArrayList<View> children = new ArrayList<> ();
        LayoutInflater inflater = getLayoutInflater();

        children.add (new ExplorerElementsScroll (this, new ExplorerMainScrollLines (this, coord_params, 30, getWindowManager ().getDefaultDisplay (), pager, adapter), coord_params));
        coordinator = new ExplorerCoordinator (this, children);
        fab1 = (FloatingActionButton) inflater.inflate (R.layout.sub_fab, coordinator, false);
        fab2 = (FloatingActionButton) inflater.inflate (R.layout.sub_fab, coordinator, false);
        fab3 = (FloatingActionButton) inflater.inflate (R.layout.sub_fab, coordinator, false);
        fab4 = (FloatingActionButton) inflater.inflate (R.layout.sub_fab, coordinator, false);
        fab = (FloatingActionButton) inflater.inflate(R.layout.main_fab, coordinator, false);



        // ----------

//        fab1 =  findViewById(R.id.fab1);
//         fab2 =  findViewById(R.id.fab2);
//         fab3 =  findViewById(R.id.fab3);
//         fab4 = findViewById(R.id.fab4);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fab1.getVisibility() == View.INVISIBLE){
                    showFABMenu();
                }else{
                    closeFABMenu();
                }
            }
        });
        adapter.addView (coordinator, 0);
        adapter.notifyDataSetChanged();

        coordinator.addView (fab1);
        coordinator.addView (fab2);
        coordinator.addView (fab3);
        coordinator.addView (fab4);
        coordinator.addView (fab);

    }
    @SuppressLint("RestrictedApi")
    private void showFABMenu(){
        isFABOpen=true;
        fab1.setVisibility(View.VISIBLE);
        fab2.setVisibility(View.VISIBLE);
        fab3.setVisibility(View.VISIBLE);
        fab4.setVisibility(View.VISIBLE);

        fab1.animate().translationY(-getResources().getDimension(R.dimen.standard_55));
        fab2.animate().translationY(-getResources().getDimension(R.dimen.standard_105));
        fab3.animate().translationY(-getResources().getDimension(R.dimen.standard_155));
        fab4.animate().translationY(-getResources().getDimension(R.dimen.standard_205));
    }
    @SuppressLint("RestrictedApi")
    private void closeFABMenu(){
        isFABOpen=false;



        fab1.animate().translationY(0);
        fab1.setVisibility(View.INVISIBLE);
        fab2.animate().translationY(0);
        fab3.animate().translationY(0);
        fab4.animate().translationY(0);


        fab2.setVisibility(View.INVISIBLE);
        fab3.setVisibility(View.INVISIBLE);
        fab4.setVisibility(View.INVISIBLE);


    }
    public void addView (View newPage)
    {
        int pageIndex = adapter.addView (newPage);
        // You might want to make "newPage" the currently displayed page:
        pager.setCurrentItem (pageIndex, true);
    }

    //-----------------------------------------------------------------------------
    // Here's what the app should do to remove a view from the ViewPager.
    public void removeView (View defunctPage)
    {
        int pageIndex = adapter.removeView (pager, defunctPage);
        // You might want to choose what page to display, if the current page was "defunctPage".
        if (pageIndex == adapter.getCount())
            pageIndex--;
        pager.setCurrentItem (pageIndex);
    }
    //-----------------------------------------------------------------------------
    // Here's what the app should do to get the currently displayed page.
    public View getCurrentPage ()
    {
        return adapter.getView (pager.getCurrentItem());
    }

    //-----------------------------------------------------------------------------
    // Here's what the app should do to set the currently displayed page.  "pageToShow" must
    // currently be in the adapter, or this will crash.
    public void setCurrentPage (View pageToShow)
    {
        pager.setCurrentItem (adapter.getItemPosition (pageToShow), true);
    }
}
