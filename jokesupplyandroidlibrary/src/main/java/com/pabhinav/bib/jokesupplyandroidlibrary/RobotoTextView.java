package com.pabhinav.bib.jokesupplyandroidlibrary;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Wrapper over {@link TextView}, this can be used to add many additional
 * functionalities, currently it supports addition of Roboto font to
 * text typeface.
 *
 * @author pabhinav.
 */
public class RobotoTextView extends TextView {

    /**
     * Constructor initialized only using {@link Context} object.
     *
     * @param context
     */
    public RobotoTextView(Context context) {
        super(context);
    }

    /**
     * Constructor initialized using {@link Context} and {@link AttributeSet} object.
     *
     * @param context
     * @param attrs
     */
    public RobotoTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        changeTypeFace(context, attrs);
    }

    /**
     * Constructor initialized using {@link Context}, {@link AttributeSet} and style attribute id.
     *
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public RobotoTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        changeTypeFace(context, attrs);
    }

    /**
     * Constructor initialized using {@link Context}, {@link AttributeSet}, style attribute and style resource id.
     *
     * @param context
     * @param attrs
     * @param defStyleAttr
     * @param defStyleRes
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public RobotoTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context , attrs, defStyleAttr, defStyleRes);
        changeTypeFace(context, attrs);
    }

    /**
     * This method is used to change typeface given {@link AttributeSet} and {@link Context} objects.
     * If font name attribute is set while using this text view, typeface is changed to the given font name.
     *
     * @param context
     * @param attributeSet
     */
    private void changeTypeFace(Context context, AttributeSet attributeSet){

        /** Typeface.createFromAsset does not work in the layout editor mode **/
        if(isInEditMode()){
            return;
        }

        TypedArray styledAttrs = context.obtainStyledAttributes(attributeSet, R.styleable.RobotoTextView);
        String fontName = styledAttrs.getString(R.styleable.RobotoTextView_font);
        styledAttrs.recycle();

        if (fontName != null) {
            Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/" + fontName);
            setTypeface(typeface);
        }
    }
}
