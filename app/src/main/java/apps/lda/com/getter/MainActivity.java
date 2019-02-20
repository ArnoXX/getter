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
//    private FloatingActionButton fab1;
//    private FloatingActionButton fab2;
//    private FloatingActionButton fab3;
//    private FloatingActionButton fab4;
//    private boolean isFABOpen;
    private  ExplorerCoordinator coordinator;
    private  FloatingActionButton fab;
    private TextView txt1;
    private TextView txt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        // TODO complete this shit
        HashMap<String, ? extends ViewGroup.LayoutParams> params_set = new HashMap<> (); // TODO organize params hash table
        Typeface roboto_thin = Typeface.createFromAsset (getAssets(),
                "fonts/roboto_thin.ttf");

        txt1 = (TextView) findViewById (R.id.breadcrumbsLine);
        txt2 = (TextView) findViewById (R.id.breadcrumbsLine2);
        adapter = new ExplorerPagerAdapter (txt1, txt2);
        pager = findViewById(R.id.pager);
        pager.setAdapter(adapter);


        // ----------
        // TODO complete
        CoordinatorLayout.LayoutParams coord_params = new CoordinatorLayout.LayoutParams (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        coordinator = new ExplorerCoordinator (this, new ExplorerElementsScroll (this, new ExplorerMainScrollLines (this, coord_params, 30, getWindowManager ().getDefaultDisplay (), pager, adapter), coord_params));
        // ----------
        adapter.addView (coordinator, 0);
        adapter.notifyDataSetChanged();

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
