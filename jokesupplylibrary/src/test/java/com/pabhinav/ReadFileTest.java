package com.pabhinav;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import static org.junit.Assert.*;

/**
 * Test class for {@link ReadFile}.
 *
 * Created by pabhinav
 */
public class ReadFileTest {

    @Test
    public void NullScannerTest() throws Exception{

        ReadFile readFile = new ReadFile(new InputStream() {
            @Override
            public int read() throws IOException {
                return 0;
            }
        });

        /** Update the local joke repos variable in joke supply class */
        Field privateJokeRepository = ReadFile.class.getDeclaredField("scanner");
        privateJokeRepository.setAccessible(true);
        privateJokeRepository.set(readFile, null);

        assertEquals(null, readFile.getNextLine());
    }
}
