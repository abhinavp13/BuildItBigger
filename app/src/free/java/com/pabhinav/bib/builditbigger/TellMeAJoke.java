package com.pabhinav.bib.builditbigger;

import android.os.Bundle;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.pabhinav.bib.jokesupplyandroidlibrary.JokeDisplay;

/**
 * This class extends {@link JokeDisplay} which is an {@link android.support.v4.app.FragmentActivity}.
 * Adds some additional functionalities over {@link JokeDisplay}.
 *
 * @author pabhinav.
 */
public class TellMeAJoke extends JokeDisplay {

    /**
     * HintCover object used to show and hide cover view.
     */
    private HintCover hintCover;

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

        /** Set the main content layout **/
        setContentView(R.layout.activity_tell_me_a_joke);

        /** Add flip animation **/
        AnimationHelper animationHelper = new AnimationHelper(this);
        setFlipAnimationHelper(animationHelper);
        setUpAnimation();

        /** Hide cover, wait for some time and then show hint cover again.
         * This is just to produce an visual effect :) **/
        hintCover = new HintCover(this);
        hintCover.hideCover();
        AsyncTaskForWaiting asyncTaskForWaiting = new AsyncTaskForWaiting(500);
        asyncTaskForWaiting.setTimesUp(new AsyncTaskForWaiting.TimesUp() {
            @Override
            public void onTimesUp() {
                hintCover.showCover();
            }
        });

        /** AdMob addition **/
        AdView adView = (AdView)findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        adView.loadAd(adRequest);
    }

    /**
     * "Got It" button clicked.
     * Need to hide hint cover view on the screen.
     *
     * @param v
     */
    public void gotItClicked(View v){
        hintCover.hideCover();
    }
}
