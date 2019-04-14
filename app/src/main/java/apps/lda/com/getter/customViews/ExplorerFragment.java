package apps.lda.com.getter.customViews;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import apps.lda.com.getter.R;

public class ExplorerFragment extends Fragment {
    private JSONObject data;
    private TextView breadcrumbs;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.explorer_fragment, container, false);

        FrameLayout frame = view.findViewById(R.id.frame);

        ExplorerCoordinator c = new ExplorerCoordinator(getContext());

        try {
            c.loadData(this.data);
            c.setBreadcrubs(this.breadcrumbs);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        c.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        frame.addView(c);
        return view;
    }
    public void setRoot(JSONObject root) {
        this.data = root;
    }
    public void setBreadcrumbs(TextView v){
        this.breadcrumbs = v;
    }
}
