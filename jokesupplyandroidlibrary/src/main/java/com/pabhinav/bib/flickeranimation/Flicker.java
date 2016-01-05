package com.pabhinav.bib.flickeranimation;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

/**
 * This class uses {@link android.graphics.drawable.AnimationDrawable}
 * object for creating a frame animation.
 * It requires list of drawable resource ids to be flickered in
 * frame animation, flickering time in milliseconds and {@link ImageView}
 * object on which animation is to be done.
 *
 * @author pabhinav.
 */
public class Flicker {

    /**
     * Images will be flickered on this ImageView object.
     */
    private ImageView truckImage;

    /**
     * List of all images to be displayed on ImageView object.
     */
    private Drawable[]  intResIds;

    /**
     * Time after which images will be displayed on ImageView object.
     */
    private int flickTimeInMillis;

    /**
     * This is used to build frame animation.
     */
    private AnimationDrawable animationDrawable;

    /**
     * Default Constructor used to set some private fields of this class.
     *
     * @param truckImage
     * @param intResIds
     * @param flickTimeInMillis
     */
    public Flicker(ImageView truckImage, Drawable[] intResIds, int flickTimeInMillis){
        this.truckImage = truckImage;
        this.intResIds = intResIds;
        this.flickTimeInMillis = flickTimeInMillis;

        /** Creates AnimationDrawable object adding drawable resources **/
        createAnimationDrawable();

        /** Sets image background as animation drawable **/
        truckImage.setImageDrawable(animationDrawable);
    }

    /**
     * Creates {@link AnimationDrawable} object,
     * uses Drawable resources given to this class
     * in its constructor.
     * Also, makes sure animation goes on forever by
     * setting {@code setOneShot} method input as false.
     */
    private void createAnimationDrawable(){
        animationDrawable = new AnimationDrawable();
        for (Drawable d : intResIds) {
            animationDrawable.addFrame(d,flickTimeInMillis);
        }
        animationDrawable.setOneShot(false);
    }


    /**
     * This method begins flickering of images,
     * after certain time interval.
     * Also, makes sure that image is visible.
     */
    public void startFlicking(){

        /** Make Image visible first **/
        truckImage.setVisibility(View.VISIBLE);

        /** Start it **/
        if(!animationDrawable.isRunning()) {
            animationDrawable.start();
        }
    }

    /**
     * This method is invoked when flickering needs to be stopped.
     * Simple method, signals stop of flickering and also, makes
     * ImageView invisible, so that if there is some flickering left,
     * it happens hidden to user.
     */
    public void stopFlicking(){

        /** Hide Image Resource **/
        truckImage.setVisibility(View.GONE);

        /** Stop it **/
        if(animationDrawable.isRunning()) {
            animationDrawable.stop();
        }
    }
}
