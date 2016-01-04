package com.pabhinav.bib.builditbigger;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.pabhinav.bib.gcm.myApi.MyApi;

import java.io.IOException;

/**
 * This class uses Google Cloud Module Service for calling
 * registered {@code TellMeAJoke} api and fetching joke
 * asynchronously.
 * It uses builder pattern to initialize service and call its api.
 *
 * @author pabhinav.
 */
public class GCMEndpointAsyncTask extends AsyncTask<Context, Void, String> {

    /**
     * App Engine service module object.
     */
    private static MyApi myApiService = null;

    /**
     * Activity {@link Context} object.
     */
    private Context context;

    @Override
    protected String doInBackground(Context... params) {

        /** Activity {@link Context} object initialize **/
        context = params[0];

        if(myApiService == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
        .setRootUrl("https://gold-atlas-118017.appspot.com/_ah/api/");
            myApiService = builder.build();
        }

        /** Try to fetch joke from java library **/
        try {
            return myApiService.tellMeAJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(context, result, Toast.LENGTH_LONG).show();
    }
}
