package com.example.miniproyecto3_battleship.model.planeTextFile;

import java.io.IOException;

/**
 * Interface defining operations for handling plain text files.
 *
 * <p>This interface ensures consistent implementation of methods for writing
 * text to files and reading text from files.</p>
 *
 * <p>It is designed to abstract file operations, making it easier to manage
 * file-based storage in the Battleship game or other applications.</p>
 *
 * @author Juan David Lopez V
 */
public interface IPlaneTextFileHandler {

    /**
     * Writes the specified text to a plain text file.
     *
     * <p>If the file does not exist, it will be created. If it does exist, the content
     * will be overwritten.</p>
     *
     * @param fileName the name of the file to write to.
     * @param text the text to write to the file.
     * @throws IOException if an I/O error occurs while writing to the file.
     */
    void writeToFile(String fileName, String text);

    /**
     * Reads the contents of a plain text file and returns it as an array of strings.
     *
     * <p>The method reads the file line by line, concatenates the lines with commas,
     * and splits the content into an array using a comma as the delimiter.</p>
     *
     * @param fileName the name of the file to read from.
     * @return an array of strings containing the lines of the file, split by commas.
     * @throws IOException if an I/O error occurs while reading from the file.
     */

    String[] readFromFile(String fileName);
}
