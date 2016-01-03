package com.pabhinav.bib.builditbigger;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * This is a placeholder activity for splash screen.
 * It just shows an image for some time, also show scale
 * animation over it, then move the control to other
 * activity.
 *
 * @author pabhinav.
 */
public class SplashActivity extends Activity {

    /**
     * Splash waiting time.
     */
    private static final int WAITING_TIME = 3500;

    /**
     * Using ButterKnife library to bind splash image.
     */
    @Bind(R.id.image_view_splash)
    ImageView splashImageView;

    /**
     * Called when the activity is starting.  This is where most initialization
     * should go: calling {@link #setContentView(int)} to inflate the
     * activity's UI, using {@link #findViewById} to programmatically interact
     * with widgets in the UI.
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        /** Extend layout to status bar,
         *
         *  Complete list of items done to achieve this :
         *
         *  Added 'Flag_layout_no_limits' flag to window.
         *  Added translucent behaviour (along with some other
         *  properties) can be checked at values-v21/styles of {@link jokesupplyandroidlibrary}.
         *  Also, important, add 'android:fitsSystemWindows="true"' behaviour to the main
         *  container layout.
         */
        extendImageToDrawOverStatusBar();

        /** Add animation to splash image **/
        addAnimationToSplashImage();

        /** Start counting time **/
        AsyncTaskForWaiting asyncTaskForWaiting = new AsyncTaskForWaiting(WAITING_TIME);

        /** Listen from class when times up **/
        asyncTaskForWaiting.setTimesUp(new AsyncTaskForWaiting.TimesUp() {
            @Override
            public void onTimesUp() {

                /** Start the next activity and finish this **/
                Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    /**
     * Sets the color of status bar.
     * Since, setting status bar color requires api version >= KITKAT,
     * an important check is inside this method.
     */
    public void extendImageToDrawOverStatusBar(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }

    /**
     * This method add animation functionality to splash image.
     * Its just zoom in animation, it will keep on zooming in
     * for some time and then new activity will get invoked.
     */
    public void addAnimationToSplashImage(){
        Animation scale = AnimationUtils.loadAnimation(this,R.anim.scale_animation);
        splashImageView.startAnimation(scale);
    }

}
