package com.pabhinav.bib.builditbigger;

import android.content.Context;
import android.view.View;
import android.widget.ViewAnimator;
import android.widget.ViewFlipper;

import com.pabhinav.bib.flipanimation.AnimationFactory;
import com.pabhinav.bib.jokesupplyandroidlibrary.FlipAnimationHelper;

/**
 * This class helps set up 3d flip animation for a view flipper.
 * Also, updates the corresponding fragment, whenever a flip is done.
 * This update includes setting up new joke in their designated text views.
 *
 * @author pabhinav.
 */
public class AnimationHelper extends FlipAnimationHelper {

    /**
     * Need activity context.
     */
    private Context context;

    /**
     * Constructor used to initialize context.
     * @param context
     */
    public AnimationHelper(Context context){
        this.context = context;
    }

    /**
     * This method binds flip animation with view flipper.
     * Whenever view flipper is clicked, flip animation is triggered.
     *
     * @param viewFlipper
     */
    @Override
    public void doSetUp(ViewFlipper viewFlipper) {

        /** Transform to view animator **/
        final ViewAnimator viewAnimator = (ViewAnimator)viewFlipper;

        /** Prepare click listener field **/
        final View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /** Hide hint cover view and do nothing else, if it is visible **/
                HintCover hintCover = new HintCover(context);
                if (!hintCover.isHidden()) {
                    hintCover.hideCover();
                    return;
                }

                /** Flip it **/
                AnimationFactory.flipTransition(viewAnimator, AnimationFactory.FlipDirection.LEFT_RIGHT);

                /** Time to show loading animation **/
                updateLoadingAnimationInFragment();

                /** Time to execute GCM module **/
                fetchNewGCMEndpointAsyncTaskObject().execute(context);
            }
        };

        /** Bind with view animator **/
        viewAnimator.setOnClickListener(onClickListener);

        /** Need to execute GCM module initially once **/
        fetchNewGCMEndpointAsyncTaskObject().execute(context);
    }

    /**
     * Creates a {@link GCMEndpointAsyncTask} object, along with
     * implementation of its callback interface.
     *
     * @return {@link GCMEndpointAsyncTask} object
     */
    private GCMEndpointAsyncTask fetchNewGCMEndpointAsyncTaskObject(){
        GCMEndpointAsyncTask gcmEndpointAsyncTask = new GCMEndpointAsyncTask();
        gcmEndpointAsyncTask.setFetchComplete(new GCMEndpointAsyncTask.FetchComplete() {
            @Override
            public void gotTheJoke(String Joke) {

                /** Need to set joke in intent of the activity **/
                Util.setJokeInIntentExtras(context, Joke);

                /** Hides the loading animation and sets joke in fragment container **/
                updateJokeInFragment();
            }
        });

        return gcmEndpointAsyncTask;
    }
}
