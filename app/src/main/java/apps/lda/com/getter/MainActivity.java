package apps.lda.com.getter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import apps.lda.com.getter.utils.ShadowUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        getSupportActionBar().hide();
        setContentView (R.layout.activity_main);
        RelativeLayout searchBar = findViewById (R.id.searchbar);
        searchBar.setBackground (ShadowUtil.getShadow (searchBar, R.color.darkgrey, R.dimen.zeroBorderRadius, R.color.shadow, R.dimen.elevation, Gravity.CENTER));
        LinearLayout breadcrumbs = findViewById (R.id.breadcrumbs);
        breadcrumbs.setBackground (ShadowUtil.getShadow (breadcrumbs, R.color.grey, R.dimen.zeroBorderRadius, R.color.shadow, R.dimen.elevation, Gravity.CENTER));

    }
}
