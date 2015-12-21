package com.pabhinav;

import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import static org.junit.Assert.*;

/**
 * Test File for unit testing {@link JokeSupply} class.
 *
 * Created by pabhinav.
 */
public class JokeSupplyTest {

    /**
     * Local JokeSupply Variable.
     */
    private JokeSupply jokeSupply;

    private JokeRepository jokeRepository;

    @Before
    public void setUp(){
        jokeSupply = new JokeSupply();
        jokeRepository = new JokeRepository();
    }

    @Test
    public void TellMeJokeTest(){
        String value = jokeSupply.tellMeJoke();
        assertNotEquals("Derp !!!", value);
    }

    @Test
    public void TellMeJokeReadFileFailureTest() throws Exception{

        /** Need to change the path value */
        setFinalStatic(JokeRepository.class, "FILE_PATH_LOCATION", "");

        /** Update the local joke repos variable in joke supply class */
        Field privateJokeRepository = JokeSupply.class.getDeclaredField("jokeRepository");
        privateJokeRepository.setAccessible(true);
        privateJokeRepository.set(jokeSupply,jokeRepository);

        String value = jokeSupply.tellMeJoke();
        assertEquals("Derp !!!", value);

        /** Need to change the path value to its original value, so that other tests are not affected */
        setFinalStatic(JokeRepository.class, "FILE_PATH_LOCATION", new String("src/main/res/repo/jokes"));
    }

    @Test
    public void getRandomValueTest() throws Exception{

        /** Use reflection for testing private method */
        Method privateRandomValueMethod = JokeSupply.class.getDeclaredMethod("getRandomValue", int.class);
        privateRandomValueMethod.setAccessible(true);

        for(int i = 0; i<100; i++) {
            int value = (int) privateRandomValueMethod.invoke(jokeSupply, 10);
            assertTrue(0 <= value && value < 10);
        }
    }

    /**
     * This local method is used to change the value for a field which is private final static, using reflection.
     *
     * @param clazz
     * @param fieldName
     * @param newValue
     * @throws Exception
     */
     private void setFinalStatic(Class clazz, String fieldName, Object newValue) throws NoSuchFieldException, IllegalAccessException {
        Field field = clazz.getDeclaredField(fieldName);
        field.setAccessible(true);
        Field modifiers = field.getClass().getDeclaredField("modifiers");
        modifiers.setAccessible(true);
        modifiers.setInt(field, field.getModifiers() & ~Modifier.FINAL);
        field.set(jokeRepository, newValue);
    }
}
