package com.pabhinav.bib.jokesupplyandroidlibrary;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.widget.ViewFlipper;

/**
 * Adds 3d flip animation to {@link ViewFlipper}.
 * Make sure that the {@link ViewFlipper} object's clickable attribute is set to true.
 * Also, used to update fragments, based on which is currently active on screen.
 *
 * @author pabhinav.
 */
public abstract class FlipAnimationHelper {

    /**
     * Activity context object
     */
    private Context context;

    /**
     * Decides which fragment to refresh, true refreshes one and false refreshes two
     */
    private boolean toggleRefresh;

    /**
     * Setter for activity context.
     *
     * @param context
     */
    public void setContext(Context context){
        this.context = context;
        toggleRefresh = true;
    }

    /**
     * Sets up 3d animation, expects {@link android.widget.ViewFlipper} present in the given activity.
     */
    public abstract void doSetUp(ViewFlipper viewFlipper);

    /**
     * This method is used to set the joke in fragment.
     * It simply checks the value of {@code toggleRefresh},
     * if it is true, this means that fragment on screen is {@link JokeFragmentCardOne},
     * so that requires updation. else, the fragment on scree is {@link JokeFragmentCardTwo},
     * need to update that.
     */
    protected void updateJokeInFragment(){

        /** if true, update fragment one and make it false, and vice versa. **/
        if(toggleRefresh) {
            updateFragmentOne();
            toggleRefresh = false;
        } else {
            updateFragmentTwo();
            toggleRefresh = true;
        }
    }

    /**
     * This method updates {@link JokeFragmentCardOne}, by calling its {@code setJoke} method.
     */
    private void updateFragmentOne(){
        Fragment fragment_one = ((FragmentActivity) context).getSupportFragmentManager()
                .findFragmentById(R.id.fragment_one);
        if (fragment_one instanceof JokeFragmentCardOne) {
            ((JokeFragmentCardOne) fragment_one).setJoke();
        }
    }

    /**
     * This method updates {@link JokeFragmentCardTwo}, by calling its {@code setJoke} method.
     */
    private void updateFragmentTwo(){
        Fragment fragment_two = ((FragmentActivity) context).getSupportFragmentManager()
                .findFragmentById(R.id.fragment_two);
        if (fragment_two instanceof JokeFragmentCardTwo) {
            ((JokeFragmentCardTwo) fragment_two).setJoke();
        }
    }
}

