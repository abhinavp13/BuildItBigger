package com.pabhinav.bib.builditbigger;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

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
     * @deprecated This function is not used anymore because,
     *              earlier the logic was to call jokesupplylibrary
     *              directly from app module, but now logic got changed
     *              to calling GCM module which in turn will call jokesupplylibrary.
     *              So, deprecating it.
     * @param context
     */
    @Deprecated
    public static void setJokeInIntentExtras(Context context){

        /** Joke Supply java lib **/
        //JokeSupply jokeSupply = new JokeSupply();

        /** Call lib function for providing joke **/
        //String Joke = jokeSupply.tellMeJoke();

        /** Put extra intent value **/
        //Intent i = ((Activity)context).getIntent();
        //i.putExtra(((Activity)context).getResources().getString(R.string.joke_fetching_key),Joke);
    }

    /**
     * This method is used to simply set joke in the intent
     * extra for a given activity context.
     *
     * @param context
     * @param Joke
     */
    public static void setJokeInIntentExtras(Context context, String Joke){
        /** Put extra intent value **/
        Intent i = ((Activity)context).getIntent();
        i.putExtra(((Activity)context).getResources().getString(R.string.joke_fetching_key),Joke);
    }


}
