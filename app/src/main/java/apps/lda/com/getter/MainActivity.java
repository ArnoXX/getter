package apps.lda.com.getter;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import apps.lda.com.getter.customViews.ExplorerElementView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        getSupportActionBar().hide();
        setContentView (R.layout.activity_main);

        LinearLayout mainScrollLines = findViewById (R.id.mainScrollLines);

       LinearLayout.LayoutParams params = new LinearLayout.LayoutParams (LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
       params.setMargins (fromDpToPx (R.dimen.explorerElementViewLeftMargin),fromDpToPx ( R.dimen.explorerElementViewTopMargin), fromDpToPx (R.dimen.explorerElementViewRightMargin), fromDpToPx (R.dimen.explorerElementViewBottomMargin));

       Drawable bg = getDrawable (R.drawable.explorer_element_view_bg);

        ExplorerElementView element = new ExplorerElementView (this, bg);
        element.setElevation (fromDpToPx (R.dimen.elevation));
        element.setLayoutParams (params);



    }
    private int fromDpToPx(int dp){
        return Math.round(dp*(getResources().getDisplayMetrics().xdpi/ DisplayMetrics.DENSITY_DEFAULT));
    }
}
