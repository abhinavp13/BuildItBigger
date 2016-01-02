package com.pabhinav.bib.jokesupplyandroidlibrary;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

/**
 * Utility Functions to be used by activities.
 * These are all static functions, so can cause memory leaks,
 * need to be carefully called by any activity.
 *
 * @author pabhinav
 */
public class Util {

    /**
     * Tries to fetch joke from intent extras.
     * If found returns the joke, else returns "Derp !!!".
     *
     * @return joke
     */
    public static String getJokeFromIntentExtras(Context context){

        /** Fetch Intent extras **/
        Bundle extras = ((Activity)context).getIntent().getExtras();

        /** Make sure its not null **/
        if(extras == null) {
            return "Derp !!!";
        }

        /** return the joke **/
        return extras.getString(((Activity)context).getResources().getString(R.string.joke_fetching_key));
    }
}
