package com.pabhinav;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * Wrapper class for reading file.
 * Uses scanner function and handles proper opening and closing of file.
 *
 * @author pabhinav
 */
class ReadFile {

    /**
     * Stores joke input stream
     */
    private InputStream inputStream;

    /**
     * Stores local scanner variable.
     */
    private Scanner scanner;

    /**
     * Constructor saving file path and opening file for reading.
     *
     * @param inputStream
     * @throws IOException
     */
    public ReadFile(InputStream inputStream) throws IOException{
        this.inputStream = inputStream;
        openFileForReading();
    }

    /**
     * Local Function trying to open file for reading.
     *
     * @throws IOException
     */
    private void openFileForReading() throws IOException{
        scanner = new Scanner(inputStream);
    }

    /**
     * This method is called to get line by line output read from file.
     * Returns null if no further line is left or, scanner is not initialized properly.
     *
     * @return read line from file as a String.
     * @throws IOException
     */
    public String getNextLine() throws IOException{
        if(scanner != null){
            if(scanner.hasNextLine()){
                /** return line read by scanner */
                return scanner.nextLine();
            } else {
                /** Close the scanner stream */
                scanner.close();

                /** if no further line is left, return null */
                return null;
            }
        }

        /** if scanner not initialized properly, return null */
        return null;
    }

}

