package apps.lda.com.getter.customViews;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ExplorerPagerAdapter extends FragmentStatePagerAdapter {
    public ExplorerPagerAdapter(FragmentManager mgr) {
        super(mgr);
    }
    @Override
    public int getCount() {
        return(10);
    }
    @Override
    public Fragment getItem(int position) {
        return(ExplorerFragment.newInstance(position));
    }
}
