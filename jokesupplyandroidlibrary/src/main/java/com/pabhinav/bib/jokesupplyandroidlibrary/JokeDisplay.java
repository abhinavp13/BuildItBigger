package com.pabhinav.bib.jokesupplyandroidlibrary;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.ViewFlipper;

/**
 * This activity is used to display joke fetched from joke supplying java library.
 * It uses {@link FlipAnimationHelper}, a helper class for binding 3d flip animation,
 * to view flipper.
 *
 * @author pabhinav.
 */
public class JokeDisplay extends FragmentActivity {

    /**
     * Helps add 3d flip animation to fragment transaction
     */
    private FlipAnimationHelper flipAnimationHelper;

    /**
     * This method is part of Activity lifecycle.
     * Called just before view gets rendered to screen.
     * Content View is set in this function.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_joke_display);

        // TODO : Splash Screen ...
    }

    /**
     * Setter for {@link FlipAnimationHelper}.
     *
     * @param flipAnimationHelper
     */
    protected void setFlipAnimationHelper(FlipAnimationHelper flipAnimationHelper){
        this.flipAnimationHelper = flipAnimationHelper;
    }

    /**
     * Calls {@code doSetup} method, which binds 3d flip animation with view flipper.
     */
    protected void setUpAnimation(){
        /** Set up animation 3d flip **/
        flipAnimationHelper.setContext(this);
        flipAnimationHelper.doSetUp((ViewFlipper)findViewById(R.id.view_flipper));
    }
}
