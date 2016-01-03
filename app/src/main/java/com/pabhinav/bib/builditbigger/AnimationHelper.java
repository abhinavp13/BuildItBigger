package com.pabhinav.bib.builditbigger;

import android.content.Context;
import android.view.View;
import android.widget.ViewAnimator;
import android.widget.ViewFlipper;

import com.pabhinav.bib.flipanimation.AnimationFactory;
import com.pabhinav.bib.jokesupplyandroidlibrary.FlipAnimationHelper;

/**
 * This class helps set up 3d flip animation for a view flipper.
 * Also, updates the corresponding fragment, whenever a flip is done.
 * This update includes setting up new joke in their designated text views.
 *
 * @author pabhinav.
 */
public class AnimationHelper extends FlipAnimationHelper {

    /**
     * Need activity context.
     */
    private Context context;

    /**
     * Constructor used to initialize context.
     * @param context
     */
    public AnimationHelper(Context context){
        this.context = context;
    }

    /**
     * This method binds flip animation with view flipper.
     * Whenever view flipper is clicked, flip animation is triggered.
     *
     * @param viewFlipper
     */
    @Override
    public void doSetUp(ViewFlipper viewFlipper) {

        /** Transform to view animator **/
        final ViewAnimator viewAnimator = (ViewAnimator)viewFlipper;

        /** Prepare click listener field **/
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /** Hide hint cover view, if visible **/
                HintCover hintCover = new HintCover(context);
                hintCover.hideCover();

                /** Need to set a new joke in activity intent extras **/
                Util.setJokeInIntentExtras(context);

                /** Flip it **/
                AnimationFactory.flipTransition(viewAnimator, AnimationFactory.FlipDirection.LEFT_RIGHT);

                /** Update Fragment **/
                updateJokeInFragment();
            }
        };

        /** Bind with view animator **/
        viewAnimator.setOnClickListener(onClickListener);

        /** Initial Update Fragment **/
        updateJokeInFragment();
    }
}
