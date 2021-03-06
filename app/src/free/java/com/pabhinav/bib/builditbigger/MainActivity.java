package com.pabhinav.bib.builditbigger;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends Activity {

    /**
     * Fields using butterknife library to get initialized.
     */
    @Bind(R.id.truckImage) ImageView truckImageView;
    @Bind(R.id.tapTextView) TextView tapTextView;

    /**
     * {@link InterstitialAd} object used to display interstitial ad in this activity.
     */
    private InterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /** Initialize butter knife **/
        ButterKnife.bind(this);

        /** Set status bar color  **/
        setStatusBarColor();

        /** Prepare Interstitial ads **/
        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(getResources().getString(R.string.interstitial_ad_unit_id));
        AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        interstitialAd.loadAd(adRequest);
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                changeResourceImageAndStartAnimation();
            }
        });
    }

    /**
     * Sets the color of status bar.
     * Since, setting status bar color requires api level >= 21,
     * {@link TargetApi} tag is added with this method.
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setStatusBarColor(){
        getWindow().setStatusBarColor(getResources().getColor(com.pabhinav.bib.jokesupplyandroidlibrary.R.color.dark_green));
    }

    /**
     * This method is invoked whenever truck image
     * is tapped by user, present on the screen.
     * Its basic functionality is to set visibility of hint text,
     * and change the image of still truck to running truck.
     * Also, register animation to this image and starts it.
     * Added functionality : Interstitial ads are displayed
     * before animation display.
     *
     * @param v
     */
    public void jokeTruckClicked(View v){

        /** if interstitial ad is available, display it, else, go for further animations **/
        if(interstitialAd.isLoaded()){
            interstitialAd.show();
        } else {
            changeResourceImageAndStartAnimation();
        }
    }

    /**
     * This method hides 'tap below' text, changes image resource
     * for truck image, and also prepares and start truck running
     * animation.
     */
    private void changeResourceImageAndStartAnimation(){

        /** Hide hint text **/
        tapTextView.setVisibility(View.INVISIBLE);

        /** Change the image resource to running truck **/
        truckImageView.setBackgroundResource(R.drawable.joke_supply_running_truck_green);

        /** Start running animation **/
        prepareAndStartAnimation();
    }

    /**
     * This method adds animation to truck image.
     * Animation is just an translate animation, trucks runs from its position
     * to right of the screen, and moves out of it.
     * Also, an async task is registered with some waiting time,
     * on its completion, new activity is started.
     */
    public void prepareAndStartAnimation(){

        /** Get the screen Width **/
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;

        /** Animate truck **/
        Animation animation = new TranslateAnimation(0, (width == 0)? 750 : (int)(width/2) , 0, 0);
        animation.setDuration(1000);
        animation.setStartOffset(100);
        animation.setFillAfter(true);
        truckImageView.startAnimation(animation);

        /**
         * We wait for a time little less than the complete animation time,
         * this is because creating an intent and starting activity takes
         * time and need to be started earlier
         */
        AsyncTaskForWaiting asyncTaskForWaiting = new AsyncTaskForWaiting(750);
        asyncTaskForWaiting.setTimesUp(new AsyncTaskForWaiting.TimesUp() {
            @Override
            public void onTimesUp() {
                Intent intent = new Intent(MainActivity.this, TellMeAJoke.class);
                startActivity(intent);
                finish();
            }
        });
    }

}
