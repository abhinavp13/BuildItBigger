package com.pabhinav.bib.flickeranimation;

import android.view.View;
import android.widget.ImageView;

/**
 * This class helps flicker images after some interval of time.
 * It needs the {@link ImageView} on which images will be set
 * after the given flick time, also, images resource ids need
 * to be provided to this class.
 *
 * This class is deprecated and 'deprecated' is suffixed,
 * reason : very slow animation shown by using it.
 *
 * @author pabhinav
 */
@Deprecated
public class FlickerDeprecated {

    /**
     * Images will be flickered on this ImageView object.
     */
    private ImageView truckImage;

    /**
     * List of all images to be displayed on ImageView object.
     */
    private int[]  intResIds;

    /**
     * Time after which images will be displayed on ImageView object.
     */
    private int flickTimeInMillis;

    /**
     * This pattern is important, it decides which image resource is picked up when,
     * For eg, say we have three image resources, then the pattern generated by this
     * class is : Image 1, Image 2, Image 3, Image 2, Image 1.
     * So, images will be flickered in this order.
     *
     * This is default pattern, to change this,
     * extend this class and override {@code prepareFlickerPattern} method.
     */
    private int[] flickerPattern;

    /**
     * Stop signal used to stop further flickering.
     */
    private boolean stop;

    /**
     * Default Constructor used to set some private fields of this class.
     *
     * @param truckImage
     * @param intResIds
     * @param flickTimeInMillis
     */
    public FlickerDeprecated(ImageView truckImage, int[] intResIds, int flickTimeInMillis){
        this.truckImage = truckImage;
        this.intResIds = intResIds;
        this.flickTimeInMillis = flickTimeInMillis;
        stop = false;

        /** Prepares Flicker Pattern for this class **/
        prepareFlickerPattern();
    }

    /**
     * Generates Flicker pattern,
     * This pattern is like (eg with 3 images):
     * Image 1, Image 2, Image 3, Image 2, Image 1.
     * This will be the pattern in which images will be short after flickTimeInMillis interval.
     */
    protected void prepareFlickerPattern(){
        flickerPattern = new int[(2*intResIds.length)-1];
        for(int i = 1; i<= (2*intResIds.length)-1; i++){
            if(i<5){
                flickerPattern[i-1] = i;
            } else {
                flickerPattern[i-1] = (2*intResIds.length) - i;
            }
        }
    }

    /**
     * This method begins flickering of images,
     * after certain time interval.
     * This flickering is done asynchronously using {@link AsyncFlickingTask} class.
     */
    public void startFlicking(){

        /** if stop triggered, then stop from further flicking **/
        if(stop){
            return;
        }

        /** Make Image visible first **/
        truckImage.setVisibility(View.VISIBLE);

        /** Set First Image **/
        truckImage.setBackgroundResource(intResIds[0]);

        /** Start async image flicking task, and on its completion start again, till stop signal is invoked **/
        AsyncFlickingTask asyncFlickingTask = new AsyncFlickingTask(truckImage,intResIds,flickTimeInMillis,flickerPattern);
        asyncFlickingTask.setTaskCompleted(new AsyncFlickingTask.TaskCompleted() {
            @Override
            public void onTaskComplete() {

                /** Once whole flickering pattern is complete, start over again, till stop signal is triggered **/
                startFlicking();
            }
        });
    }

    /**
     * This method is invoked when flickering needs to be stopped.
     * Simple method, signals stop of flickering and also, makes
     * ImageView invisible, so that if there is some flickering left,
     * it happens hidden to user.
     */
    public void stopFlicking(){

        /** Stop Further flicking **/
        stop = true;

        /** Hide Image Resource **/
        truckImage.setVisibility(View.GONE);
    }
}