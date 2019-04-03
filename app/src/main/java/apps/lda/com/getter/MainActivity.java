package apps.lda.com.getter;


import android.app.Fragment;
import android.app.FragmentManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import apps.lda.com.getter.customViews.ExplorerCoordinator;
import apps.lda.com.getter.customViews.ExplorerFragment;
import apps.lda.com.getter.customViews.ExplorerPager_Deprecated;
import apps.lda.com.getter.customViews.ExplorerRecycler;

public class MainActivity extends AppCompatActivity {


    private ExplorerRecycler.ExplorerPagerAdapter adapter;
    private ExplorerPager_Deprecated pager;
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
        Typeface roboto_thin = Typeface.createFromAsset (getAssets(),
                "fonts/roboto_thin.ttf");

        txt1 = (TextView) findViewById (R.id.breadcrumbsLine);


        Fragment fr = new ExplorerFragment();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.main_frame, fr).commit();

    }
}
