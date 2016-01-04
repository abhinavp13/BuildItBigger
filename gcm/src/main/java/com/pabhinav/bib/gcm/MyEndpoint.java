package com.pabhinav.bib.gcm;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.pabhinav.JokeSupply;

/** An endpoint class we are exposing */
@Api(
  name = "myApi",
  version = "v1",
  namespace = @ApiNamespace(
    ownerDomain = "gcm.bib.pabhinav.com",
    ownerName = "gcm.bib.pabhinav.com",
    packagePath=""
  )
)
public class MyEndpoint {

   /** A simple endpoint method that uses jokesupplylibrary for fetching joke and setting it as response. */
   @ApiMethod(name = "tellMeAJoke")
    public MyBean tellMeAJoke() {
        MyBean response = new MyBean();
        JokeSupply jokeSupply = new JokeSupply();
        response.setData(jokeSupply.tellMeJoke());
        return response;
    }

}
