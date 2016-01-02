package com.pabhinav;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * This class parses a text file where jokes can be added.
 * It will return the whole list of jokes.
 *
 * Created by pabhinav
 */
public class JokeRepository {

    /**
     * Private variable used to store complete list of jokes.
     */
    private ArrayList<String> jokeList;

    /**
     * Local variable for reading jokes.
     */
    private InputStream inputStream;

    /**
     * Default Constructor.
     * Initializes empty joke list.
     */
    public JokeRepository(InputStream inputStream){
        this.inputStream = inputStream;
        jokeList = new ArrayList<>();
    }

    /**
     * Returns the size of list of jokes.
     * Returns zero if jokeList is null.
     *
     * @return size of jokeList variable.
     */
    public int getJokeListSize(){
        if(jokeList != null) {
            return jokeList.size();
        }
        return 0;
    }

    /**
     * Method used to parses text file, in which jokes
     * are added.
     *
     * @throws IOException
     */
    private void jokeFileParser() throws IOException{

        /** Read file wrapper for reading joke list file **/
        ReadFile readFile = new ReadFile(inputStream);

        String joke = readFile.getNextLine();
        while(joke != null){

            /** Removing empty spaces in the beginning and end of joke */
            joke = joke.trim();

            /** Joke should not be empty string or a comment line */
            if(!joke.startsWith("#")){
                jokeList.add(joke);
            }

            /** Keep on reading next joke, till it is null */
            joke = readFile.getNextLine();
        }
    }

    /**
     * Method used to fetch a list of jokes as a string.
     * It calls helper method {@code jokeFileParser} which parses
     * a text file and updates local list of jokes, which it then
     * returns.
     *
     * @return list of jokes as string.
     * @throws IOException
     */
    public ArrayList<String> getJokeList() throws IOException{

        /** Call parser to parse file with jokes **/
        jokeFileParser();

        /** return the complete joke list */
        return jokeList;
    }
}
