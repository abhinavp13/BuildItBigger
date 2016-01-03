package com.pabhinav.bib.builditbigger;

import android.os.AsyncTask;

/**
 * This class extends {@link AsyncTask}, used to
 * wait for sometime using a background thread, instead of
 * blocking main thread.
 * One can specify time required to be waited before triggering a callback.
 */
public class AsyncTaskForWaiting extends AsyncTask {

    /**
     * Field storing waiting time in milliseconds.
     */
    private int millisec;

    /**
     * Field storing {@link com.pabhinav.bib.builditbigger.AsyncTaskForWaiting.TimesUp} interface object.
     */
    private TimesUp timesUp;

    /**
     * Default Constructor which takes in waiting time in milliseconds.
     * Also, executes the task. No need to execute again by super class.
     *
     * @param millisec
     */
    public AsyncTaskForWaiting(int millisec){
        this.millisec = millisec;
        execute();
    }

    /**
     * Interface definition for a callback to be invoked when
     * async task has done its task and time for waiting is over.
     */
    public interface TimesUp{
        void onTimesUp();
    }

    /**
     * Setter method for {@link com.pabhinav.bib.builditbigger.AsyncTaskForWaiting.TimesUp} interface object.
     *
     * @param timesUp
     */
    public void setTimesUp(TimesUp timesUp){
        this.timesUp = timesUp;
    }

    /**
     * Method used by {@link AsyncTask} for doing task
     * using a background task, and not disturbing main thread.
     *
     * @param params
     * @return null
     */
    @Override
    protected Object doInBackground(Object[] params) {
        try {
            Thread.sleep((long)millisec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Method used by {@link AsyncTask} after {@code doInBackground}
     * method has successfully completed its invokation.
     * It is used here to make a call to callback interface function.
     *
     * @param param
     */
    @Override
    protected void onPostExecute(Object param) {
        if(timesUp != null){
            timesUp.onTimesUp();
        }
    }
}
