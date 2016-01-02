package com.pabhinav.bib.builditbigger;

import android.os.Bundle;

import com.pabhinav.bib.jokesupplyandroidlibrary.JokeDisplay;

/**
 * This class extends {@link JokeDisplay} which is an {@link android.support.v4.app.FragmentActivity}.
 * Adds some additional functionalities over {@link JokeDisplay}.
 *
 * @author pabhinav.
 */
public class TellMeAJoke extends JokeDisplay {

    /**
     * The onCreate method used for setting up the content view
     * Also used here to add joke as an intent extra for this activity,
     * and also add flip animation for changing joke.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /** Set initial intent extra and joke in fragment **/
        Util.setJokeInIntentExtras(this);

        /** Add flip animation **/
        AnimationHelper animationHelper = new AnimationHelper(this);
        setFlipAnimationHelper(animationHelper);
        setUpAnimation();
    }
}
