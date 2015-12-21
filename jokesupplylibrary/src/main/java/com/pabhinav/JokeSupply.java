package com.pabhinav;

import java.io.IOException;
import java.util.Random;
import java.util.logging.Logger;

/**
 * This class is used to supply joke.
 * It uses {@link JokeRepository} for fetching Jokes
 * and choses one from them.
 *
 * @author pabhinav
 */
public class JokeSupply {

    /**
     * Logger for this class.
     */
    private final static Logger LOGGER = Logger.getLogger(JokeSupply.class.getName());

    /**
     * local joke repos variable.
     */
    private JokeRepository jokeRepository;

    /**
     * Constructor for initializing joke repository.
     */
    public JokeSupply(){
        jokeRepository = new JokeRepository();
    }

    /**
     * This Function fetches joke from {@link JokeRepository}
     * and chooses one from the listed jokes.
     * The criteria for choosing a Joke is random java util function.
     *
     * @return joke as a string.
     */
    public String tellMeJoke(){
        try {
            return (jokeRepository.getJokeList()).get(getRandomValue(jokeRepository.getJokeListSize()));
        } catch (IOException ioe){
            LOGGER.warning("JokeSupply was not able to supply joke. Reason : " + ioe);
            return "Derp !!!";
        }
    }

    /**
     * This function retuns a random value from 0 to size specified.
     * 0 is inclusive and size is exclusive.
     *
     * @param size
     * @return random integer value in between 0(inclusive) to size(exclusive)
     */
    private int getRandomValue(int size){
        Random random = new Random();
        return random.nextInt(size);
    }
}
