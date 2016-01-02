package com.pabhinav.bib.jokesupplyandroidlibrary;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Abstract class used by {@link JokeFragmentCardOne} and {@link JokeFragmentCardTwo},
 * for extending some common features.
 * Some static behaviour, common to both fragment includes setting joke in their corresponding
 * designated text view.
 *
 * @author pabhinav
 */
public abstract class AbstractJokeFragment extends Fragment{

    /**
     * Container for this fragment, in which every view gets rendered.
     */
    protected View rootView;

    /**
     * Text view displaying joke.
     */
    protected TextView textView;

    /**
     * This method is part of fragment lifecycle.
     * Called when fragment view is just about to get rendered to screen.
     * This gets implemented in extending subclasses.
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return rootView
     */
    @Override
    public abstract View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    /**
     * This method is used to set joke in its text view.
     */
    public void setJoke(){

        /** Make sure rootView is not null **/
        if(rootView == null){
            return;
        }

        /** Set Joke in text view **/
        textView.setText(Util.getJokeFromIntentExtras(getActivity()));
    }
}
