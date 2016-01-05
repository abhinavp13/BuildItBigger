package com.pabhinav.bib.jokesupplyandroidlibrary;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pabhinav.bib.flickeranimation.Flicker;

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
     * Text View displaying please wait loading is going on.
     */
    protected TextView plzWaitTextView;

    /**
     * ImageView used for displaying loading image.
     */
    protected ImageView truckLoadingImage;

    /**
     * {@link Flicker} object used to start and stop loading animation.
     */
    protected Flicker flicker;

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

        /** Hide Loading **/
        hideLoading();

        /** Set Joke in text view **/
        textView.setText(Util.getJokeFromIntentExtras(getActivity()));
    }

    /**
     * Sets up {@link Flicker} animation object,
     * requires {@link ImageView} for its initialization.
     */
    public void setUpFlickerImageView(){

        /** Frame Drawables **/
        Drawable[] drawables = new Drawable[]{
                getResources().getDrawable(R.drawable.truck_unloading_1),
                getResources().getDrawable(R.drawable.truck_unloading_2),
                getResources().getDrawable(R.drawable.truck_unloading_3),
                getResources().getDrawable(R.drawable.truck_unloading_4)};

        /** Initialize Flicker animation **/
        flicker = new Flicker(truckLoadingImage, drawables, 250);
    }

    /**
     * Method used to display loading elements
     * present in the fragment's layout.
     * Also, start flickering of image to display
     * loading animation on screen.
     */
    public void displayLoading(){

        plzWaitTextView.setVisibility(View.VISIBLE);
        truckLoadingImage.setVisibility(View.VISIBLE);
        textView.setVisibility(View.GONE);

        /** Need to start flickering **/
        flicker.startFlicking();
    }

    /**
     * Method used to hide loading elements
     * present in the fragment's layout.
     * Also, stop flickering of image to hide
     * loading animation on screen.
     */
    public void hideLoading(){

        textView.setVisibility(View.VISIBLE);
        plzWaitTextView.setVisibility(View.GONE);
        truckLoadingImage.setVisibility(View.GONE);

        /** Need to stop flickering **/
        flicker.stopFlicking();
    }

}
