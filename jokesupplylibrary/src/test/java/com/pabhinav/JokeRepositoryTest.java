package com.pabhinav;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;

/**
 * Test File for unit testing {@link JokeRepository} class.
 *
 * Created by pabhinav.
 */
public class JokeRepositoryTest {

    private JokeRepository jokeRepository;

    @Before
    public void setUp() throws IOException{
        jokeRepository = new JokeRepository(new FileInputStream(new File("src/main/resources/repo/jokes")));
    }

    @Test(expected = NullPointerException.class)
    public void NullJokeListTest() throws Exception{

        /** Update the local joke repos variable in joke supply class */
        Field privateJokeRepository = JokeRepository.class.getDeclaredField("jokeList");
        privateJokeRepository.setAccessible(true);
        privateJokeRepository.set(jokeRepository, null);

        jokeRepository.getJokeList();
    }

    @Test
    public void NullJokeListSizeTest() throws Exception{
        /** Update the local joke repos variable in joke supply class */
        Field privateJokeRepository = JokeRepository.class.getDeclaredField("jokeList");
        privateJokeRepository.setAccessible(true);
        privateJokeRepository.set(jokeRepository, null);

        assertEquals(0,jokeRepository.getJokeListSize());
    }
}
