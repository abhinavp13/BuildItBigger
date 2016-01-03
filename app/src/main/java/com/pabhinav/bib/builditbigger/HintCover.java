package com.pabhinav.bib.builditbigger;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * This class handles showing and hiding
 * hint cover view elements.
 *
 * @author pabhinav
 */
public class HintCover {

    /**
     * Main hint cover view.
     */
    View coverView;

    /**
     * Hint text in cover view.
     */
    TextView tapTextView;

    /**
     * "Got iT" button object.
     */
    Button gotButton;

    /**
     * Context object field.
     */
    private Context context;

    /**
     * Constructor for initializing context object.
     *
     * @param context
     */
    public HintCover(Context context){
        this.context = context;
        coverView = (View)((Activity)context).findViewById(R.id.cover_view);
        tapTextView = (TextView)((Activity)context).findViewById(R.id.tapTextView);
        gotButton = (Button)((Activity)context).findViewById(R.id.got_button);
    }

    /**
     * Hides complete hint cover view elements.
     */
    public void hideCover(){
        gotButton.setVisibility(View.GONE);
        tapTextView.setVisibility(View.GONE);
        coverView.setVisibility(View.GONE);
    }

    /**
     * Shows complete hint cover view elements.
     */
    public void showCover(){
        gotButton.setVisibility(View.VISIBLE);
        tapTextView.setVisibility(View.VISIBLE);
        coverView.setVisibility(View.VISIBLE);
    }

}
