package com.pabhinav;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

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
    public void setUp() throws IOException{
        jokeSupply = new JokeSupply();
        jokeRepository = new JokeRepository(new FileInputStream(new File("src/main/resources/repo/jokes")));
    }

    @Test
    public void TellMeJokeTest(){
        String value = jokeSupply.tellMeJoke();
        assertNotEquals("Derp !!!", value);
    }

    @Test
    public void jokeRepositoryNullTest() throws Exception {

        /** change final static value for making {@link JokeRepository} object null
         * One thing to note here is important,
         * Sometimes its not possible to change the value of a String
         * object, which is private final static,
         * Do the following changes for changing its value :
         * private static final String value = new String("Value");
         * Now, it should work !!! **/
        setFinalStatic(JokeSupply.class, "PATH_IN_RESOURCE", null);

        /** Testing with null {@link JokeRepository} **/
        jokeSupply = new JokeSupply();
        String value = jokeSupply.tellMeJoke();
        assertEquals("Derp !!!", value);

        /** change final static value for remaining test functions to run correctly **/
        setFinalStatic(JokeSupply.class, "PATH_IN_RESOURCE", "/repo/jokes");
        jokeSupply = new JokeSupply();
    }

    @Test
    public void TellMeJokeReadFileFailureTest() throws Exception {

        /** Update the local joke repos variable in joke supply class */
        Field privateInputStream = JokeRepository.class.getDeclaredField("inputStream");
        privateInputStream.setAccessible(true);
        privateInputStream.set(jokeRepository, null);

        /** Update the local joke repos variable in joke supply class */
        Field privateJokeRepository = JokeSupply.class.getDeclaredField("jokeRepository");
        privateJokeRepository.setAccessible(true);
        privateJokeRepository.set(jokeSupply,jokeRepository);

        String value = jokeSupply.tellMeJoke();
        assertEquals("Derp !!!", value);

        /** Need to change the path value to its original value, so that other tests are not affected */
        Field privateInputStream1 = JokeRepository.class.getDeclaredField("inputStream");
        privateInputStream1.setAccessible(true);
        File file = new File("src/main/resources/repo/jokes");
        privateInputStream1.set(jokeRepository, new FileInputStream(file));
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
