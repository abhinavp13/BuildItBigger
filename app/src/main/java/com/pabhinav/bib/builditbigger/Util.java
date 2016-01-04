package com.pabhinav.bib.builditbigger;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.pabhinav.JokeSupply;

/**
 * Common Utilities Functions.
 * These are all static functions, so can cause memory leaks,
 * need to be carefully called by any activity.
 *
 * @author pabhinav.
 */
public class Util {

    /**
     * This method is used to set joke as an intent extra.
     * Requires activity context.
     *
     * @param context
     */
    public static void setJokeInIntentExtras(Context context){

        /** Joke Supply java lib **/
        JokeSupply jokeSupply = new JokeSupply();

        /** Call lib function for providing joke **/
        String Joke = jokeSupply.tellMeJoke();

        /** Put extra intent value **/
        Intent i = ((Activity)context).getIntent();
        i.putExtra(((Activity)context).getResources().getString(R.string.joke_fetching_key),Joke);
    }
}
