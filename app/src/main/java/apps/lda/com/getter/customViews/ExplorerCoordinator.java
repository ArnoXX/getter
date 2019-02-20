package apps.lda.com.getter.customViews;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import apps.lda.com.getter.R;

public class ExplorerCoordinator extends CoordinatorLayout {
    private ExplorerElementsScroll child ;
    private FloatingActionButton mainFab;
    private FloatingActionButton subFab1;
    private FloatingActionButton subFab2;
    private FloatingActionButton subFab3;
    private FloatingActionButton subFab4;

    private LayoutInflater inflater;
    public ExplorerCoordinator(@NonNull Context context, ExplorerElementsScroll child ) {
        super (context);
        this.inflater = LayoutInflater.from(context);

        this.subFab1 = (FloatingActionButton) inflater.inflate (R.layout.sub_fab, this, false);
        this.subFab2 = (FloatingActionButton) inflater.inflate (R.layout.sub_fab, this, false);
        this.subFab3 = (FloatingActionButton) inflater.inflate (R.layout.sub_fab, this, false);
        this.subFab4 = (FloatingActionButton) inflater.inflate (R.layout.sub_fab, this, false);
        this.mainFab = (FloatingActionButton) inflater.inflate(R.layout.main_fab, this, false);

        this.child = child;
        init();
    }
    private void init(){

            this.addView (this.child);

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
}
