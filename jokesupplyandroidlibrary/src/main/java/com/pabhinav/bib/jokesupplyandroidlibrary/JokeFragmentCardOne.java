package com.pabhinav.bib.jokesupplyandroidlibrary;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Fragment one for joke card. It extends {@link AbstractJokeFragment},
 * which is an {@link android.support.v4.app.FragmentActivity} class extension.
 * This class just initializes fragment and, also set joke
 * in its layout's joke text view.
 *
 * @author  pabhinav
 */
public class JokeFragmentCardOne extends AbstractJokeFragment{

    /**
     * This is part of Fragment life cycle. This is used to initialize
     * main rootView for this fragment.
     * Also, we call {@code setJoke} super class method to setting up joke
     * into joke text view, fetched from intent extras.
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return rootView
     */
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        /** Initialize rootView and TextView **/
        rootView = inflater.inflate(R.layout.joke_fragment_one, container, false);;
        textView = (TextView)rootView.findViewById(R.id.text_view_card_one);

        /** Call the super class setJoke method **/
        setJoke();

        /** return the main rootView **/
        return rootView;
    }
}
