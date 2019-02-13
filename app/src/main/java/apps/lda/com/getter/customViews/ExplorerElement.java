package apps.lda.com.getter.customViews;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.RelativeLayout;

import androidx.interpolator.view.animation.LinearOutSlowInInterpolator;
import apps.lda.com.getter.R;
import apps.lda.com.getter.utils.extraUtils;

public class ExplorerElement extends RelativeLayout {
    private ViewGroup.LayoutParams params;
    private Drawable bg;
    private int elevation;
    private View[] children;
    private int id;
    private int animationDur;
    private ObjectAnimator alpha;
    private ObjectAnimator elevationAnim;
    private Context context;
    private int startpos;
    private int delay;
    public ExplorerElement(Context context, ViewGroup.LayoutParams params, Drawable bg, int elevation, View[] children, int id, int animationDur, int delay) {
        super (context);
        this.params = params;
        this.bg = bg;
        this.elevation = elevation;
        this.children = children;
        this.id = id;
        this.animationDur = animationDur;
        this.context = context;
        this.delay = delay;
        init ();
    }
    private void init(){
        this.setAlpha (0);
        this.setId (this.id);
        this.setLayoutParams (this.params);
        this.setBackground (this.bg);
        this.setElevation (this.elevation);
        for(View child : this.children){
            this.addView (child);
        }

        this.alpha = ObjectAnimator.ofFloat(this, "alpha",0f, 1f);
        this.alpha.setStartDelay (this.delay);
        this.alpha.setDuration(this.animationDur);

        this.elevationAnim = ObjectAnimator.ofFloat(this, "elevation", 0f, getResources ().getDimension (R.dimen.elevation));
        this.elevationAnim.setStartDelay (this.delay*2);
        this.elevationAnim.setDuration(this.animationDur);

    }
    public AnimatorSet getElemAnimator(){
        AnimatorSet set = new AnimatorSet ();
        set.play (this.alpha).with (this.elevationAnim);
        return set;
    }
}
